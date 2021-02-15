package br.com.mercadosallas.clientes.exception;

import lombok.Getter;

@Getter
public class ClienteNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 4010655958693219577L;

    private String message;

    public ClienteNaoEncontradoException(String message) {
        super(message);
        this.message = message;
    }
}
