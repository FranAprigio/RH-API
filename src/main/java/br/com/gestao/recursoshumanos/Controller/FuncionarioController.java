package br.com.gestao.recursoshumanos.Controller;

import br.com.gestao.recursoshumanos.Error.Funcionario.BuscarFuncionarioException;
import br.com.gestao.recursoshumanos.Error.Funcionario.EditarDesempenhoException;
import br.com.gestao.recursoshumanos.Error.Funcionario.ValidarFuncionarioException;
import br.com.gestao.recursoshumanos.Error.Funcionario.EditarFeedbackException;
import br.com.gestao.recursoshumanos.Error.Funcionario.EditarSalarioException;
import br.com.gestao.recursoshumanos.Model.Funcionario;
import br.com.gestao.recursoshumanos.Service.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    FuncionarioService fServ;

    @Operation(description = "O serviço valida ou não o pedido de férias de um funcionário.")
    @ApiResponse(description = "Funcionario pode tirar férias.")
    @GetMapping(value = "/ferias/{id}")
    public String feriasFuncionario(@PathVariable int id) throws Exception {
        return fServ.feriasFuncionario(id);
    }

    @Operation(description = "O serviço demite o funcionario e muda o status como ex-funcionario")
    @Parameter(description = "Id do funcionario que será demitido")
    @ApiResponse(description = "Funcionario demitido")
    @GetMapping("/{id}")
    public Funcionario buscarFuncionarioById(@PathVariable int id) throws Exception {
        return fServ.buscarFuncionario(id);
    }

    @Operation(description = "O serviço lista todos os funcionários da empresa.")
    @Parameter(description = "")
    @ApiResponse(description = "Lista com todos funcionários")
    @GetMapping("/todos")
    public ResponseEntity<?> listarTodosFuncionarios() throws Exception {
        List<Funcionario> todosFuncionarios = fServ.listarFuncionarios();
        return ResponseEntity.ok(todosFuncionarios);
    }

    @Operation(description = "O serviço verifica se o cpf do funcionario é valido")
    @Parameter(description = "O cpf do funcionario")
    @ApiResponse(description = "Salario alterado")
    @GetMapping("/verificar-cpf/{cpf}")
    public ResponseEntity<?> verificarCPF(@PathVariable String cpf) {
        if (fServ.validarCPF(cpf)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }


//POST
    @Operation(description = "O serviço contrata um funcionario com os dados herdados de pessoa e adicionando os dados do funcionario")
    @Parameter(description = "Dados do funcionario")
    @ApiResponse(description = "Funcionario contratado")
    @PostMapping
    public ResponseEntity<?> contratarFuncionario(@Valid @RequestBody Funcionario funcionario) {
        try {
            LocalDate dataNascimento = funcionario.getNascimento();
            LocalDate dataAtual = LocalDate.now();
            int idade = Period.between(dataNascimento, dataAtual).getYears();

            if (idade >= 18 && fServ.isValid(funcionario)) {
                return ResponseEntity.ok(fServ.save(funcionario));
            } else if (idade < 18) {
                return ResponseEntity.badRequest().body("O funcionário deve ser maior de 18 anos.");
            } else {
                return ResponseEntity.badRequest().body("Funcionário não atende aos requisitos de contratação.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao contratar funcionário.");
        }
    }

    @Operation(description = "O serviço edita o desempenho do funcionario")
    @Parameter(description = "Id do funcionario que tera seu desempenho editado e o desempenho determinado")
    @ApiResponse(description = "Desempenho alterado")
    @PutMapping(value = "/{id}/desempenho")
    public Funcionario editarDesempenho(@Valid @PathVariable int id, @RequestBody int nvDesempenho)throws Exception {
        return fServ.editarDesempenho(nvDesempenho, id);
    }

    @Operation(description = "O serviço edita o desempenho do funcionario")
    @Parameter(description = "Id do funcionario que tera seu desempenho editado e o desempenho determinado")
    @ApiResponse(description = "Desempenho alterado")
    @PutMapping(value = "/{id}/feedback")
    public Funcionario editarFeedback(@Valid @PathVariable int id, @RequestBody String nvFeedback) throws Exception {
        return fServ.editarFeedback(nvFeedback, id);
    }

    @Operation(description = "O serviço altera o salario do funcionario")
    @Parameter(description = "Id do funcionario que tera seu salario alterado e o novo salario determinado")
    @ApiResponse(description = "Salario alterado")
    @PutMapping(value = "/{id}/salario")
    public Funcionario editarSalario(@Valid @PathVariable int id, @RequestBody double nvSalario) throws Exception {
        return fServ.editarSalario(nvSalario, id);
    }

//DELETE
    @Operation(description = "O serviço demite o funcionario e muda o status como ex-funcionario")
    @Parameter(description = "Id do funcionario que será demitido")
    @ApiResponse(description = "funcionario demitido")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> demitirFuncionario(@PathVariable int id)throws Exception {
        Funcionario funcionario = fServ.demitirFuncionario(id);
        return ResponseEntity.ok("Funcionário " + id + " demitido");
    }
    @Operation(description = "O serviço retorna a promoção do funcionario")
    @Parameter(description = "Passamos o id e verifica se o funcionario esta no banco")
    @ApiResponse(description = "Funcionario promovido")
    @PostMapping(value = "/`{id}/promover")
    public ResponseEntity<String> promoverFuncionario(@PathVariable int id, @Valid @RequestBody Funcionario funcionario) throws Exception{
        fServ.buscarFuncionario(id);
        fServ.promoverFuncionario(funcionario,id);
        return ResponseEntity.ok("Funcionario " + id + " Promovido");
    }

    @ExceptionHandler(BuscarFuncionarioException.class)
    public ResponseEntity<?> BuscarFuncionarioException(BuscarFuncionarioException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(ValidarFuncionarioException.class)
    public ResponseEntity<?> ValidarFuncionarioException(ValidarFuncionarioException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(EditarDesempenhoException.class)
    public ResponseEntity<?> EditarDesempenhoException(EditarDesempenhoException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(EditarFeedbackException.class)
    public ResponseEntity<?> EditarFeedbackException(EditarFeedbackException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(EditarSalarioException.class)
    public ResponseEntity<?> EditarSalarioException(EditarSalarioException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}