package br.com.gestao.recursoshumanos.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private int id;

    @Column(name = "nm_departamento", nullable = false, length = 40)
    private DepartamentoNome nome;

    @Column(name = "sg_setor", nullable = false, length = 10)
    private String setor;
}

