package br.com.gestao.recursoshumanos.Error.Treinamento;

public class ObjetoExistenteException extends Exception{
    @Override
    public String getMessage(){
        return "O objeto não pode ser criado, pois já existe um objeto com este ID.";
    }
}
