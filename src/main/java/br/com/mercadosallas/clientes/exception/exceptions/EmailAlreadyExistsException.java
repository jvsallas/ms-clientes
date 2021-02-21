package br.com.mercadosallas.clientes.exception.exceptions;

import lombok.Getter;

@Getter
public class EmailAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 5729593589950201374L;

    private String message;

    public EmailAlreadyExistsException(String message) {
        super();
        this.message = message;
    }
}
