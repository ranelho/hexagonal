package com.rlti.hexagonal.adapters.input.controller;


import com.rlti.hexagonal.adapters.input.controller.request.PessoaRequest;
import com.rlti.hexagonal.adapters.output.response.PessoaResponse;
import com.rlti.hexagonal.application.port.input.PessoaInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaInputPort pessoaInputPort;

    @PostMapping
    public ResponseEntity<Void> criarPessoa(@Valid @RequestBody PessoaRequest request){
        pessoaInputPort.criarPessoa(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<PessoaResponse>> listarPessoas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Page<PessoaResponse> pessoas = pessoaInputPort.listarPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }
}
