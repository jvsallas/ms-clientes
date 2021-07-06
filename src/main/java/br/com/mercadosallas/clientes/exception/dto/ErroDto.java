package br.com.mercadosallas.clientes.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErroDto {
    private String message;
    private int status;
    private String error;
    private LocalDateTime timestamp;

    public ErroDto(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST.value();
        this.timestamp = LocalDateTime.now();
    }

    public ErroDto(String message, int status, String error) {
        this.message = message;
        this.status = status;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    public ErroDto(String message, Throwable exception) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST.value();
        this.error = exception.getMessage();
        this.timestamp = LocalDateTime.now();

    }
}
