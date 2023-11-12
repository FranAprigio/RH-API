package br.com.gestao.recursoshumanos.Error.Funcionario;

public class ValidarFuncionarioException extends Exception {
    @Override()
    public String getMessage() {
        return "Esse funcionário não pode existir ";
    }
}
