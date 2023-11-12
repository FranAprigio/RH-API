package br.com.gestao.recursoshumanos.Error.FolhaPagamento;

public class BuscarFolhaException extends Exception{
    @Override
    public String getMessage(){
        return "Não foi possível concluir a busca. Pssível erro de conexão com banco de dados.";
    }
}
