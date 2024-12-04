package com.rlti.hexagonal.pessoa.adapters.input.controller;

import com.rlti.hexagonal.pessoa.application.port.input.ProfissaoInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
