package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long>  {

    @Query("SELECT p FROM PESSOA p " +
            "INNER JOIN PESSOA_FISICA pf on pf.idPessoa = p.idPessoa " +
            "WHERE (:nome IS NULL OR UPPER(p.nome) LIKE %:nome% ) AND " +
            "(:dataNascimento IS NULL OR TO_CHAR(pf.dataNascimento, 'yyyy-MM-dd') = :dataNascimento) AND" +
            "(:sexo IS NULL OR pf.sexo = :sexo) AND " +
            "(:cpf IS NULL OR pf.cpf = :cpf)  ")
    Page<PessoaEntity> findWithFilters(
            @Param("nome") String nome,
            @Param("dataNascimento") String dataNascimento,
            @Param("sexo") String sexo,
            @Param("cpf") String cpf,
            Pageable pageable
    );
}
