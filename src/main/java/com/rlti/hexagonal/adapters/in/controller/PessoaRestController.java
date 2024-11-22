package com.rlti.hexagonal.adapters.in.controller;


import com.rlti.hexagonal.adapters.in.controller.request.PessoaRequest;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.ports.in.InsertPessoaInputPort;
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

    private final InsertPessoaInputPort insertPessoaInputPort;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Void> criarPessoa(@Valid @RequestBody PessoaRequest request){
        var pessoa = modelMapper.map(request, Pessoa.class);
        insertPessoaInputPort.criarPessoa(pessoa);
        return ResponseEntity.ok().build();
    }
}
