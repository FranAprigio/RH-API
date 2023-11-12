package br.com.gestao.recursoshumanos.Service.DAO;

import br.com.gestao.recursoshumanos.Model.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinamentoDao extends JpaRepository<Treinamento, Integer> {
    Treinamento findById(int i);
}
