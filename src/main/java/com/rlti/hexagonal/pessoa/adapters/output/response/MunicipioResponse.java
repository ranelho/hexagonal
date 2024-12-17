package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MunicipioResponse(String nome) {}
