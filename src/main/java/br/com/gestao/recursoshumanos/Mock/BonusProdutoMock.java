package br.com.gestao.recursoshumanos.Mock;

import br.com.gestao.recursoshumanos.Service.DTO.BonusProduto.BonusProdutoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component(value = "produto")
public class BonusProdutoMock extends RestTemplate {
    static Map<String, Object> comandos;
    static {
        comandos = new HashMap<>();
        comandos.put("http://compras/bonus/1", BonusProdutoDTO.builder()
                .id(1)
                .valorBonus(200)
                .build());
    }
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object ...urlVariables){
        return new ResponseEntity<>((T)comandos.get(url), HttpStatus.OK);
    }
}
