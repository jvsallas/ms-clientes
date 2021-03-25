package br.com.mercadosallas.clientes.exception.exceptions;

import br.com.mercadosallas.handler.ClienteExceptionGeneric;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CpfAlreadyExistsException extends ClienteExceptionGeneric {
    private static final long serialVersionUID = 3120585767759956829L;

    public CpfAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
    public CpfAlreadyExistsException(String message, int status) {
        super(message, status);
    }
}
