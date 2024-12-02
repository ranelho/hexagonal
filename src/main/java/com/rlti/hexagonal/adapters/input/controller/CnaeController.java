package com.rlti.hexagonal.adapters.input.controller;

import com.rlti.hexagonal.adapters.input.controller.request.CnaeRequest;
import com.rlti.hexagonal.adapters.output.response.CnaeResponse;
import com.rlti.hexagonal.application.port.input.CnaeInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cnae")
@RequiredArgsConstructor
public class CnaeController {

    private final CnaeInputPort cnaeInputPort;

    @GetMapping("/all")
    ResponseEntity<List<CnaeResponse>> getAllCnaes() {
        var cnae = cnaeInputPort.findAll();
        return ResponseEntity.ok(cnae);
    }

    @PostMapping("/{idPessoa}/cnaes")
    public ResponseEntity<Void> addCnae(
            @PathVariable Long idPessoa,
            @Valid @RequestBody List<CnaeRequest> cnaeRequestList) {
        cnaeInputPort.addCnaes(idPessoa, cnaeRequestList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
