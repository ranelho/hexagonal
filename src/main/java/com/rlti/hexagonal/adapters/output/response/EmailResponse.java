package com.rlti.hexagonal.adapters.output.response;


import com.rlti.hexagonal.application.core.domain.Email;
import lombok.Data;

import java.util.List;

@Data
public class EmailResponse {
    private Long idEmail;
    private String email;
    private String tipoEmail;

    public  EmailResponse(Email email){
        this.idEmail = email.getIdEmail();
        this.email = email.getEmail();
        this.tipoEmail = email.getTipoEmail().toString();
    }

    public static List<EmailResponse> converte(List<Email> emails) {
        return emails.stream().map(EmailResponse::new).toList();
    }
}
