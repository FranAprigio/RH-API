package br.com.gestao.recursoshumanos.Service;

import br.com.gestao.recursoshumanos.Error.Funcionario.BuscarFuncionarioException;
import br.com.gestao.recursoshumanos.Error.Funcionario.EditarDesempenhoException;
import br.com.gestao.recursoshumanos.Error.Funcionario.EditarFeedbackException;
import br.com.gestao.recursoshumanos.Error.Funcionario.EditarSalarioException;
import br.com.gestao.recursoshumanos.Error.Funcionario.ValidarFuncionarioException;
import br.com.gestao.recursoshumanos.Model.ExFuncionario;
import br.com.gestao.recursoshumanos.Model.Funcionario;
import br.com.gestao.recursoshumanos.Service.DAO.ExFuncionarioDao;
import br.com.gestao.recursoshumanos.Service.DAO.FuncionarioDao;
import br.com.gestao.recursoshumanos.Service.DTO.Funcionario.ExisteEPIDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioDao fDao;

    @Autowired
    ExFuncionarioDao exFDao;

    @Autowired
    static
    RestTemplate rest;

    public boolean isValid(Funcionario funcionario) throws Exception {
        try {
            return (funcionario.getSalarioBruto() >= 1500 &&
                    (funcionario.getNotaDesempenho() >= 5));
        } catch (Exception e) {
            throw new ValidarFuncionarioException();
        }
    }

    public Funcionario save(Funcionario funcionario) {
        return fDao.save(funcionario);
    }

    public Funcionario demitirFuncionario(int id) throws Exception {
        Funcionario funcionario = buscarFuncionario(id);
        ExFuncionario exFuncionario = mapearFuncionarioParaExFuncionario(funcionario);
        try {
            fDao.delete(funcionario);
            return funcionario;
        }
        catch (Exception e){
            throw new Exception("Falha ao demitir funcionário.", e);
        }
    }

    private ExFuncionario mapearFuncionarioParaExFuncionario(Funcionario funcionario) {
        ExFuncionario exFuncionario = new ExFuncionario();
        exFuncionario.setNome(funcionario.getNome());
        exFuncionario.setId(funcionario.getId());
        exFuncionario.setCargo(funcionario.getCargo());
        exFuncionario.setDepartamento(funcionario.getDepartamento());
        exFuncionario.setEmail(funcionario.getEmail());
        exFuncionario.setFeedback(funcionario.getFeedback());
        exFuncionario.setMatricula(funcionario.getMatricula());
        exFuncionario.setCpf(funcionario.getCpf());
        exFuncionario.setSalarioBruto(funcionario.getSalarioBruto());
        exFuncionario.setNumeroFilhos(funcionario.getNumeroFilhos());
        exFuncionario.setTelefone(funcionario.getTelefone());
        exFuncionario.setNascimento(funcionario.getNascimento());
        exFuncionario.setDataDemissao(LocalDate.now());
        exFDao.save(exFuncionario);
        return exFuncionario;
    }

    public Funcionario buscarFuncionario(int id) throws Exception {

        Optional<Funcionario> op = fDao.findById(id);

        if (op.isPresent()) {
            return op.get();
        } else {
            throw new BuscarFuncionarioException();
        }
    }

    public Funcionario editarDesempenho(int nvDesempenho, int id) throws Exception {
        if (nvDesempenho <= 10) {
            Funcionario funcionario = buscarFuncionario(id);
            funcionario.setNotaDesempenho(nvDesempenho);
            fDao.save(funcionario);
            return funcionario;
        } else {
            throw new EditarDesempenhoException();
        }
    }

    public Funcionario editarFeedback(String nvFeedback, int id) throws Exception {
        try {
            Funcionario funcionario = buscarFuncionario(id);
            funcionario.setFeedback(nvFeedback);
            fDao.save(funcionario);
            return funcionario;
        } catch (Exception e) {
            throw new EditarFeedbackException();
        }
    }

    public Funcionario editarSalario(double nvSalario, int id) throws Exception {
        try {
            Funcionario funcionario = buscarFuncionario(id);
            funcionario.setSalarioBruto(nvSalario);
            fDao.save(funcionario);
            return funcionario;
        } catch (Exception e) {
            throw new EditarSalarioException();
        }
    }

    public List<Funcionario> buscaAll() throws BuscarFuncionarioException {
        List<Funcionario> todosFuncionariosEncontrados = fDao.findAll();

        if ((long) todosFuncionariosEncontrados.size() == 0) throw new BuscarFuncionarioException();
        return todosFuncionariosEncontrados;
    }

    public boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }

        if (digito1 != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }

        return digito2 == Character.getNumericValue(cpf.charAt(10));
    }

    public String feriasFuncionario(int id) throws Exception{
        Funcionario funcionario = this.buscarFuncionario(id);

        LocalDate dataContratacao = funcionario.getDataContratacao();
        LocalDate currentDate = LocalDate.now();

        long monthsDifference = ChronoUnit.MONTHS.between(dataContratacao, currentDate);
        if(monthsDifference >= 12) {
            return "O Funcionário"+ funcionario.getNome()+"pode tirar férias";
        } else {
            return "O Funcionário"+ funcionario.getNome()+" não pode tirar férias";
        }
    }

    public boolean promoverFuncionario(Funcionario funcionarioExistente, int id) {

        if (funcionarioExistente != null) {
            String novoCargo = "Analista de CRM I";
            double percentualAumento = 30.0;
            double aumento = funcionarioExistente.getSalarioBruto() * (percentualAumento / 100);
            double novoSalario = funcionarioExistente.getSalarioBruto() + aumento;
            if (funcionarioExistente.getCargo().equals("Estagiário de CRM") && funcionarioExistente.getNotaDesempenho() >= 6
                    && funcionarioExistente.getSalarioBruto() < novoSalario && funcionarioExistente.getId() == id) {
                funcionarioExistente.setCargo(novoCargo);
                funcionarioExistente.setSalarioBruto(novoSalario);
                return true;
            }
        }
        return false;
    }
    public boolean solicitarEPI(double idEPI, int matricula) throws Exception {
        Funcionario funcionario = this.buscarFuncionario(matricula);
        boolean precisaEPI = funcionario.isPrecisaEPI() && "Vendas".equalsIgnoreCase(String.valueOf(funcionario.getDepartamento()));

        if (!precisaEPI) {
            return false;
        }

        String url = "http://compras-sgeu.up.railway.app/estoque/verificar-epi/" + matricula + "/" + idEPI;
        try {
            ResponseEntity<ExisteEPIDTO> response = rest.getForEntity(url, ExisteEPIDTO.class);
            boolean ExisteEPIDTO = response.getBody().estoque;

            if (!ExisteEPIDTO) {
                return false;
            }
            return precisaEPI;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao conectar com o servidor de Compras").hasBody();
        }
    }
    public void verificarEPI(Funcionario funcionario) throws Exception {
        if ("Vendas".equalsIgnoreCase(String.valueOf(funcionario.getDepartamento()))) {
            boolean epiSolicitado = solicitarEPI(1, funcionario.getMatricula());
            funcionario.setPrecisaEPI(epiSolicitado);
        }
    }
    public List<Funcionario> listarFuncionarios() throws Exception{
        try {
            return fDao.findAll();
        }catch (Exception e){
            throw new Exception("Possível erro ao comunicar com o Banco de Dados. Erro ao listar funcionários.",e);
        }
    }
}