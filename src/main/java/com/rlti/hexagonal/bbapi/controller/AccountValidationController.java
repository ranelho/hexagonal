package com.rlti.hexagonal.bbapi.controller;

import com.rlti.hexagonal.bbapi.application.port.in.ValidateAccountUseCase;
import com.rlti.hexagonal.bbapi.domain.model.ValidationResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validate")
public class AccountValidationController {

    private final ValidateAccountUseCase useCase;

    public AccountValidationController(ValidateAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ValidationResponse validateAccount(@RequestParam String agencia,
                                              @RequestParam String conta,
                                              @RequestParam String cpfCnpj) {
        return useCase.validateAccount(agencia, conta, cpfCnpj);
    }
}
