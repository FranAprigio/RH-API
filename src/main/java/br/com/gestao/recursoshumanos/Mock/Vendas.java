package br.com.gestao.recursoshumanos.Mock;

import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.VendasDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Component(value = "vendas")
public class Vendas extends RestTemplate {
    static Map<String, Object> comandos;
    static {
        comandos = new HashMap<>();

        comandos.put("http://34.195.14.39/vendedor/2/vendas/mensais",
                VendasDTO.builder()
                        .valorVendas(2000)
                        .build());
    }
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object ...uriVariables ){
        return new ResponseEntity<>((T)comandos.get(url), HttpStatus.OK);
    }
}
