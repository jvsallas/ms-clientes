package br.com.mercadosallas.clientes.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 4010655958693219577L;

    private String mensagem;
    private Integer statusCode;
}
