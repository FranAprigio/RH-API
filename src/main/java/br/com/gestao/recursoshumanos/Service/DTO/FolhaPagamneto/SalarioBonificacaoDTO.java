package br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioBonificacaoDTO {
    private double bonusDesempenho;

    private double bonusAniversario;
}
