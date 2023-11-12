package br.com.gestao.recursoshumanos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao.recursoshumanos.Service.BonusProdutoService;
import br.com.gestao.recursoshumanos.Service.DTO.BonusProduto.BonusProdutoDTO;
import br.com.gestao.recursoshumanos.Service.DTO.BonusProduto.ClienteDTO;

@RestController
@RequestMapping("/bonus")
public class BonusControler {
    @Autowired
    BonusProdutoService service;

    @GetMapping("/produto/{id}/cliente/{cliente}")
    public ResponseEntity<?> bonusProduto(@PathVariable int id, @PathVariable int cliente) {
        BonusProdutoDTO bonificacao = service.requisitarBonusProduto(id);
        ClienteDTO clientePremiado = ClienteDTO.builder()
                .id_cliente(cliente)
                .id_produto(bonificacao.getId())
                .valorBonificacao(bonificacao.getValorBonus())
                .build();
        return ResponseEntity.ok(clientePremiado);
    }
}