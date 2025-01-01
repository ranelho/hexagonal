package com.rlti.hexagonal.pessoa.adapters.input.controller;

import com.rlti.hexagonal.pessoa.adapters.input.request.AgenciaRequest;
import com.rlti.hexagonal.pessoa.adapters.input.request.BancoRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.AgenciaResponse;
import com.rlti.hexagonal.pessoa.adapters.output.response.BancoResponse;
import com.rlti.hexagonal.pessoa.application.port.input.InstituicaoFinanceiraInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/instituicao-financeira")
@RequiredArgsConstructor
public class InstituicaoFinanceiraController {

    private final InstituicaoFinanceiraInputPort instituicaoFinanceiraInputPort;

    @PostMapping("/banco")
    ResponseEntity<Void> criarBanco(@Valid @RequestBody BancoRequest request) {
        instituicaoFinanceiraInputPort.criarBanco(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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

    @GetMapping("/banco/{idBanco}/agencias")
    ResponseEntity<Page<AgenciaResponse>> listarAgencias(@PathVariable Long idBanco,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "12") int size) {
        var agencias = instituicaoFinanceiraInputPort.listarAgenciasPorBanco(idBanco, page, size);
        return ResponseEntity.ok(agencias);
    }

    @PostMapping("/import")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        try {
            instituicaoFinanceiraInputPort.importExcel(file);
            return ResponseEntity.status(HttpStatus.OK).body("Importação realizada com sucesso.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao importar o arquivo.");
        }
    }
}
