package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import org.springframework.data.domain.Page;

public record PessoaResponse(
        Long idPessoa,
        String nome,
        Tipo tipo,
        String cpfOrCnpj
) {
    public PessoaResponse(Pessoa pessoa) {
        this(
                pessoa.getIdPessoa(),
                pessoa.getNome(),
                pessoa.getTipo(),
                pessoa.getTipo() == Tipo.FISICA
                        ? pessoa.getPessoaFisica().getCpf()
                        : pessoa.getPessoaJuridica().getCnpj()
        );
    }

    public static Page<PessoaResponse> converte(Page<Pessoa> pessoas) {
        return pessoas.map(PessoaResponse::new);
    }
}
