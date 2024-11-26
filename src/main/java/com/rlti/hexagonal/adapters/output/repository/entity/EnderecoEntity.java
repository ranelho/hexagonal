package com.rlti.hexagonal.adapters.output.repository.entity;


import com.rlti.hexagonal.application.core.domain.enums.TipoEndereco;
import com.rlti.hexagonal.application.core.domain.enums.TipoLogradouro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ENDERECO")
public class EnderecoEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long idEndereco;
        private String cep;
        private String logradouro;
        private String bairro;
        private String cidade;
        private String uf;
        private String complemento;
        private String numero;
        private String pais;
        private TipoLogradouro tipoLogradouro;
        private TipoEndereco tipoEndereco;
        private String tipoFuncaoEndereco;

        @ManyToOne(fetch = FetchType.LAZY)
        private PessoaEntity pessoa;
}
