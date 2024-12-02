package com.rlti.hexagonal.adapters.input.controller;

import com.rlti.hexagonal.adapters.input.controller.request.ContaBancariaRequest;
import com.rlti.hexagonal.application.port.input.ContaBancariaInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/conta-bancaria")
@RestController
@RequiredArgsConstructor
public class ContaBancariaController {

    private final ContaBancariaInputPort contaBancariaInputPort;

    @PostMapping("/{idPessoa}")
    public ResponseEntity<Void> novaContaBancaria(@PathVariable Long idPessoa, @Valid @RequestBody ContaBancariaRequest request) {
        contaBancariaInputPort.novaContaBancaria(idPessoa, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
