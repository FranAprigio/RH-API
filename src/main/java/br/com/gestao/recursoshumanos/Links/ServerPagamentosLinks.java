package br.com.gestao.recursoshumanos.Links;

import org.springframework.stereotype.Service;
@Service
public class ServerPagamentosLinks {
    final String LINK_SERVIDOR_PAGAMENTO = "http://modulo-pagamento-production.up.railway.app";
    final String solicitarPagamento = LINK_SERVIDOR_PAGAMENTO + "/modulo-de-pagamentos/salario/";

    public String getSolicitarPagamento(){
        return solicitarPagamento;
    };
}
