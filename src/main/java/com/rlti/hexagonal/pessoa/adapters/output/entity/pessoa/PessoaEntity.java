package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.PaisEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.*;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.PessoaJuridicaEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_PESSOA")
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

    public PessoaEntity(Pessoa pessoa,
                        PaisEntity pais,
                        OrientacaoSexualEntity orientacao,
                        IdentidadeGeneroEntity identidadeGenero, ProfissaoEntity profissao, EstadoCivilEntity estadoCivil) {
        inicializarCamposComuns(pessoa);
        this.pessoaFisica = new PessoaFisicaEntity(pessoa.getPessoaFisica(), pais, orientacao, identidadeGenero, profissao, estadoCivil);
        this.pessoaFisica.setPessoa(this);
    }

    public PessoaEntity(Pessoa pessoa) {
        inicializarCamposComuns(pessoa);
        this.pessoaJuridica = new PessoaJuridicaEntity(pessoa.getPessoaJuridica());
        this.pessoaJuridica.setPessoa(this);
    }

    private void inicializarCamposComuns(Pessoa pessoa) {
        this.idPessoa = pessoa.getIdPessoa();
        this.tipo = pessoa.getTipo();
        this.nome = pessoa.getNome().toUpperCase();
        this.createdAt = pessoa.getCreatedAt();
        this.telefones = pessoa.getTelefones().stream().map(TelefoneEntity::new).toList();
        this.enderecos = pessoa.getEnderecos().stream().map(EnderecoEntity::new).toList();
        this.emails = pessoa.getEmails().stream().map(EmailEntity::new).toList();
    }

    public void update(Pessoa pessoa) {
        updateCampoComuns(pessoa);
        if(pessoa.getTipo() == Tipo.FISICA){
            this.pessoaFisica = new PessoaFisicaEntity(pessoa.getPessoaFisica());
        }else if (pessoa.getTipo() == Tipo.JURIDICA){
            this.pessoaJuridica = new PessoaJuridicaEntity(pessoa.getPessoaJuridica());
        }
    }

    private void updateCampoComuns(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.telefones = pessoa.getTelefones().stream().map(TelefoneEntity::new).toList();
        this.enderecos = pessoa.getEnderecos().stream().map(EnderecoEntity::new).toList();
        this.emails = pessoa.getEmails().stream().map(EmailEntity::new).toList();
    }
}


