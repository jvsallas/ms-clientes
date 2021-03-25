package br.com.mercadosallas.telefones.exception;

import br.com.mercadosallas.handler.ClienteExceptionGeneric;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MaximoTelefoneException extends ClienteExceptionGeneric {
    private static final long serialVersionUID = 4592362287975629929L;

    public MaximoTelefoneException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
