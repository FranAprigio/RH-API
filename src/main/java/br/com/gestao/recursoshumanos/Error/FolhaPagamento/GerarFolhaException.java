package br.com.gestao.recursoshumanos.Error.FolhaPagamento;

public class GerarFolhaException extends Exception{
    public GerarFolhaException(String message) {
        super("Não foi possível gerar a folha de pagamento. " + message + ".");
    }
}