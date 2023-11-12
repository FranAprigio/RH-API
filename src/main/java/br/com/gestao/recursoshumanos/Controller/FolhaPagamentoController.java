package br.com.gestao.recursoshumanos.Controller;

import java.time.LocalDate;
import java.util.List;

import br.com.gestao.recursoshumanos.Error.FolhaPagamento.BuscarFolhaException;
import br.com.gestao.recursoshumanos.Error.FolhaPagamento.FolhaExistenteException;
import br.com.gestao.recursoshumanos.Error.FolhaPagamento.GerarFolhaException;
import br.com.gestao.recursoshumanos.Model.FolhaPagamento;
import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.RelatorioFolhaPagamentoDTO;
import br.com.gestao.recursoshumanos.Service.FolhaPagamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/folha/pagamento")
public class FolhaPagamentoController {
    @Autowired
    FolhaPagamentoService folhaPagamentoService;
    @GetMapping()
    @Operation(description = "O serviço busca todas folhas geradas.")
    @ApiResponse(description = "Histórico de pagamento.")
    public List<FolhaPagamento>buscarTodasFolhasPagamento() {
        return folhaPagamentoService.buscarTodasFolhasPagamento();
    }

//GET
    @Operation(description = "O serviço gera o relatorio da folha de pagamento no periodo determinado")
    @Parameter(description = "Mês inicial, Ano inicial, Mês final, Ano final")
    @ApiResponse(description = "Relatório gerado")
    @GetMapping(value = "/relatorio")
    public ResponseEntity<List<RelatorioFolhaPagamentoDTO>> gerarRelatorioMes(
            @RequestParam(name = "iniciomes", defaultValue = "1") int mes,
            @RequestParam(name = "inicioano", defaultValue = "2023") int ano,
            @RequestParam(name = "finalmes", defaultValue = "12") int mesFinal,
            @RequestParam(name = "finalano", defaultValue = "2023") int anoFinal) throws Exception {

        List<FolhaPagamento> todasFolhasGeradasNoPeriodoEscolhido = folhaPagamentoService.buscarFolhasDePagametoFiltrandoPorTempo(mes, ano, mesFinal, anoFinal);
        return ResponseEntity.ok(folhaPagamentoService.gerarRelatorio(todasFolhasGeradasNoPeriodoEscolhido));
    }

//POST
    @PostMapping("/gerar")
    @Operation(description = "O serviço gera o a folha de pagamento do mês atual")
    @ApiResponse(description = "Folha de pagamentos gerada com sucesso!")
    public ResponseEntity<String> gerarFolhaPagamento() throws Exception{
        LocalDate dataAtual = LocalDate.now();

        boolean pedidoValido = folhaPagamentoService.isValid(dataAtual);

        folhaPagamentoService.gerarFolhaPagamentoMensal(dataAtual.getMonthValue(), dataAtual.getYear());
        return ResponseEntity.ok("Folha de pagamentos gerada com sucesso!");
    }

    @ExceptionHandler(GerarFolhaException.class)
    public ResponseEntity<?> GerarFolhaException(GerarFolhaException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(FolhaExistenteException.class)
    public ResponseEntity<?> FolhaExistenteException(FolhaExistenteException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(BuscarFolhaException.class)
    public ResponseEntity<?> BuscarFolhaException(BuscarFolhaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
