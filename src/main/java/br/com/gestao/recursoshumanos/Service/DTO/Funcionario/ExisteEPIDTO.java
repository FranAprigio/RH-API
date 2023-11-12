package br.com.gestao.recursoshumanos.Service.DTO.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExisteEPIDTO {

    public int matricula;
    public boolean estoque;
}
