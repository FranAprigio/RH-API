package br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComissaoDTO {

    private double metaVendaEmReais;
    private double porcentagemComissao;

}
