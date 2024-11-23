package com.rlti.hexagonal.adapters.input.controller;

import com.rlti.hexagonal.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.adapters.output.response.TelefoneResponse;
import com.rlti.hexagonal.application.core.domain.Telefone;
import com.rlti.hexagonal.application.port.input.TelefoneInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/telefones")
@RequiredArgsConstructor
public class TelefoneController {

    private final TelefoneInputPort telefoneInputPort;

    @PostMapping("/{cpf}")
    public ResponseEntity<Void> criarTelefone(@PathVariable @CPF String cpf, @Valid @RequestBody TelefoneRequest request){
        telefoneInputPort.criarTelefone(cpf, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{cpf}")
    public ResponseEntity<List<TelefoneResponse>> listarTelefones(@PathVariable @CPF String cpf){
        var telefones= telefoneInputPort.findAllByCpf(cpf);
        return ResponseEntity.ok().body(telefones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarTelefone(@PathVariable Long id, @Valid @RequestBody TelefoneRequest request){
        telefoneInputPort.updateTelefone(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable Long id){
        telefoneInputPort.deleteTelefone(id);
        return ResponseEntity.noContent().build();
    }
}
