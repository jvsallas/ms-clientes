package br.com.mercadosallas.telefones.exception;

import br.com.mercadosallas.handler.ClienteExceptionGeneric;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MinimoTelefoneException extends ClienteExceptionGeneric {
    private static final long serialVersionUID = -3308117885243103609L;

    public MinimoTelefoneException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
