package br.com.mercadosallas.telefones.exception;

import br.com.mercadosallas.handler.ClienteExceptionGeneric;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TelefoneNotFoundException extends ClienteExceptionGeneric {
    private static final long serialVersionUID = -9122837982520528703L;

    public TelefoneNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
