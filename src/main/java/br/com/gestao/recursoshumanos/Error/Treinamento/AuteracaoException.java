package br.com.gestao.recursoshumanos.Error.Treinamento;

public class AuteracaoException extends Exception{
    @Override
    public String getMessage(){
        return "Não foi possível editar este objeto.";
    }
}
