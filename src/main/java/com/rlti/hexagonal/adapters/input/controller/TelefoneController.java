package com.rlti.hexagonal.adapters.input.controller;

import com.rlti.hexagonal.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.adapters.output.response.TelefoneResponse;
import com.rlti.hexagonal.application.port.input.TelefoneInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/telefones")
@RequiredArgsConstructor
public class TelefoneController {

    private final TelefoneInputPort telefoneInputPort;

    @PostMapping("/{idPessoaFisica}")
    public ResponseEntity<Void> criarTelefone(@PathVariable Long idPessoaFisica, @Valid @RequestBody List<TelefoneRequest> request){
        telefoneInputPort.criarTelefone(idPessoaFisica, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{idPessoaFisica}")
    public ResponseEntity<List<TelefoneResponse>> listarTelefones(@PathVariable Long idPessoaFisica){
        var telefones= telefoneInputPort.findAllById(idPessoaFisica);
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
