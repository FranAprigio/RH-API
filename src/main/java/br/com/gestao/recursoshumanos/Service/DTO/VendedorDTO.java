package br.com.gestao.recursoshumanos.Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO {
    public int matricula;
    public double vendas;
}
