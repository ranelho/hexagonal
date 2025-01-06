package com.rlti.hexagonal.pessoa.adapters.input.controller;


import com.rlti.hexagonal.pessoa.adapters.input.request.*;
import com.rlti.hexagonal.pessoa.adapters.output.response.*;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.port.input.PessoaInputPort;
import com.rlti.hexagonal.util.PageableUtil;
import com.rlti.hexagonal.util.SortFieldResolver;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
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
        var handler = tipoPessoaHandler.get(pessoa.tipo());
        var detalhes = handler.apply(idPessoa);
        return ResponseEntity.ok(detalhes);
    }

    /*@PostMapping
    public ResponseEntity<Void> criarPessoa(@RequestBody @Valid PessoaRequest request) {
        Long idSalvo = pessoaInputPort.criarPessoa(request);
        URI location = URI.create(String.format("/pessoas/%d", idSalvo));
        return ResponseEntity.created(location).build();
    }*/

    @PostMapping("/fisica")
    public ResponseEntity<Void> criarPessoaFisica(@RequestBody @Valid PessoaFisicaRequest request) {
        Long idSalvo = pessoaInputPort.criarPessoaFisica(request);
        URI location = URI.create(String.format("/pessoa-fisica/%d", idSalvo));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Page<PessoaResponse>> listarPessoas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Page<PessoaResponse> pessoas = pessoaInputPort.listarPessoas(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    @GetMapping("/fisica/{idPessoa}")
    public ResponseEntity<PessoaFisicaDetalheResponse> buscarPessoaFisica(@PathVariable Long idPessoa) {
        PessoaFisicaDetalheResponse pessoa = pessoaInputPort.buscarPessoaFisica(idPessoa);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/fisica/all")
    public ResponseEntity<Page<PessoaFisicaResponse>> listarPessoasFisica(
            @ModelAttribute @Valid PaginationRequest paginationRequest) {
        if (SortFieldResolver.isValidSortField("pessoaFisica", paginationRequest.getSortBy())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O campo de ordenação '" + paginationRequest.getSortBy() + "' não é válido para Pessoa Física."
            );
        }

        Pageable pageable = PageableUtil.getPageable(paginationRequest, "pessoaFisica");
        Page<PessoaFisicaResponse> pessoasFisicas = pessoaInputPort.listarPessoasFisica(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(pessoasFisicas);
    }

    @GetMapping("/fisica/all-with-filter")
    public ResponseEntity<Page<PessoaFisicaResponse>> listPessoasFisicasWithFilter(
            @ParameterObject Pageable pageable,
            @ParameterObject PessoaFilterParam params) {
        Page<PessoaFisicaResponse> pessoasFisicas = pessoaInputPort.listPessoasFisicasWithFilter(pageable, params);
        return ResponseEntity.status(HttpStatus.OK).body(pessoasFisicas);
    }

    @GetMapping("/juridica/{idPessoa}")
    public ResponseEntity<PessoaJuridicaDetalheResponse> buscarPessoaJuridica(@PathVariable Long idPessoa) {
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
    public ResponseEntity<Void> criarEmail(@PathVariable Long idPessoa, @RequestBody @Valid EmailRequest request) {
        pessoaInputPort.criarEmail(idPessoa, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/pessoa-fisica/{idPessoa}")
    public ResponseEntity<Void> atualizarPessoa(@PathVariable Long idPessoa, @RequestBody @Valid PessoaFisicaUpdateRequest request) {
        pessoaInputPort.atualizarPessoa(idPessoa, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
