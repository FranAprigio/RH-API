package br.com.gestao.recursoshumanos.Service.DTO.Treinamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreinamentoDTO {

    private String nome;

    private int dificuldade;

    private int prazoConclusao;

    private String treinamentoTipo;
}
