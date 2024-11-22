package com.rlti.hexagonal.adapters.out;

import com.rlti.hexagonal.adapters.out.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
}
