package com.example.demo.infrastructure.rest.controller;

import com.example.demo.application.usecase.ProcessTransactionUseCase;
import com.example.demo.infrastructure.rest.dto.TransactionDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ProcessTransactionUseCase processTransactionUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)

    public void create(@Valid @RequestBody TransactionDTO dto) {
        // Conversão elegante: o DTO se transforma em Dominio antes de sair da infraestutura
        var transaction = dto.toDomain();
        processTransactionUseCase.execute(dto.toDomain());
    }

}
