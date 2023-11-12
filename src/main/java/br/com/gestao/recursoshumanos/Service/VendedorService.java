package br.com.gestao.recursoshumanos.Service;

import br.com.gestao.recursoshumanos.Links.ServerVendasLinks;
import br.com.gestao.recursoshumanos.Model.Funcionario;
import br.com.gestao.recursoshumanos.Service.DAO.FuncionarioDao;
import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.VendasDTO;
import br.com.gestao.recursoshumanos.Service.DTO.VendedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VendedorService {
    private final RestTemplate rest;
    private final FuncionarioDao funcionarioDao;
    private final ServerVendasLinks vendasServer;
    private final List<Funcionario> todosVendedores;
    private final List<VendedorDTO> listaVendas;

    @Autowired
    public VendedorService(RestTemplate rest, FuncionarioDao funcionarioDao, ServerVendasLinks serverVendasLinks) {
        this.rest = rest;
        this.funcionarioDao = funcionarioDao;
        this.vendasServer = serverVendasLinks;
        todosVendedores = new ArrayList<>();
        listaVendas = new ArrayList<>();
    }

    public VendedorDTO vendedorComMaisVendasMes(int mes, int ano) throws Exception {
        todosVendedores.clear();
        todosVendedores.addAll(funcionarioDao.findAllByDepartamentoId(2));

        for (Funcionario todosVendedore : todosVendedores) {
            buscarVendas(todosVendedore, ano, mes);
        }

        VendedorDTO maiorVendedor = new VendedorDTO(0, 0);

        for (VendedorDTO listaVenda : listaVendas) {
            if (maiorVendedor.getVendas() <= listaVenda.getVendas()) {
                maiorVendedor = listaVenda;
            }
        }
        return maiorVendedor;
    }

    public void buscarVendas(Funcionario funcionario, int anoAtual, int mesAtual) throws Exception {
        String url = vendasServer.getTotalVendas() + "/" + funcionario.getMatricula() + "/" + anoAtual + "/" + mesAtual;

        try {
            ResponseEntity<VendasDTO> vendaMes = rest.getForEntity(url, VendasDTO.class);
            double vendas = Objects.requireNonNull(vendaMes.getBody()).getValorVendas();
            VendedorDTO vendedorAtual = VendedorDTO.builder()
                    .matricula(funcionario.getMatricula())
                    .vendas(vendas)
                    .build();
            listaVendas.add(vendedorAtual);
        } catch (Exception e) {
            throw new Exception("Possivel falha ao se comunicar com servidor de Vendas",e);
        }
    }
}
