package br.com.gestao.recursoshumanos.Service.DTO.BonusProduto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonusProdutoDTO {
    public int id;
    public double valorBonus;
}



