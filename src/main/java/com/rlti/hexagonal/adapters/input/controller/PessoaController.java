package com.rlti.hexagonal.adapters.input.controller;


import com.rlti.hexagonal.adapters.input.controller.request.PessoaFisicaRequest;
import com.rlti.hexagonal.adapters.input.controller.request.PessoaRequest;
import com.rlti.hexagonal.adapters.output.response.PessoaFisicaDetalheResponse;
import com.rlti.hexagonal.adapters.output.response.PessoaFisicaResponse;
import com.rlti.hexagonal.adapters.output.response.PessoaJuridicaDetalheResponse;
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
    public ResponseEntity<Void> criarPessoa(@RequestBody @Valid PessoaRequest request){
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

    @GetMapping("/fisica")
    public ResponseEntity<Page<PessoaFisicaResponse>> listarPessoasFisica(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Page<PessoaFisicaResponse> pessoasFisica = pessoaInputPort.listarPessoasFisica(page, size);
        return ResponseEntity.ok(pessoasFisica);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPessoa(@PathVariable Long id){
        PessoaResponse pessoa = pessoaInputPort.buscarPessoa(id);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/pessoa-fisica/{id}")
    public ResponseEntity<PessoaFisicaDetalheResponse> buscarPessoaFisica(@PathVariable Long id){
        PessoaFisicaDetalheResponse pessoa = pessoaInputPort.buscarPessoaFisica(id);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/pessoa-juridica/{idPessoa}")
    public ResponseEntity<PessoaJuridicaDetalheResponse> buscarPessoaJuridica(@PathVariable Long idPessoa){
        PessoaJuridicaDetalheResponse pessoa = pessoaInputPort.buscarPessoaJuridica(idPessoa);
        return ResponseEntity.ok(pessoa);
    }
}
