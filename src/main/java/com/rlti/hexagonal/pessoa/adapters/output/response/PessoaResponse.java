package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.core.domain.Pessoa;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PessoaResponse {
    private Long idPessoa;
    private String nome;
    private Tipo tipo;
    private String cpfOrCnpj;

    public PessoaResponse(Pessoa pessoa) {
        this.idPessoa = pessoa.getIdPessoa();
        this.nome = pessoa.getNome();
        this.tipo = pessoa.getTipo();
        if (pessoa.getTipo() == Tipo.FISICA) {
            this.cpfOrCnpj = pessoa.getPessoaFisica().getCpf();
        } else {
            this.cpfOrCnpj = pessoa.getPessoaJuridica().getCnpj();
        }
    }

    public static Page<PessoaResponse> converte(Page<Pessoa> pessoas) {
        return pessoas.map(PessoaResponse::new);
    }
}
