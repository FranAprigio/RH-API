package br.com.gestao.recursoshumanos.Error.FolhaPagamento;

public class FolhaExistenteException extends Exception{
    @Override
    public String getMessage(){
        return "A Folha de Pagamentos desse mês já foi gerada.";
    }
}
