package br.com.mercadosallas.clientes.exception.exceptions;

import br.com.mercadosallas.handler.ClienteExceptionGeneric;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailAlreadyExistsException extends ClienteExceptionGeneric {
    private static final long serialVersionUID = 5729593589950201374L;

    public EmailAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
