package br.com.mercadosallas.clientes.validation.exception;

import lombok.Getter;

@Getter
public class CpfJaCadastradoException extends RuntimeException {
    private static final long serialVersionUID = 3120585767759956829L;

    private String message;

    public CpfJaCadastradoException(String message) {
        super();
        this.message = message;
    }
}
