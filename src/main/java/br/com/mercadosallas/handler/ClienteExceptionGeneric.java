package br.com.mercadosallas.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ClienteExceptionGeneric extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public ClienteExceptionGeneric(String message, int status) {
        super(message);
        this.message = message;
        this.httpStatus = HttpStatus.valueOf(status);
    }
}
