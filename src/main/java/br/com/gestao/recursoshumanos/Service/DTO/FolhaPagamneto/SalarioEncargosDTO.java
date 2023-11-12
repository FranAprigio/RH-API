package br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioEncargosDTO {

    private double inss;


    private double fgts;


    private double sindical;
}
