package br.com.gestao.recursoshumanos.Controller;

import br.com.gestao.recursoshumanos.Service.DTO.VendedorDTO;
import br.com.gestao.recursoshumanos.Service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    VendedorService vendedorService;

    //Me pediram para fazer esse método, eu não quis, conversei, expliquei, mas.... tá aí

    @GetMapping(value = "/mes/{mes}/{ano}")
    public ResponseEntity<?> vendedorDoMes(@PathVariable int mes, @PathVariable int ano) throws Exception {
        VendedorDTO vendedor = vendedorService.vendedorComMaisVendasMes(mes,ano);
        return ResponseEntity.ok(vendedor);
    }
}
