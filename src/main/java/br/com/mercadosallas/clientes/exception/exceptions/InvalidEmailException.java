package br.com.mercadosallas.clientes.exception.exceptions;

import lombok.Getter;

@Getter
public class InvalidEmailException extends RuntimeException {

    private static final long serialVersionUID = 6571240371884574923L;

    private String message;

    public InvalidEmailException(String message) {
        super(message);
        this.message = message;
    }

}
