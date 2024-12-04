package com.rlti.hexagonal.pessoa.adapters.output.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "DEPENDENTE_IR")
public class DependenteIrEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idDependenteIr;
    private String nomeDependenteIr;
    private String grauParentesco;
    @CPF
    private String cpfDependenteIr;
    private LocalDate dataNascimentoDependenteIr;
    private String dependenteIr;
}
