package com.rlti.hexagonal.adapters.in.controller;


import com.rlti.hexagonal.adapters.in.controller.request.PessoaRequest;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.ports.in.PessoaInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Void> criarPessoa(@Valid @RequestBody PessoaRequest request){
        pessoaInputPort.criarPessoa(request);
        return ResponseEntity.ok().build();
    }
}
