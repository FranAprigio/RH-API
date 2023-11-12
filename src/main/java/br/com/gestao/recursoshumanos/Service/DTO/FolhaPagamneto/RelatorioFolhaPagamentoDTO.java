package br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto;

import br.com.gestao.recursoshumanos.Model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioFolhaPagamentoDTO {

    private int mesLancamento, anoLancamento;

    private double benValeTransporte, benPlanoSaude, benAdicionalNoturno, benSalarioFamilia;

    private double bonusDesempenho, bonusAniversario;

    private double comissao;

    private double encInss, encFgts, encSindical;

    private double salarioBase;

    private int matricula;

}

