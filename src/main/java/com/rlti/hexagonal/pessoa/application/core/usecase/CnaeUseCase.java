package com.rlti.hexagonal.pessoa.application.core.usecase;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.rlti.hexagonal.pessoa.adapters.input.request.CnaeRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.CnaeResponse;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;
import com.rlti.hexagonal.pessoa.application.port.input.CnaeInputPort;
import com.rlti.hexagonal.pessoa.application.port.output.CnaeOutputPort;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CnaeUseCase implements CnaeInputPort {
    private final CnaeOutputPort outputPort;
    private final ModelMapper modelMapper;


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

    @SneakyThrows
    @Override
    public void processarCnae(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("O arquivo está vazio.");
        }

        try (InputStreamReader isr = new InputStreamReader(file.getInputStream(), StandardCharsets.ISO_8859_1);
             CSVReader csvReader = new CSVReaderBuilder(isr)
                     .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                     .build()) {

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                if (nextLine.length < 2) {
                    System.err.println("Linha inválida: " + Arrays.toString(nextLine));
                    continue;
                }
                try {
                    String subclasse = nextLine[0].trim();
                    String denominacao = nextLine[1].trim();
                    Cnae cnae = new Cnae(subclasse, denominacao);

                    outputPort.processarCnae(cnae);
                } catch (NumberFormatException e) {
                    System.err.println("Código inválido: " + nextLine[0]);
                }
            }
        } catch (IOException e) {
            // Trate a exceção de forma mais informativa
            throw new RuntimeException("Erro ao processar o arquivo: " + e.getMessage(), e);
        }
    }

    @Override
    public void salvarSubclasses() {
        outputPort.salvarSubclasses();
    }

    @Override
    public void addCnaesV2(Long idPessoa, String subclasse, List<CnaeRequest> cnaeRequestList) {
        var cnaes = cnaeRequestList.stream()
                .map(request -> modelMapper.map(request, Cnae.class))
                .toList();

        outputPort.addCnaesV2(idPessoa, subclasse, cnaes);
    }

}
