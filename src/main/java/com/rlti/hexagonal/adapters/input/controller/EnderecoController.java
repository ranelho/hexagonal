package com.rlti.hexagonal.adapters.input.controller;

import com.rlti.hexagonal.adapters.input.controller.request.EnderecoRequest;
import com.rlti.hexagonal.application.core.domain.Endereco;
import com.rlti.hexagonal.application.port.input.EnderecoInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoInputPort enderecoInputPort;

    @GetMapping
    public ResponseEntity<Endereco> buscarEndereco(@RequestParam String cep) {
        var endereco = enderecoInputPort.buscarEndereco(cep);
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<Void> salvarEndereco(@PathVariable Long idPessoa, @RequestBody EnderecoRequest endereco) {
        enderecoInputPort.salvarEndereco(idPessoa, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
