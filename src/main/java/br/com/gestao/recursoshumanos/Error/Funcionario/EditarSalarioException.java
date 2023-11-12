package br.com.gestao.recursoshumanos.Error.Funcionario;

public class EditarSalarioException extends Exception {
    @Override
    public String getMessage() {
        return "Erro ao alterar o salário ";
    }
}
