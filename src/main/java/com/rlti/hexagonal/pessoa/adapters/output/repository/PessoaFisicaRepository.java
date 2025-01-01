package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.PessoaFisicaEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, Long> {
    Optional<PessoaFisicaEntity> findByCpf(String cpf);

    @Query("SELECT pf FROM PessoaFisicaEntity pf " +
            "INNER JOIN PessoaEntity p ON p.idPessoa = pf.idPessoaFisica " +
            "WHERE (:nome IS NULL OR UPPER(p.nome) LIKE %:nome%) AND " +
            "(:dataNascimento IS NULL OR TO_CHAR(pf.dataNascimento, 'yyyy-MM-dd') = :dataNascimento) AND " +
            "(:sexo IS NULL OR pf.sexo = :sexo) AND " +
            "(:cpf IS NULL OR pf.cpf = :cpf)")
    Page<PessoaFisicaEntity> findWithFilters(
            @Param("nome") String nome,
            @Param("dataNascimento") String dataNascimento,
            @Param("sexo") Sexo sexo,
            @Param("cpf") String cpf,
            Pageable pageable);
}
