package com.rlti.hexagonal.adapters.input.controller;

import com.rlti.hexagonal.application.core.domain.Endereco;
import com.rlti.hexagonal.application.port.input.BuscaEnderecoInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final BuscaEnderecoInputPort buscaEnderecoInputPort;

    @GetMapping
    public ResponseEntity<Endereco> buscarEndereco(@RequestParam String cep) {
        var endereco = buscaEnderecoInputPort.buscarEndereco(cep);
        return ResponseEntity.ok(endereco);
    }
}
