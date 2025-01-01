package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_DEPENDENTE")
public class DependenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_DEPENDENTE_IR")
    private Long idDependenteIr;

    @Column(name = "NOME_DEPENDENTE_IR")
    private String nomeDependente;

    @Column(name = "GRAU_PARENTESCO")
    private String grauParentesco;
    @CPF @Column(name = "CPF_DEPENDENTE")
    private String cpfDependente;

    @Column(name = "DATA_NASCIMENTO_DEPENDENTE")
    private LocalDate dataNascimentoDependente;

    @Column(name = "DEPENDENTE")
    private String dependente;
}
