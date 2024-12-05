package com.rlti.hexagonal.pessoa.adapters.input.controller;


import com.rlti.hexagonal.pessoa.adapters.input.controller.request.EmailRequest;
import com.rlti.hexagonal.pessoa.adapters.input.controller.request.PessoaRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.*;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.port.input.PessoaInputPort;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/api/v1/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaInputPort pessoaInputPort;

    private Map<Tipo, Function<Long, Object>> tipoPessoaHandler;

    @PostConstruct
    private void initTipoPessoaHandler() {
        tipoPessoaHandler = Map.of(
                Tipo.FISICA, pessoaInputPort::buscarPessoaFisica,
                Tipo.JURIDICA, pessoaInputPort::buscarPessoaJuridica
        );
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<?> buscarPessoa(@PathVariable Long idPessoa) {
        var pessoa = pessoaInputPort.buscarPessoa(idPessoa);
        var handler = tipoPessoaHandler.get(pessoa.getTipo());
        var detalhes = handler.apply(idPessoa);
        return ResponseEntity.ok(detalhes);
    }

    @PostMapping
    public ResponseEntity<Void> criarPessoa(@RequestBody @Valid PessoaRequest request){
        pessoaInputPort.criarPessoa(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<PessoaResponse>> listarPessoas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Page<PessoaResponse> pessoas = pessoaInputPort.listarPessoas(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    @GetMapping("/fisica/{idPessoa}")
    public ResponseEntity<PessoaFisicaDetalheResponse> buscarPessoaFisica(@PathVariable Long idPessoa){
        PessoaFisicaDetalheResponse pessoa = pessoaInputPort.buscarPessoaFisica(idPessoa);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/fisica/all")
    public ResponseEntity<Page<PessoaFisicaResponse>> listarPessoasFisica(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Page<PessoaFisicaResponse> pessoasFisicas = pessoaInputPort.listarPessoasFisica(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(pessoasFisicas);
    }

    @GetMapping("/juridica/{idPessoa}")
    public ResponseEntity<PessoaJuridicaDetalheResponse> buscarPessoaJuridica(@PathVariable Long idPessoa){
        PessoaJuridicaDetalheResponse pessoa = pessoaInputPort.buscarPessoaJuridica(idPessoa);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/juridica/all")
    public ResponseEntity<Page<PessoaJuridicaResponse>> listarPessoasJuridicas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Page<PessoaJuridicaResponse> pessoasJuridicas = pessoaInputPort.listarPessoasJuridicas(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(pessoasJuridicas);
    }

    @PostMapping("/email/{idPessoa}")
    public ResponseEntity<Void> criarEmail(@PathVariable Long idPessoa, @RequestBody @Valid EmailRequest request){
        pessoaInputPort.criarEmail(idPessoa, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
