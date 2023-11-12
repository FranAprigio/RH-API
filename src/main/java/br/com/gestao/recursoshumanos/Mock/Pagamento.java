package br.com.gestao.recursoshumanos.Mock;

import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.RespostaPagamentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Component(value = "pagamento")
public class Pagamento extends RestTemplate{
    static Map<String, Object> comandos;
    static {
        comandos = new HashMap<>();

        comandos.put("https://pagamentos.up.railway.app/funcionario/pagamento",
                RespostaPagamentoDTO.builder()
                        .pagamentoEfetuado(true)
                        .build());

    }
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object ...uriVariables ){
        return new ResponseEntity<>((T)comandos.get(url), HttpStatus.OK);
    }
}