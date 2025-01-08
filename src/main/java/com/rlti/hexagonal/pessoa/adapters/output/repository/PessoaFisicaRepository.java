package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.PessoaFisicaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, Long> {
    Optional<PessoaFisicaEntity> findByCpf(String cpf);

    @Query("SELECT pf FROM PessoaFisicaEntity pf " +
            "INNER JOIN PessoaEntity p ON p.idPessoa = pf.idPessoaFisica " +
            "LEFT JOIN PaisEntity pa ON pa.idPais = pf.pais.idPais " +
            "WHERE (:nome IS NULL OR UPPER(p.nome) LIKE %:nome%) AND " +
            "(:dataNascimento IS NULL OR TO_CHAR(pf.dataNascimento, 'yyyy-MM-dd') = :dataNascimento) AND " +
            "(:sexo IS NULL OR CAST(pf.sexo AS string) = :sexo) AND " +
            "(:cpf IS NULL OR pf.cpf = :cpf)")
    Page<PessoaFisicaEntity> findWithFilters(
            @Param("nome") String nome,
            @Param("dataNascimento") String dataNascimento,
            @Param("sexo") String sexo,
            @Param("cpf") String cpf,
            Pageable pageable);

    @Query("SELECT p FROM PessoaFisicaEntity p WHERE CAST(p.sexo AS string) = :sexo")
    Page<PessoaFisicaEntity> findBySexo(@Param("sexo") String sexo, Pageable pageable);

    //2 opccao mais rapido
    @Query("SELECT pf.idPessoaFisica, p.nome, pf.dataNascimento, pf.sexo, pf.cpf, pa.nacionalidade, e.email " +
            "FROM PessoaFisicaEntity pf " +
            "INNER JOIN PessoaEntity p ON p.idPessoa = pf.idPessoaFisica " +
            "LEFT JOIN PaisEntity pa ON pa.idPais = pf.pais.idPais " +
            "LEFT JOIN EmailEntity e ON e.pessoa.idPessoa = p.idPessoa AND e.tipoEmail = 'PRINCIPAL' " +
            "WHERE (:nome IS NULL OR UPPER(p.nome) LIKE %:nome%) AND " +
            "(:dataNascimento IS NULL OR TO_CHAR(pf.dataNascimento, 'yyyy-MM-dd') = :dataNascimento) AND " +
            "(:sexo IS NULL OR CAST(pf.sexo AS string) = :sexo) AND " +
            "(:cpf IS NULL OR pf.cpf = :cpf)")
    Page<Object[]> findWithFilters2(
            @Param("nome") String nome,
            @Param("dataNascimento") String dataNascimento,
            @Param("sexo") String sexo,
            @Param("cpf") String cpf,
            Pageable pageable);
}
