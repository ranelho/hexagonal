package com.rlti.hexagonal.pessoa.application.core.domain.enums;

public class EstadoCivil {
    Long idEstadoCivil;
    String nomeEstadoCivil;

    public EstadoCivil() {
    }
    public EstadoCivil(Long idEstadoCivil, String nomeEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
        this.nomeEstadoCivil = nomeEstadoCivil;
    }

    public EstadoCivil(Long idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Long getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(Long idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getNomeEstadoCivil() {
        return nomeEstadoCivil;
    }

    public void setNomeEstadoCivil(String nomeEstadoCivil) {
        this.nomeEstadoCivil = nomeEstadoCivil;
    }
}
