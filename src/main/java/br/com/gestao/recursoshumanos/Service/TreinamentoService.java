package br.com.gestao.recursoshumanos.Service;

import br.com.gestao.recursoshumanos.Error.Treinamento.*;
import br.com.gestao.recursoshumanos.Model.Treinamento;
import br.com.gestao.recursoshumanos.Model.TreinamentoTipo;
import br.com.gestao.recursoshumanos.Service.DAO.TreinamentoDao;
import br.com.gestao.recursoshumanos.Service.DTO.Treinamento.TreinamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TreinamentoService {
    @Autowired
    TreinamentoDao treinamentoDao;

    public Treinamento obterTreinamentoPorID(int id) throws Exception {
        var treinamento = treinamentoDao.findById(id);
        if (treinamento == null){
            throw new BuscaException();
        }
        return treinamento;
    }

    public List<Treinamento> obterListaTreinamento(int skip, int take)throws Exception{
        var listaTreinamento = treinamentoDao.findAll().stream()
                            .skip(skip)
                            .limit(take)
                            .collect(Collectors.toList());

        if (listaTreinamento.isEmpty()){
            throw new BuscaException();
        }
        return listaTreinamento;
    }

    public Treinamento criarTreinamento(TreinamentoDTO treinamentoDTO) throws Exception{
        TreinamentoTipo tipoTreinamento = transformarStringEmTipoTreinamento(treinamentoDTO.getTreinamentoTipo());

        Treinamento nvTreinamento = Treinamento.builder()
                .id(0)
                .nome(treinamentoDTO.getNome())
                .dificuldade(treinamentoDTO.getDificuldade())
                .dataCriacao(LocalDate.now())
                .prazoConclusao(treinamentoDTO.getPrazoConclusao())
                .concluida(false)
                .treinamentoTipo(tipoTreinamento)
                .build();
        return nvTreinamento;
    }

    public Treinamento salvarTreinamento(Treinamento nvTreinamento) throws Exception{
        try {
            treinamentoDao.save(nvTreinamento);
            return nvTreinamento;
        }catch(Exception e){
            throw new AdicaoException();
        }
    }

    public void editarCampoNome(String nvNome, int id) throws Exception{
        var treinamento = obterTreinamentoPorID(id);
        treinamento.setNome(nvNome);

        try {
            treinamentoDao.save(treinamento);
        }catch (Exception e) {
            throw new AuteracaoException();
        }
    }

    public void editarTreinamento(Treinamento treinamentoOriginal, TreinamentoDTO novaTreinamento) throws Exception{

        treinamentoOriginal.setNome(novaTreinamento.getNome());
        treinamentoOriginal.setPrazoConclusao(novaTreinamento.getPrazoConclusao());
        treinamentoOriginal.setDificuldade(novaTreinamento.getDificuldade());
        treinamentoOriginal.setTreinamentoTipo(transformarStringEmTipoTreinamento(novaTreinamento.getTreinamentoTipo()));

        try {
            treinamentoDao.save(treinamentoOriginal);
        }catch (Exception e){
            throw new AuteracaoException();
        }
    }

    public void apagarTreinamento(int id) throws Exception{
        try {
            treinamentoDao.deleteById(id);
        }catch(Exception e)
        {
            throw new ApagarException();
        }
    }

    public void isValid(TreinamentoDTO treinamentoDTO) throws Exception {
        try {
            transformarStringEmTipoTreinamento(treinamentoDTO.getTreinamentoTipo());
        } catch (Exception e) {
            throw e;
        }
    }

    public TreinamentoTipo transformarStringEmTipoTreinamento(String treinamentoDTO)throws Exception{
        String tipoRecebido = treinamentoDTO.toUpperCase().trim();

        try {
            return TreinamentoTipo.valueOf(tipoRecebido);
        }catch (Exception e){
            throw new TipoExcepition();
        }
    }
}
