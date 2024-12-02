package com.rlti.hexagonal.adapters.input.controller;


import com.rlti.hexagonal.adapters.input.controller.request.PessoaRequest;
import com.rlti.hexagonal.adapters.output.response.*;
import com.rlti.hexagonal.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.application.port.input.PessoaInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<PessoaResponse>> listarPessoas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Page<PessoaResponse> pessoas = pessoaInputPort.listarPessoas(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

/*    @GetMapping("/{idPessoa}")
    public ResponseEntity<PessoaResponse> buscarPessoa(@PathVariable Long idPessoa){
        PessoaResponse pessoa = pessoaInputPort.buscarPessoa(idPessoa);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }*/

    @GetMapping("/{idPessoa}")
    public ResponseEntity<?> buscarPessoa(@PathVariable Long idPessoa) {
        var pessoa = pessoaInputPort.buscarPessoa(idPessoa);

        if (pessoa.getTipo() == Tipo.FISICA) {
            var pessoaFisica = pessoaInputPort.buscarPessoaFisica(idPessoa);
            return ResponseEntity.ok(pessoaFisica);
        } else if (pessoa.getTipo() == Tipo.JURIDICA) {
            var pessoaJuridica = pessoaInputPort.buscarPessoaJuridica(idPessoa);
            return ResponseEntity.ok(pessoaJuridica);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tipo de pessoa inválido ou não suportado.");
        }
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
}
