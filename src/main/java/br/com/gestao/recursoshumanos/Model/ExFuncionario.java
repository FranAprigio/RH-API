
package br.com.gestao.recursoshumanos.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_ex_funcionario")
@Inheritance(strategy = InheritanceType.JOINED)
public class ExFuncionario extends Pessoa {

    @Column(name = "cd_matricula", unique = true, nullable = false)
    private int matricula;

    @OneToOne
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @Column(name = "nm_cargo", nullable = false)
    private String cargo;

    @Column(name = "qt_filhos", nullable = false, length = 2)
    private int numeroFilhos;

    @Column(name = "fl_turno", nullable = false)
    private boolean trabalhadorNoturno;

    @Column(name = "fl_vale_transporte", nullable = false)
    private boolean recebeValeTransporte;

    @Column(name = "vl_plano_saude", nullable = false)
    private boolean recebePlanoSaude;

    @Column(name = "vl_salario", nullable = false)
    private double salarioBruto;

    @Column(name = "ds_feedback", nullable = true)
    private String feedback;

    @Column(name = "vl_nota", nullable = true, length = 2)
    private int notaDesempenho;

    @Column(name = "dt_demissao", nullable = true)
    private LocalDate dataDemissao;

    @Column(name = "fl_EPI", nullable = false)
    private boolean EPI;
}
