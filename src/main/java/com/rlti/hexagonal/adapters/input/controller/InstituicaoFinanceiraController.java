package com.rlti.hexagonal.adapters.input.controller;

import com.rlti.hexagonal.adapters.input.controller.request.AgenciaRequest;
import com.rlti.hexagonal.adapters.input.controller.request.BancoRequest;
import com.rlti.hexagonal.adapters.output.response.AgenciaResponse;
import com.rlti.hexagonal.adapters.output.response.BancoResponse;
import com.rlti.hexagonal.application.port.InstituicaoFinanceiraInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instituicao-financeira")
@RequiredArgsConstructor
public class InstituicaoFinanceiraController {

    private final InstituicaoFinanceiraInputPort instituicaoFinanceiraInputPort;

    @PostMapping("/banco")
    ResponseEntity<Void> criarBanco(@Valid @RequestBody BancoRequest request) {
        instituicaoFinanceiraInputPort.criarBanco(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/banco/{id}")
    ResponseEntity<Void> atualizarBanco(@PathVariable Long id, @Valid @RequestBody BancoRequest request) {
        instituicaoFinanceiraInputPort.atualizarBanco(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/banco")
    ResponseEntity<Page<BancoResponse>> listarBancos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        var bancos = instituicaoFinanceiraInputPort.listarBancos(page, size);
        return ResponseEntity.ok(bancos);
    }

    @GetMapping("/banco/{id}")
    ResponseEntity<BancoResponse> buscarBanco(@PathVariable Long id) {
        var banco = instituicaoFinanceiraInputPort.buscarBanco(id);
        return ResponseEntity.ok(banco);
    }

    @PostMapping("/banco/{id}/agencia")
    ResponseEntity<Void> criarAgencia(@PathVariable Long id, @Valid @RequestBody AgenciaRequest request) {
        instituicaoFinanceiraInputPort.criarAgencia(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/banco/agencia/{idAgencia}")
    ResponseEntity<Void> atualizarAgencia(@PathVariable Long idAgencia, @Valid @RequestBody AgenciaRequest request) {
        instituicaoFinanceiraInputPort.atualizarAgencia(idAgencia, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/banco/agencia/{idAgencia}")
    ResponseEntity<AgenciaResponse> buscarAgencia(@PathVariable Long idAgencia) {
        var agencia = instituicaoFinanceiraInputPort.buscarAgencia(idAgencia);
        return ResponseEntity.ok(agencia);
    }
}
