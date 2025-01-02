package com.rlti.hexagonal.pessoa.application.core.usecase;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.rlti.hexagonal.handler.ArquivoVazioException;
import com.rlti.hexagonal.handler.ProcessamentoArquivoException;
import com.rlti.hexagonal.pessoa.adapters.input.request.CnaeFilter;
import com.rlti.hexagonal.pessoa.adapters.input.request.CnaeRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.CnaeResponse;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;
import com.rlti.hexagonal.pessoa.application.port.input.CnaeInputPort;
import com.rlti.hexagonal.pessoa.application.port.output.CnaeOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CnaeUseCase implements CnaeInputPort {
    private final CnaeOutputPort outputPort;
    private final ModelMapper modelMapper;

    private static final Logger LOGGER = Logger.getLogger(CnaeUseCase.class.getName());

    public CnaeUseCase(CnaeOutputPort outputPort, ModelMapper modelMapper) {
        this.outputPort = outputPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CnaeResponse> findAll() {
        var cnaes = outputPort.findAll();
        return CnaeResponse.convert(cnaes);
    }

    @Override
    public void addCnaes(Long idPessoa, List<CnaeRequest> cnaeRequestList) {
        var cnaes = cnaeRequestList.stream()
                .map(request -> modelMapper.map(request, Cnae.class))
                .toList();
        long principais = cnaes.stream()
                .filter(Cnae::isPrincipal)
                .count();
        if (principais > 1) {
            throw new IllegalArgumentException("Apenas um CNAE pode ser marcado como principal.");
        }
        outputPort.addCnaes(idPessoa, cnaes);
    }


    public void processarCnae(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ArquivoVazioException("O arquivo está vazio.");
        }

        try (InputStreamReader isr = new InputStreamReader(file.getInputStream(), StandardCharsets.ISO_8859_1);
             CSVReader csvReader = new CSVReaderBuilder(isr)
                     .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                     .build()) {

            processarLinhas(csvReader);
        } catch (IOException | CsvValidationException e) {
            throw new ProcessamentoArquivoException("Erro ao processar o arquivo: " + e.getMessage(), e);
        }
    }

    private void processarLinhas(CSVReader csvReader) throws IOException, CsvValidationException {
        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            if (nextLine.length < 2) {
                final String[] linhaInvalida = nextLine;
                if (LOGGER.isLoggable(Level.WARNING)) {
                    LOGGER.warning(() -> String.format("Linha inválida: %s", Arrays.toString(linhaInvalida)));
                }
                continue;
            }
            processarLinha(nextLine);
        }
    }

    private void processarLinha(String[] nextLine) {
        try {
            String subclasse = nextLine[0].trim();
            String denominacao = nextLine[1].trim();
            Cnae cnae = new Cnae(subclasse, denominacao);

            outputPort.processarCnae(cnae);
        } catch (NumberFormatException e) {
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.warning(() -> String.format("Código inválido: %s", nextLine[0]));
            }
        }
    }

    @Override
    public void salvarSubclasses() {
        outputPort.salvarSubclasses();
    }

    @Override
    public void addCnaesV2(Long idPessoa, String cnaePrincipal, List<CnaeRequest> cnaeRequestList) {
        var cnaes = cnaeRequestList.stream()
                .map(request -> modelMapper.map(request, Cnae.class))
                .toList();

        outputPort.addCnaesV2(idPessoa, cnaePrincipal, cnaes);
    }

    @Override
    public List<CnaeResponse> getAllCnaesWithFilter(CnaeFilter params) {
        var cnaes = outputPort.getAllCnaesWithFilter(params);
        return CnaeResponse.convert(cnaes);
    }

}
