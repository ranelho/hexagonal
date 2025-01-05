package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.ContaBancariaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.PaisEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.*;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.PessoaJuridicaEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_PESSOA")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_seq_generator")
    @SequenceGenerator(name = "pessoa_seq_generator", sequenceName = "TB_PESSOA_ID_PESSOA_seq", allocationSize = 1)
    @Column(name = "ID_PESSOA")
    private Long idPessoa;

    @Column(name = "TP_PESSOA", columnDefinition = "TIPOPESSOA")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Basic(optional = false)
    private Tipo tipo;

    @Column(name = "NOM_PESSOA")
    private String nome;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaFisicaEntity pessoaFisica;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaJuridicaEntity pessoaJuridica;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EnderecoEntity> enderecos;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EmailEntity> emails;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TelefoneEntity> telefones;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private ContaBancariaEntity conta;

/*    public PessoaEntity(Pessoa pessoa,
                        PaisEntity pais,
                        OrientacaoSexualEntity orientacao,
                        IdentidadeGeneroEntity identidadeGenero, ProfissaoEntity profissao, EstadoCivilEntity estadoCivil) {
        inicializarCamposComuns(pessoa, pais);
        this.pessoaFisica = new PessoaFisicaEntity(pessoa.getPessoaFisica(), pais, orientacao, identidadeGenero, profissao, estadoCivil);
        this.pessoaFisica.setPessoa(this);
    }*/

    public PessoaEntity(Pessoa pessoa, PessoaFisicaEntity pessoaFisica, PaisEntity pais, ContaBancariaEntity conta) {
        setCommonFields(pessoa, pais);
        this.pessoaFisica = pessoaFisica;
        this.conta = conta;

        this.conta.setPessoa(this);
        this.pessoaFisica.setPessoa(this);
    }

    private void setCommonFields(Pessoa pessoa, PaisEntity pais) {
        this.idPessoa = pessoa.getIdPessoa();
        this.tipo = pessoa.getTipo();
        this.nome = pessoa.getNome().toUpperCase();
        this.createdAt = pessoa.getCreatedAt();
        this.telefones = pessoa.getTelefones().stream().map(telefone -> new TelefoneEntity(telefone, this)).toList();
        this.enderecos = pessoa.getEnderecos().stream().map(endereco -> new EnderecoEntity(endereco, pais, this)).toList();
        this.emails = pessoa.getEmails().stream().map(email -> new EmailEntity(email, this)).toList();
    }


}


