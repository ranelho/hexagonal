package com.rlti.hexagonal.pessoa.application.port.input;

import com.opencsv.CSVReader;
import org.springframework.web.multipart.MultipartFile;

public interface ProfissaoInputPort {
    void processarProfissao(MultipartFile file);

    //void processarProfissao(String[] linha);
}
