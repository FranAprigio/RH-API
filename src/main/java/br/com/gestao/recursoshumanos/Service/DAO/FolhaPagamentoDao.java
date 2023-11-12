package br.com.gestao.recursoshumanos.Service.DAO;

import br.com.gestao.recursoshumanos.Model.FolhaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolhaPagamentoDao extends JpaRepository<FolhaPagamento, Integer> {
    @Query("SELECT f FROM FolhaPagamento f WHERE f.anoLancamento*100 + f.mesLancamento BETWEEN :dataInicio AND :dataFim")
    List<FolhaPagamento> findByDataLancamentoBetween(@Param("dataInicio") Integer dataInicio, @Param("dataFim") Integer dataFim);

    @Query("SELECT f.mesLancamento FROM FolhaPagamento f ORDER BY f.id DESC")
    Integer findOneRetornaUltimoMesDeFolhaLancada();
}
