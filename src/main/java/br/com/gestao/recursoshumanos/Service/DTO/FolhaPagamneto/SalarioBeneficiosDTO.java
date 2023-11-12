package br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioBeneficiosDTO {

    private double valeTransporte;

    private double planoSaude;

    private double adicionalNoturno;

    private double salarioFamilia;
}
