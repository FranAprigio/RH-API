package br.com.gestao.recursoshumanos.Error.Funcionario;

public class EditarDesempenhoException extends Exception {
    @Override
    public String getMessage() {
        return "Nota do desempenho maior do que o permitido ";
    }
}
