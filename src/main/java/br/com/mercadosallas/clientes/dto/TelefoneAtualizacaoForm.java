package br.com.mercadosallas.clientes.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TelefoneAtualizacaoForm {

    private Long id;
    private String ddd;
    private String numero;
    private String tipo;
}
