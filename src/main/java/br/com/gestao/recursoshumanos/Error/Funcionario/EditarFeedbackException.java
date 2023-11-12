package br.com.gestao.recursoshumanos.Error.Funcionario;

public class EditarFeedbackException extends Exception {
    @Override
    public String getMessage() {
        return "Não foi possível editar o feedback desse funcionário. ";
    }
}
