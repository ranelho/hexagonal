package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.EstadoCivil;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.GrauInstrucao;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PessoaFisicaUpdateRequest {
    private String nomeSocial;
    private String resideExterior;
    @NotNull private LocalDate dataObito;
    @NotNull private Sexo sexo;
    private EstadoCivil estadoCivil;
    private BigDecimal rendaMensal;
    private String indicadorFacta;
    private String indicadoCrs;
    private String indicadorPpe;
    private String especificacaoPpe;
    private GrauInstrucao grauInstrucao;
}
