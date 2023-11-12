package br.com.gestao.recursoshumanos.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import br.com.gestao.recursoshumanos.Error.Treinamento.*;
import br.com.gestao.recursoshumanos.Model.Treinamento;
import br.com.gestao.recursoshumanos.Service.DTO.Treinamento.TreinamentoDTO;
import br.com.gestao.recursoshumanos.Service.TreinamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/treinamento")
public class TreinamentoController {
    @Autowired
    TreinamentoService treinamentoService;

    @Operation(description = "O serviço buscar o treinamento pelo ID")
    @Parameter(description = "ID do treinamento")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarTreinamentoID(@PathVariable int id) throws Exception {
        var treinamento = treinamentoService.obterTreinamentoPorID(id);
        return ResponseEntity.ok().body(treinamento);
    }
    @Operation(description = "O serviço lista todos os treinamentos no serviço")
    @Parameter(description = "")
    @ApiResponse(description = "Treinamentos Listadas")
    @GetMapping(value = "/listar")
    public ResponseEntity<?> listarTreinamento(@RequestParam(value = "skip", defaultValue = "0") int skip,
                                           @RequestParam(value = "take", defaultValue = "15") int take)
            throws Exception{
        var lista = treinamentoService.obterListaTreinamento(skip,take);
        return ResponseEntity.ok().body(lista);
    }

    @Operation(description = "O serviço lista todos os treinamentos no serviço")
    @Parameter(description = "Digite o nome do treinamento")
    @ApiResponse(description = "Treinamentos Listadas")
    @PostMapping(name = "/criar")
    public ResponseEntity<?> criarTreinamento(@Valid @RequestBody TreinamentoDTO treinamentoDTO) throws Exception{
        treinamentoService.isValid(treinamentoDTO);
        Treinamento nvTreinamento = treinamentoService.criarTreinamento(treinamentoDTO);
        Treinamento treinamentoSalva = treinamentoService.salvarTreinamento(nvTreinamento);
        return ResponseEntity.status(HttpStatus.OK).body(treinamentoSalva);
    }

    @Operation(description = "O serviço edita o nome do treinamento selecionado")
    @Parameter(description = "O nome do treinamento e o id desse treinamento")
    @ApiResponse(description = "Nome do Treinamento editado")
    @PatchMapping(value = "/editar/nome/{id}")
    public ResponseEntity<?> editarNome(@RequestBody String nvNome, @PathVariable int id) throws Exception{
        treinamentoService.obterTreinamentoPorID(id);
        treinamentoService.editarCampoNome(nvNome, id);
        return ResponseEntity.ok().body("Campo editado com sucesso!");
    }

    @Operation(description = "O serviço edita os dados do treinamento selecionado")
    @Parameter(description = "O nome do treinamento e o id desse treinamento")
    @ApiResponse(description = "ID do Treinamento editado")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarTreinamento(@Valid @RequestBody TreinamentoDTO treinamentoEditada, @PathVariable int id)throws Exception{
        Treinamento treinamentoOriginal = treinamentoService.obterTreinamentoPorID(id);
        treinamentoService.editarTreinamento(treinamentoOriginal, treinamentoEditada);
        return ResponseEntity.ok().body(treinamentoOriginal);
    }

    @Operation(description = "O serviço exclui o treinamento selecionado")
    @Parameter(description = "O nome do treinamento e o id desse treinamento")
    @ApiResponse(description = "Treinamento excluido")
    @DeleteMapping(name = "/apagar", value = "")
    public ResponseEntity<?> apagarTreinamento(@RequestParam("id") int id)throws Exception{
        treinamentoService.obterTreinamentoPorID(id);
        treinamentoService.apagarTreinamento(id);
        return ResponseEntity.status(HttpStatus.OK).body("Treinamento removido.");
    }

    @ExceptionHandler(BuscaException.class)
    public ResponseEntity<?> handleBuscaException(BuscaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(AdicaoException.class)
    public ResponseEntity<?> handleAdicaoException(AdicaoException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(AuteracaoException.class)
    public ResponseEntity<?> handleAuteracaoException(AuteracaoException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(ApagarException.class)
    public ResponseEntity<?> handleApagarException(ApagarException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(ObjetoExistenteException.class)
    public ResponseEntity<?> handleObjetoExistenteException(ObjetoExistenteException e){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
