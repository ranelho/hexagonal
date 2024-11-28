package com.rlti.hexagonal.adapters.output.repository.entity;

import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.core.domain.enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PESSOA")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_seq_generator")
    @SequenceGenerator(name="pessoa_seq_generator", sequenceName = "pessoa_sequence", allocationSize=1)
    @Column(name = "id_pessoa")
    private Long idPessoa;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private Tipo tipo;

    @Column(nullable = false)
    private String nome;

    private String resideExterior = "N";

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaFisicaEntity pessoaFisica;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaJuridicaEntity pessoaJuridica;


/*    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<EnderecoEntity> enderecos;*/

    public PessoaEntity(Pessoa pessoa,
                        PaisEntity pais,
                        OrientacaoSexualEntity orientacao,
                        IdentidadeGeneroEntity identidadeGenero, ProfissaoEntity profissao) {
        this.idPessoa = pessoa.getIdPessoa();
        this.tipo = pessoa.getTipo();
        this.nome = pessoa.getNome().toUpperCase();
        this.resideExterior = pessoa.getResideExterior().toUpperCase();
        this.createdAt = pessoa.getCreatedAt();
        this.pessoaFisica = new PessoaFisicaEntity(pessoa.getPessoaFisica(), pais, orientacao, identidadeGenero, profissao);
        this.pessoaFisica.setPessoa(this);

    }

    public PessoaEntity(Pessoa pessoa) {
        this.idPessoa = pessoa.getIdPessoa();
        this.tipo = pessoa.getTipo();
        this.nome = pessoa.getNome().toUpperCase();
        this.resideExterior = pessoa.getResideExterior().toUpperCase();
        this.createdAt = pessoa.getCreatedAt();
        this.pessoaJuridica = new PessoaJuridicaEntity(pessoa.getPessoaJuridica());
        this.pessoaJuridica.setPessoa(this);
    }
}


