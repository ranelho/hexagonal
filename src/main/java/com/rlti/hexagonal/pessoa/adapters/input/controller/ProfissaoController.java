package com.rlti.hexagonal.pessoa.adapters.input.controller;

import com.rlti.hexagonal.pessoa.adapters.input.request.ProfissaoFilter;
import com.rlti.hexagonal.pessoa.adapters.output.response.ProfissaoResponse;
import com.rlti.hexagonal.pessoa.application.port.input.ProfissaoInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/profissao")
@RequiredArgsConstructor
@Slf4j
public class ProfissaoController {

    private final ProfissaoInputPort profissaoInputPort;

    @PostMapping("/ler-csv")
    public ResponseEntity<String> processarProfissao(@RequestParam("file") MultipartFile file) {
        // Lógica para processar o arquivo CSV e salvar os dados no banco
        try {
            // Exemplo de leitura do arquivo CSV
            profissaoInputPort.processarProfissao(file);
            return ResponseEntity.ok("Arquivo processado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo");
        }
    }

    @GetMapping("/all-with-filter")
    ResponseEntity<List<ProfissaoResponse>> getAllProfissoesWithFilter(@ParameterObject ProfissaoFilter params) {
        var profissoes = profissaoInputPort.getAllCnaesWithFilter(params);
        return ResponseEntity.ok(profissoes);
    }

}
