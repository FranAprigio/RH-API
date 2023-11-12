package br.com.gestao.recursoshumanos.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_folha_pagamento")
public class FolhaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_folha_pagamento")
    private int id;

//DATA
    @Column(name = "dt_mes")
    private int mesLancamento;

    @Column(name = "dt_ano")
    private int anoLancamento;

//BENEFÍCIOS
    @Column(name = "vl_ben_vale_transporte")
    private double benValeTransporte;

    @Column(name = "vl_ben_plano_saude")
    private double benPlanoSaude;

    @Column(name = "vl_ben_adicional_noturno")
    private double benAdicionalNoturno;

    @Column(name = "vl_ben_salario_familia")
    private double benSalarioFamilia;

//BONIFICAÇÕES
    @Column(name = "vl_bon_desempenho")
    private double bonusDesempenho;

    @Column(name = "vl_bon_aniversario")
    private double bonusAniversario;

//COMISSAO
    @Column(name = "vl_bon_comissao")
    private double comissao;

//ENCARGOS
    @Column(name = "vl_enc_inss")
    private double encInss;

    @Column(name = "vl_enc_fgts")
    private double encFgts;

    @Column(name = "vl_enc_sindical")
    private double encSindical;

//RESULTADO / FUNCIONARIO
    @Column(name = "vl_salario")
    private double salarioBruto;

    @OneToOne
    @JoinColumn(name = "cd_matricula")
    private Funcionario matricula;

}
