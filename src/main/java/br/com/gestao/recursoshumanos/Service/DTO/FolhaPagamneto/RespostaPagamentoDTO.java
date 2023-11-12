package br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespostaPagamentoDTO {
//    public int matricula;
    public boolean pagamentoEfetuado;
//    public double salarioLiquido;
}
