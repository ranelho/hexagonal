package com.rlti.hexagonal.adapters.input.controller;


import com.rlti.hexagonal.adapters.input.controller.request.PessoaRequest;
import com.rlti.hexagonal.application.port.input.PessoaInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pessoas")
@RequiredArgsConstructor
public class PessoaRestController {

    private final PessoaInputPort pessoaInputPort;

    @PostMapping
    public ResponseEntity<Void> criarPessoa(@Valid @RequestBody PessoaRequest request){
        pessoaInputPort.criarPessoa(request);
        return ResponseEntity.ok().build();
    }
}
