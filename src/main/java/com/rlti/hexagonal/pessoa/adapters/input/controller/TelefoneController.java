package com.rlti.hexagonal.pessoa.adapters.input.controller;

import com.rlti.hexagonal.pessoa.adapters.input.request.TelefoneRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.TelefoneResponse;
import com.rlti.hexagonal.pessoa.application.port.input.TelefoneInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/telefones")
@RequiredArgsConstructor
public class TelefoneController {

    private final TelefoneInputPort telefoneInputPort;

    @PostMapping("/{idPessoa}")
    public ResponseEntity<Void> criarTelefone(@PathVariable Long idPessoa, @Valid @RequestBody List<TelefoneRequest> request){
        telefoneInputPort.criarTelefone(idPessoa, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all/{idPessoa}")
    public ResponseEntity<List<TelefoneResponse>> listarTelefones(@PathVariable Long idPessoa){
        var telefones= telefoneInputPort.findAllById(idPessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarTelefone(@PathVariable Long id, @Valid @RequestBody TelefoneRequest request){
        telefoneInputPort.updateTelefone(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable Long id){
        telefoneInputPort.deleteTelefone(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
