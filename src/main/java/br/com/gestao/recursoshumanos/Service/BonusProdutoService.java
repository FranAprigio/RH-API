package br.com.gestao.recursoshumanos.Service;

import br.com.gestao.recursoshumanos.Service.DTO.BonusProduto.BonusProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class BonusProdutoService {
    @Autowired
    RestTemplate restTemplate;
    @Qualifier("produto")
    @Autowired
    RestTemplate mock;
    public BonusProdutoDTO requisitarBonusProduto(int produtoid){
        String url = "http://compras/bonus/" + produtoid + "";
        ResponseEntity<BonusProdutoDTO> produtoRecebido = mock.getForEntity(url,BonusProdutoDTO.class);
        BonusProdutoDTO resposta = Objects.requireNonNull(produtoRecebido.getBody());
        return resposta;
    }
}
