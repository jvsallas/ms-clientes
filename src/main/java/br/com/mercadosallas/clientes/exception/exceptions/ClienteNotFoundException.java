package br.com.mercadosallas.clientes.exception.exceptions;

import br.com.mercadosallas.handler.ClienteExceptionGeneric;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClienteNotFoundException extends ClienteExceptionGeneric {
    private static final long serialVersionUID = 4010655958693219577L;

    public ClienteNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
