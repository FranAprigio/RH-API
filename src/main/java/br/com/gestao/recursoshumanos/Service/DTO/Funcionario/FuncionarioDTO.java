package br.com.gestao.recursoshumanos.Service.DTO.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    int id;
    String nome;
    String telefone;
    String cpf;
    String nascimento;
    int matricula;
    int departamento;
    String cargo;
    int numeroFilhos;
    boolean trabalhadorNoturno;
    boolean recebeValeTransporte;
    boolean recebePlanoSaude;
    int salarioBruto;
    String feedback;
    int notaDesempenho;
    String email;
    LocalDate dataContratacao;
}
