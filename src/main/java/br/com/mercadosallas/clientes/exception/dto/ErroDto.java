package br.com.mercadosallas.clientes.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErroDto {
    private String message;
    private int status;
    private String error;
    private LocalDateTime timestamp;

    public ErroDto(String message) {
        this.message = message;
    }

    public ErroDto(String message, int status, String error) {
        this.message = message;
        this.status = status;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
}
