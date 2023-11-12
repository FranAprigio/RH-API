package br.com.gestao.recursoshumanos.Service.DAO;

import br.com.gestao.recursoshumanos.Model.ExFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExFuncionarioDao extends JpaRepository<ExFuncionario, Integer> {
}
