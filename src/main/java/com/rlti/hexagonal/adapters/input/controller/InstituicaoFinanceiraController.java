package com.rlti.hexagonal.adapters.input.controller;

import com.rlti.hexagonal.adapters.input.controller.request.BancoRequest;
import com.rlti.hexagonal.application.port.InstituicaoFinanceiraInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instituicao-financeira")
@RequiredArgsConstructor
public class InstituicaoFinanceiraController {

    private final InstituicaoFinanceiraInputPort instituicaoFinanceiraInputPort;

    @PostMapping
    ResponseEntity<Void> criarBanco(@Valid @RequestBody BancoRequest request) {
        instituicaoFinanceiraInputPort.criarBanco(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> atualizarBanco(@PathVariable Long id, @Valid @RequestBody BancoRequest request) {
        instituicaoFinanceiraInputPort.atualizarBanco(id, request);
        return ResponseEntity.ok().build();
    }
}
