package com.rlti.hexagonal.pessoa.adapters.input.controller;

import com.rlti.hexagonal.pessoa.adapters.input.request.EnderecoRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.EnderecoResponse;
import com.rlti.hexagonal.pessoa.application.port.input.EnderecoInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoInputPort enderecoInputPort;

    @GetMapping
    public ResponseEntity<EnderecoResponse> buscarEndereco(@RequestParam String cep) {
        var endereco = enderecoInputPort.buscarEndereco(cep);
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<Void> salvarEndereco(@PathVariable Long idPessoa, @RequestBody EnderecoRequest endereco) {
        enderecoInputPort.salvarEndereco(idPessoa, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/municipios/{uf}")
    public List<String> getMunicipios(@PathVariable("uf") String uf) {
        return enderecoInputPort.buscarNomesMunicipiosPorUF(uf);
    }
}
