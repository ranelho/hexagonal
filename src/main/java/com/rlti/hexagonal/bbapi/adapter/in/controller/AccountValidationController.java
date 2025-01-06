package com.rlti.hexagonal.bbapi.adapter.in.controller;

import com.rlti.hexagonal.bbapi.application.port.in.ValidateAccountInputPort;
import com.rlti.hexagonal.bbapi.adapter.out.response.ValidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validate")
@RequiredArgsConstructor
public class AccountValidationController {

    private final ValidateAccountInputPort validateAccountInputPort;

    @GetMapping
    public ValidateResponse validateAccount(@RequestParam String agencia,
                                            @RequestParam String conta,
                                            @RequestParam String cpfCnpj) {
        // Valida o campo cpfCnpj
        validateCpfCnpj(cpfCnpj);

        // Realiza a validação e formatação dos parâmetros
        String formattedAgencia = formatAccountOrAgency(agencia);
        String formattedConta = formatAccountOrAgency(conta);

        // Chama o método de validação com os valores formatados
        return validateAccountInputPort.validateAccount(formattedAgencia, formattedConta, cpfCnpj);
    }

    private String formatAccountOrAgency(String input) {
        // Remove pontos
        input = input.replace(".", "");

        // Remove o que está após "-" ou espaço em branco
        if (input.contains("-")) {
            input = input.substring(0, input.indexOf("-"));
        } else if (input.contains(" ")) {
            input = input.substring(0, input.indexOf(" "));
        }

        // Retorna o dado formatado
        return input;
    }

    private void validateCpfCnpj(String cpfCnpj) {
        // Verifica se o valor contém ".", "-", ou "/"
        if (cpfCnpj.contains(".") || cpfCnpj.contains("-") || cpfCnpj.contains("/")) {
            throw new IllegalArgumentException("O campo cpfCnpj não deve conter '.', '-' ou '/'.");
        }
    }
}
