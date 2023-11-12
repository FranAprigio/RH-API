package br.com.gestao.recursoshumanos.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "t_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private int id;

    @Column(name = "nm_pessoa", nullable = false, length = 50)
    @NotBlank(message = "Campo 'pessoa' inválido.")
    private String nome;

    @Column(name = "em_email", nullable = false)
    @NotBlank(message = "Campo 'email' inválido.")
    private String email;

    @Column(name = "cd_telefone", nullable = false)
    @Size(min = 9, max = 20, message = "Campo 'telefone' inválido.")
    private String telefone;

    @Column(name = "cd_cpf", nullable = true)
    @Size(min = 11, max = 11 , message = "Campo 'cpf' inválido.")
    private String cpf;

    @Column(name = "dt_nascimento", nullable = true)
    private LocalDate nascimento;

    private int calcularIdade(){
        return Period.between(LocalDate.now(),nascimento).getYears();
    }
}
