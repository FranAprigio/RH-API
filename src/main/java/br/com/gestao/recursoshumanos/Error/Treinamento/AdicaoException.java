package br.com.gestao.recursoshumanos.Error.Treinamento;

public class AdicaoException extends Exception{
    @Override
    public String getMessage(){
        return "Não foi possível criar este objeto.";
    }
}
