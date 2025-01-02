package com.rlti.hexagonal.pessoa.application.port.input;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.rlti.hexagonal.pessoa.adapters.input.request.ProfissaoFilter;
import com.rlti.hexagonal.pessoa.adapters.output.response.ProfissaoResponse;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.Profissao;
import com.rlti.hexagonal.pessoa.application.port.output.ProfissaoOutputPort;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ProfissaoUseCase implements ProfissaoInputPort {

    private final ProfissaoOutputPort profissaoService;

    public ProfissaoUseCase(ProfissaoOutputPort profissaoOutputPort) {
        this.profissaoService = profissaoOutputPort;
    }


    @SneakyThrows
    @Override
    public void processarProfissao(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("O arquivo está vazio.");
        }

        try (InputStreamReader isr = new InputStreamReader(file.getInputStream(), StandardCharsets.ISO_8859_1);
             CSVReader csvReader = new CSVReaderBuilder(isr)
                     .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                     .build()) {

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                // Verifica se a linha contém pelo menos dois elementos
                if (nextLine.length < 2) {
                    System.err.println("Linha inválida: " + Arrays.toString(nextLine));
                    continue; // Ignora linhas inválidas
                }

                try {
                    String codigo =nextLine[0].trim();
                    String titulo = nextLine[1].trim();

                    // Cria a profissão a partir dos dados lidos
                    Profissao profissao = new Profissao(codigo, titulo);

                    // Envia a profissão para o serviço processar (verificar duplicidade e salvar no banco)
                    profissaoService.processarProfissao(profissao);
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
    public List<ProfissaoResponse> getAllCnaesWithFilter(ProfissaoFilter params) {
        var profissoes = profissaoService.getAllProfissoesWithFilter(params);
        return ProfissaoResponse.convert(profissoes);

    }
}
