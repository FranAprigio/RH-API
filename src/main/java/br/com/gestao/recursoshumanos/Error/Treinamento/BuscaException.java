package br.com.gestao.recursoshumanos.Error.Treinamento;

public class BuscaException extends Exception{

    @Override
    public String getMessage(){
        return "Objeto pesquisado não encontrado.";
    }
}
