package br.com.mercadosallas.clientes.validation.handler;

import br.com.mercadosallas.clientes.validation.dto.ErroFormularioDto;
import br.com.mercadosallas.clientes.validation.dto.ErroInterno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidationHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormularioDto> handle(MethodArgumentNotValidException exception) {
        List<ErroFormularioDto> errosForm = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

            ErroFormularioDto erroForm = new ErroFormularioDto(fieldError.getField(), mensagem);

            errosForm.add(erroForm);
        });

        return errosForm;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeException.class)
    public String handleData(DateTimeException exception) {
        return "Ocorreu um erro ao formatar a data";
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErroInterno handleGenerico(Exception exception) {
        return new ErroInterno("Ocorreu um inesperado: " + exception.getMessage());
    }


}
