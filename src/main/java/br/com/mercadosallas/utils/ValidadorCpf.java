package br.com.mercadosallas.utils;

import br.com.caelum.stella.validation.CPFValidator;

public class ValidadorCpf {
    public static void validar(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.assertValid(cpf);
    }
}
