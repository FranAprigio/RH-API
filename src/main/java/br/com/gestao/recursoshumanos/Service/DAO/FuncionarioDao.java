package br.com.gestao.recursoshumanos.Service.DAO;

import br.com.gestao.recursoshumanos.Model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioDao extends JpaRepository<Funcionario, Integer> {
    @Query("SELECT f FROM Funcionario f WHERE f.departamento.id = :departamentoId")
    List<Funcionario> findAllByDepartamentoId(@Param("departamentoId") Integer departamentoId);

}

