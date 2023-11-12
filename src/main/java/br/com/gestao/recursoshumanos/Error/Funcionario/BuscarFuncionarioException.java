package br.com.gestao.recursoshumanos.Error.Funcionario;

public class BuscarFuncionarioException extends Exception {
    @Override
    public String getMessage() {
        return "Não foi possível concluir a busca. Possível erro de conexão com banco de dados ou o funcinário não existe.";
    }
}
