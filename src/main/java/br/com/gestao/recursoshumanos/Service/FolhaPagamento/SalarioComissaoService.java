package br.com.gestao.recursoshumanos.Service.FolhaPagamento;

import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.ComissaoDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class SalarioComissaoService {
    static final List<ComissaoDTO> LISTA_DE_METAS_PARA_COMISSAO = Arrays.asList(
            new ComissaoDTO(1000, 0.025),
            new ComissaoDTO(2500, 0.05),
            new ComissaoDTO(6000, 0.2)
    );

    public double calcularComissao(double valorVenda) {
        double porcentagemComissao = 0.01;

        for (int i = LISTA_DE_METAS_PARA_COMISSAO.size() - 1; i >= 0; i--) {
            ComissaoDTO metaComissao = LISTA_DE_METAS_PARA_COMISSAO.get(i);

            if (valorVenda >= metaComissao.getMetaVendaEmReais()) {
                porcentagemComissao = metaComissao.getPorcentagemComissao();
                i = -1;
            }
        }
        return porcentagemComissao * valorVenda;
    }
}