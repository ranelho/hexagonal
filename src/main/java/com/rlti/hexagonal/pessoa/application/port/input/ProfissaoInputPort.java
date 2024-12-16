package com.rlti.hexagonal.pessoa.application.port.input;

import com.opencsv.CSVReader;
import com.rlti.hexagonal.pessoa.adapters.input.request.ProfissaoFilter;
import com.rlti.hexagonal.pessoa.adapters.output.response.ProfissaoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfissaoInputPort {
    void processarProfissao(MultipartFile file);

    List<ProfissaoResponse> getAllCnaesWithFilter(ProfissaoFilter params);

    //void processarProfissao(String[] linha);
}
