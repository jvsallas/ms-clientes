package br.com.mercadosallas.clientes.exception.exceptions;

import lombok.Getter;

@Getter
public class CpfAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 3120585767759956829L;

    private String message;

    public CpfAlreadyExistsException(String message) {
        super();
        this.message = message;
    }
}
