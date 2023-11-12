package br.com.gestao.recursoshumanos.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_treinamento")
public class Treinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_treinamento")
    private int id;

    @Column(name = "nm_treinamento", nullable = false, length = 50)
    private String nome;

    @Max(value = 10, message = "Dificuldade Max:10 ")
    @Column(name = "vl_dificuldade",nullable = false)
    private int dificuldade;

    @Column(name = "fl_status", nullable = false)
    private boolean concluida;

    @Column(name = "dt_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "nu_prazo_dias")
    private int prazoConclusao;

    @Column(name = "nm_tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TreinamentoTipo treinamentoTipo;
}
