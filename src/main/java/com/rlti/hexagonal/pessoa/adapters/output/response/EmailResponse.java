package com.rlti.hexagonal.pessoa.adapters.output.response;


import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Email;

import java.util.List;

public record EmailResponse(
        Long idEmail,
        String email,
        String tipoEmail) {

    public EmailResponse(Email email) {
        this(
                email.getIdEmail(),
                email.getEmail(),
                email.getTipoEmail().toString()
        );
    }
    public static List<EmailResponse> converte(List<Email> emails) {
        return emails.stream()
                .map(EmailResponse::new)
                .toList();
    }
}
