package com.rlti.hexagonal.pessoa.application.core.usecase;

import com.rlti.hexagonal.pessoa.adapters.input.request.AgenciaRequest;
import com.rlti.hexagonal.pessoa.adapters.input.request.BancoRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.AgenciaResponse;
import com.rlti.hexagonal.pessoa.adapters.output.response.BancoResponse;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Agencia;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Banco;
import com.rlti.hexagonal.pessoa.application.port.input.InstituicaoFinanceiraInputPort;
import com.rlti.hexagonal.pessoa.application.port.output.InstituicaoFinanceiraOutputPort;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class InstituicaoFinanceiraUseCase implements InstituicaoFinanceiraInputPort {

    private final InstituicaoFinanceiraOutputPort instituicaoFinanceiraOutputPort;
    private final ModelMapper modelMapper;

    public InstituicaoFinanceiraUseCase(InstituicaoFinanceiraOutputPort instituicaoFinanceiraOutputPort, ModelMapper modelMapper) {
        this.instituicaoFinanceiraOutputPort = instituicaoFinanceiraOutputPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public void criarBanco(BancoRequest request) {
        var banco = modelMapper.map(request, Banco.class);
        instituicaoFinanceiraOutputPort.criarBanco(banco);
    }

    @Override
    public void atualizarBanco(Long id, BancoRequest request) {
        var banco = modelMapper.map(request, Banco.class);
        instituicaoFinanceiraOutputPort.atualizarBanco(id, banco);
    }

    @Override
    public Page<BancoResponse> listarBancos(int page, int size) {
        var bancos = instituicaoFinanceiraOutputPort.findAllPaginated(page, size);
        return bancos.map(banco -> modelMapper.map(banco, BancoResponse.class));
    }

    @Override
    public BancoResponse buscarBanco(Long id) {
        var banco = instituicaoFinanceiraOutputPort.findById(id);
        return modelMapper.map(banco, BancoResponse.class);
    }

    @Override
    public void criarAgencia(Long id, AgenciaRequest request) {
        var agencia = modelMapper.map(request, Agencia.class);
        instituicaoFinanceiraOutputPort.criarAgencia(id, agencia);
    }

    @Override
    public void atualizarAgencia(Long idAgencia, AgenciaRequest request) {
        var agencia = modelMapper.map(request, Agencia.class);
        instituicaoFinanceiraOutputPort.atualizarAgencia(idAgencia, agencia);
    }

    @Override
    public AgenciaResponse buscarAgencia(Long idAgencia) {
        var agencia = instituicaoFinanceiraOutputPort.buscarAgencia(idAgencia);
        return modelMapper.map(agencia, AgenciaResponse.class);
    }

    @Override
    public Page<AgenciaResponse> listarAgenciasPorBanco(Long idBanco, int page, int size) {
        var agencias = instituicaoFinanceiraOutputPort.listarAgenciasPorBanco(idBanco, page, size);
        return agencias.map(agencia -> modelMapper.map(agencia, AgenciaResponse.class));
    }

    @Override
    public void importExcel(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 9; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Banco banco = new Banco();
                    banco.setCnpj(row.getCell(0).getStringCellValue());
                    banco.setNome(row.getCell(1).getStringCellValue());
                    banco.setSiteNaInternet(row.getCell(13).getStringCellValue());
                    instituicaoFinanceiraOutputPort.save(banco);
                }
            }
        }
    }


}
