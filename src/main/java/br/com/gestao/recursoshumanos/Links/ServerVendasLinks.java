package br.com.gestao.recursoshumanos.Links;

import org.springframework.stereotype.Service;

//Criei essa nova estrutura, pensando em facilitar as manutenções no código, uma vez que caso o servidor mude o link,
// basta mudar por aqui e o serviços voltaram a funcionar
@Service
public class ServerVendasLinks {
    final String LINK_SERVIDOR_VENDAS = "https://gateway-sgeu.up.railway.app/vendas/pedido";
    final String totalVendas = LINK_SERVIDOR_VENDAS + "/calcular/vendedor";

    public String getTotalVendas(){
        return totalVendas;
    };
}
