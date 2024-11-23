package com.rlti.hexagonal.adapters.input.controller;

import com.rlti.hexagonal.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.application.port.input.TelefoneInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
