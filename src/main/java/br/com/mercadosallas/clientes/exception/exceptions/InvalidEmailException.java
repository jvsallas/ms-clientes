package br.com.mercadosallas.clientes.exception.exceptions;

import br.com.mercadosallas.handler.ClienteExceptionGeneric;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidEmailException extends ClienteExceptionGeneric {

    private static final long serialVersionUID = -5393228626043330798L;

    public InvalidEmailException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());

    }

}
