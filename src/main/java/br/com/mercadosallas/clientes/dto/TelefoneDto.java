package br.com.mercadosallas.clientes.dto;

import lombok.Data;

@Data
public class TelefoneDto {
    private Long id;
    private String ddd;
    private String numero;
    private String tipo;
}
