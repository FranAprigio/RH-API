package br.com.gestao.recursoshumanos.Error.Treinamento;

public class TipoExcepition extends Exception{
    @Override
    public String getMessage(){
        return "Não foi possível criar este objeto. Pois o tipo deve ser treinamento ou desenvolvimento.";
    }
}
