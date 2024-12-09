package com.rlti.hexagonal.pessoa.adapters.input.controller;

import com.rlti.hexagonal.pessoa.adapters.input.request.CnaeRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.CnaeResponse;
import com.rlti.hexagonal.pessoa.application.port.input.CnaeInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/v2/{idPessoa}/{subclasse}cnaes")
    public ResponseEntity<Void> addCnaeV2(
            @PathVariable Long idPessoa,
            @PathVariable String subclasse,
            @Valid @RequestBody List<CnaeRequest> cnaeRequestList) {
        cnaeInputPort.addCnaesV2(idPessoa, subclasse, cnaeRequestList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/ler-csv")
    public ResponseEntity<String> processarProfissao(@RequestParam("file") MultipartFile file) {
        try {
            cnaeInputPort.processarCnae(file);
            return ResponseEntity.ok("Arquivo processado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo");
        }
    }

    @GetMapping("/salvar-subclasses")
    public String salvarSubclasses() {
        try {
            cnaeInputPort.salvarSubclasses();
            return "Subclasses salvas com sucesso!";
        } catch (Exception e) {
            return "Erro ao salvar subclasses: " + e.getMessage();
        }
    }
}
