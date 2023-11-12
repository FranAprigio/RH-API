package br.com.gestao.recursoshumanos.Service;

import br.com.gestao.recursoshumanos.Error.FolhaPagamento.BuscarFolhaException;
import br.com.gestao.recursoshumanos.Error.FolhaPagamento.FolhaExistenteException;
import br.com.gestao.recursoshumanos.Error.FolhaPagamento.GerarFolhaException;
import br.com.gestao.recursoshumanos.Links.ServerPagamentosLinks;
import br.com.gestao.recursoshumanos.Links.ServerVendasLinks;
import br.com.gestao.recursoshumanos.Model.FolhaPagamento;
import br.com.gestao.recursoshumanos.Model.Funcionario;
import br.com.gestao.recursoshumanos.Service.DAO.FolhaPagamentoDao;
import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.CorpoPagamentoDTO;
import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.*;
import br.com.gestao.recursoshumanos.Service.FolhaPagamento.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FolhaPagamentoService {

    @Autowired
    FolhaPagamentoDao folhaPagamentoDao;
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    SalarioEncargosService salarioEncargosService;
    @Autowired
    SalarioComissaoService salarioComissaoService;
    @Autowired
    SalarioBeneficiosService salarioBeneficiosService;
    @Autowired
    SalarioBonificacoesService salarioBonificacoesService;
    @Autowired
    ServerVendasLinks serverVendasLinks;

    @Autowired
    ServerPagamentosLinks serverPagamentosLinks;
    @Autowired
    RestTemplate rest;

    final String DEPARTAMENTO_VENDAS = "Vendas";

    public List<FolhaPagamento> buscarTodasFolhasPagamento() {
        return folhaPagamentoDao.findAll();
    }

    public int buscarUltimaFolhaGerada() {
        var ultimoMes = folhaPagamentoDao.findOneRetornaUltimoMesDeFolhaLancada();
        return ultimoMes == null ? 0 : ultimoMes;
    }

    public boolean isValid(LocalDate dataAberturaPedidoDeGeracaoDeFolhaDePagamentos) throws FolhaExistenteException {

        verificarFolhaPagamentoDuplicada(dataAberturaPedidoDeGeracaoDeFolhaDePagamentos);

        return true;
    }

    public void verificarFolhaPagamentoDuplicada(LocalDate dataAberturaPedidoDeGeracaoDeFolhaDePagamentos)throws FolhaExistenteException{
        int mesAtual = dataAberturaPedidoDeGeracaoDeFolhaDePagamentos.getMonthValue();
        int ultimoMes = buscarUltimaFolhaGerada();

        if (ultimoMes == mesAtual) {
            throw new FolhaExistenteException();
        }
    }

    public void gerarFolhaPagamentoMensal(int mesAtual, int anoAtual) throws Exception {
        List<Funcionario> todosFuncionariosEncontrados = funcionarioService.buscaAll();

        for (Funcionario funcionario : todosFuncionariosEncontrados) {
            gerarDadosDePagamentoIndividual(funcionario, mesAtual, anoAtual);
        }
    }
    public void gerarDadosDePagamentoIndividual(Funcionario funcionario, int mesAtual, int anoAtual ) throws Exception{

        double comissaoRecebida = 0;

        if (Objects.equals(funcionario.getDepartamento().getNome().toString(), DEPARTAMENTO_VENDAS)){
            double totalVendido = solicitarTotalVendasMesVendedor(funcionario.getMatricula(),anoAtual,mesAtual);
            comissaoRecebida = salarioComissaoService.calcularComissao(totalVendido);
        }

        SalarioBeneficiosDTO beneficios = salarioBeneficiosService.retornarTodosBeneficios(funcionario);
        SalarioBonificacaoDTO bonificacoes = salarioBonificacoesService.retornarTodasBonificacoes(funcionario);
        SalarioEncargosDTO encargos = salarioEncargosService.retornarTodosEncargos(funcionario.getSalarioBruto());

        double salarioLiquido = calcularSalarioLiquido(funcionario);
        boolean pagamentoRealizado = solicitarPagamentoDeFuncionario(salarioLiquido,funcionario.getMatricula());

        if (!pagamentoRealizado){
            throw new GerarFolhaException("Pagamento não foi realizado, provável falta de caixa. Entre em contato com o financeiro");
        }

        try {
            gerarFolhaDePagamentoIndividual(mesAtual,anoAtual,beneficios,bonificacoes,comissaoRecebida,encargos,funcionario);
        }catch (Exception e){
            throw new GerarFolhaException("Possível erro com banco de dados, favor reptia o processo mais tarde.");
        }
    }

    private void gerarFolhaDePagamentoIndividual(int mesAtual, int anoAtual,SalarioBeneficiosDTO beneficios,SalarioBonificacaoDTO bonificacoes,double comissaoRecebida,SalarioEncargosDTO encargos,Funcionario funcionario){
        FolhaPagamento folhaGerada = FolhaPagamento.builder()
                .id(0)
                .mesLancamento(mesAtual)
                .anoLancamento(anoAtual)
                .benValeTransporte(beneficios.getValeTransporte())
                .benPlanoSaude(beneficios.getPlanoSaude())
                .benAdicionalNoturno(beneficios.getAdicionalNoturno())
                .benSalarioFamilia(beneficios.getSalarioFamilia())
                .bonusDesempenho(bonificacoes.getBonusDesempenho())
                .bonusAniversario(bonificacoes.getBonusAniversario())
                .comissao(comissaoRecebida)
                .encInss(encargos.getInss())
                .encFgts(encargos.getFgts())
                .encSindical(encargos.getSindical())
                .salarioBruto(funcionario.getSalarioBruto())
                .matricula(funcionario)
                .build();
        folhaPagamentoDao.save(folhaGerada);
    }

//    @Qualifier("vendas")
//    @Autowired
//    RestTemplate mock;
// Caso não funcione, teste com o mock, basta descomentar
    public double solicitarTotalVendasMesVendedor(int matricula, int anoAtual, int mesAtual) throws Exception{

        String url = serverVendasLinks.getTotalVendas() + "/" + matricula + "/" + anoAtual + "/" + mesAtual;
        try {
//          ResponseEntity<VendasDTO> vendaMes = mock.getForEntity(url, VendasDTO.class);
            ResponseEntity<VendasDTO> vendaMes = rest.getForEntity(url, VendasDTO.class);
            return Objects.requireNonNull(vendaMes.getBody()).getValorVendas();
        }catch (Exception e){
            throw new Exception("Possivel falha ao se comunicar com servidor de Vendas.",e);
        }
    }

//    @Qualifier("pagamento")
//    @Autowired
//    RestTemplate mock;
// Caso não funcione, teste com o mock, basta descomentar
    private boolean solicitarPagamentoDeFuncionario(double salarioLiquido, int matricula) throws Exception {
        String url = serverPagamentosLinks.getSolicitarPagamento() + "/" + matricula + "/" + salarioLiquido;
        try {
//          ResponseEntity<RespostaPagamentoDTO> pagamentoRealizado = mock.getForEntity(url, RespostaPagamentoDTO.class);
            ResponseEntity<RespostaPagamentoDTO> pagamentoRealizado = rest.getForEntity(url, RespostaPagamentoDTO.class);

            return Objects.requireNonNull(pagamentoRealizado.getBody()).isPagamentoEfetuado();
        } catch (Exception e) {
            throw new GerarFolhaException("Possivel falha ao se comunicar com servidor de Pagamento.");
        }
    }

    private double calcularSalarioLiquido(Funcionario funcionario) {
        double salarioBruto = funcionario.getSalarioBruto();
        double totalBeneficios = salarioBeneficiosService.somarTodosBeneficios(funcionario);
        double totalBonificacoes = salarioBonificacoesService.somarTodasBonificacoes(funcionario);
        double totalEncargos = salarioEncargosService.somarTodosEncargos(salarioBruto);

        return salarioBruto + totalBeneficios + totalBonificacoes + totalEncargos;
    }

    public List<RelatorioFolhaPagamentoDTO> gerarRelatorio(List<FolhaPagamento> listaFolhasPagamento) {

        List<RelatorioFolhaPagamentoDTO> relatorioCompleto = new ArrayList<>();
        for(FolhaPagamento folha : listaFolhasPagamento){

            Funcionario funcionario = folha.getMatricula();
            var salario = funcionario.getSalarioBruto();
            var matricula = funcionario.getMatricula();

            RelatorioFolhaPagamentoDTO relatorioFuncionario = RelatorioFolhaPagamentoDTO.builder()
                    .mesLancamento(folha.getMesLancamento())
                    .anoLancamento(folha.getAnoLancamento())
                    .benValeTransporte(folha.getBenValeTransporte())
                    .benPlanoSaude(folha.getBenPlanoSaude())
                    .benAdicionalNoturno(folha.getBenAdicionalNoturno())
                    .benSalarioFamilia(folha.getBenSalarioFamilia())
                    .bonusDesempenho(folha.getBonusDesempenho())
                    .bonusAniversario(folha.getBonusAniversario())
                    .comissao(folha.getComissao())
                    .encInss(folha.getEncInss())
                    .encFgts(folha.getEncFgts())
                    .encSindical(folha.getEncSindical())
                    .salarioBase(salario)
                    .matricula(matricula)
                    .build();
            relatorioCompleto.add(relatorioFuncionario);
        }
        return relatorioCompleto;
    }

    public List<FolhaPagamento> buscarFolhasDePagametoFiltrandoPorTempo(int mes, int ano, int mesFinal, int anoFinal)throws Exception{
        try {
                return folhaPagamentoDao.findByDataLancamentoBetween((ano)*100 + mes, (anoFinal)*100 + mesFinal);
        }catch (Exception e){
            throw new BuscarFolhaException();
        }
    }
}
