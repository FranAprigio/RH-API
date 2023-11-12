package br.com.gestao.recursoshumanos.Service.DAO;

import br.com.gestao.recursoshumanos.Model.Departamento;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoDao extends JpaRepository<Departamento, Integer> {
    Optional<Departamento> findById(Long id);
}
