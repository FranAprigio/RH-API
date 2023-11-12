package br.com.gestao.recursoshumanos.Error.Treinamento;

public class ApagarException extends Exception{
    @Override
    public String getMessage(){
        return "Não foi possível apagar este objeto.";
    }

}
