package br.com.mercadosallas.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ClienteExceptionGeneric extends RuntimeException {

    private HttpStatus httpStatus;

    public ClienteExceptionGeneric(String message, int status) {
        super(message);
        this.httpStatus = HttpStatus.valueOf(status);
    }

    public ClienteExceptionGeneric(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;

    }
}
