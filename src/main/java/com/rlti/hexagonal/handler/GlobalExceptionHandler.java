package com.rlti.hexagonal.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(DuplicidadeException.class)
    public ResponseEntity<Object> handleDuplicidadeException(DuplicidadeException ex) {
        logger.warn("Erro de duplicidade: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                ERROR, "Duplicidade de Registro",
                MESSAGE, ex.getMessage(),
                TIMESTAMP, Instant.now()
        ));
    }

    @ExceptionHandler(ArquivoVazioException.class)
    public ResponseEntity<Object> handleArquivoVazioException(ArquivoVazioException ex) {
        logger.warn("Erro de arquivo vazio: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                ERROR, "Arquivo Vazio",
                MESSAGE, ex.getMessage(),
                TIMESTAMP, Instant.now()
        ));
    }

    @ExceptionHandler(ProcessamentoArquivoException.class)
    public ResponseEntity<Object> handleProcessamentoArquivoException(ProcessamentoArquivoException ex) {
        logger.error("Erro ao processar arquivo: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                ERROR, "Erro ao Processar Arquivo",
                MESSAGE, ex.getMessage(),
                TIMESTAMP, Instant.now()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        logger.error("Erro inesperado: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                ERROR, "Erro Interno",
                MESSAGE, ex.getMessage(),
                TIMESTAMP, Instant.now()
        ));
    }
}
