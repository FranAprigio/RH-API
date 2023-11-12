package br.com.gestao.recursoshumanos;

import br.com.gestao.recursoshumanos.Model.*;
import br.com.gestao.recursoshumanos.Model.Funcionario;
import br.com.gestao.recursoshumanos.Service.DAO.DepartamentoDao;
import br.com.gestao.recursoshumanos.Service.DAO.FuncionarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class GestaoDeRhApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeRhApplication.class, args);
	}

	@Autowired
	FuncionarioDao funcdao;

	@Autowired
	DepartamentoDao departamentoDao;

	@Override
	public void run(String... args) throws Exception {

		Departamento dptCompras = new Departamento(1, DepartamentoNome.Compras,"Cps");
		departamentoDao.save(dptCompras);

		Departamento dptVendas = new Departamento(2, DepartamentoNome.Vendas,"Vds");
		departamentoDao.save(dptVendas);

		Departamento dptFinanceiro = new Departamento(3, DepartamentoNome.Pagamentos,"Fnc");
		departamentoDao.save(dptFinanceiro);

		Departamento dptRH = new Departamento(4, DepartamentoNome.Recursos_Humanos,"RH");
		departamentoDao.save(dptRH);

		Departamento dptCRM = new Departamento(5, DepartamentoNome.Customer_Relationship_Management,"CRM");
		departamentoDao.save(dptCRM);

//COMPRAS
		Funcionario funcCompras = new Funcionario();
		funcCompras.setNome("Zezin");
		funcCompras.setEmail("Ze@sgeu.com.br");
		funcCompras.setTelefone("1245663222");
		funcCompras.setCpf("66754871083");
		funcCompras.setNascimento(LocalDate.of(2000, 1, 8));
		funcCompras.setMatricula(1);
		funcCompras.setDepartamento(dptCompras);
		funcCompras.setCargo("Analista de Compras");
		funcCompras.setNumeroFilhos(1);
		funcCompras.setTrabalhadorNoturno(false);
		funcCompras.setRecebeValeTransporte(true);
		funcCompras.setRecebePlanoSaude(true);
		funcCompras.setSalarioBruto(3100.25);
		funcCompras.setFeedback("Compra tanto da China, que já é sócio do Porto de Santos.");
		funcCompras.setNotaDesempenho(7);
		funcCompras.setEPI(false);
		funcCompras.setPrecisaEPI(false);
		funcCompras.setDataContratacao(LocalDate.of(2015, 1, 8));

		funcdao.save(funcCompras);

		Funcionario funcCompras1 = new Funcionario();
		funcCompras1.setNome("Ana");
		funcCompras1.setEmail("ana@sgeu.com.br");
		funcCompras1.setTelefone("987654321");
		funcCompras1.setCpf("59116630076");
		funcCompras1.setNascimento(LocalDate.of(1995, 6, 15));
		funcCompras1.setMatricula(5);
		funcCompras1.setDepartamento(dptCompras);
		funcCompras1.setCargo("Assistente de Compras");
		funcCompras1.setNumeroFilhos(0);
		funcCompras1.setTrabalhadorNoturno(false);
		funcCompras1.setRecebeValeTransporte(true);
		funcCompras1.setRecebePlanoSaude(true);
		funcCompras1.setSalarioBruto(2500.0);
		funcCompras1.setFeedback("Excelente em encontrar fornecedores com preços competitivos.");
		funcCompras1.setNotaDesempenho(8);
		funcCompras1.setEPI(false);
		funcCompras1.setPrecisaEPI(false);
		funcdao.save(funcCompras1);

		Funcionario funcCompras2 = new Funcionario();
		funcCompras2.setNome("Pedro");
		funcCompras2.setEmail("pedro@sgeu.com.br");
		funcCompras2.setTelefone("123456789");
		funcCompras2.setCpf("80853113050");
		funcCompras2.setNascimento(LocalDate.of(1990, 3, 10));
		funcCompras2.setMatricula(3);
		funcCompras2.setDepartamento(dptCompras);
		funcCompras2.setCargo("Analista de Compras");
		funcCompras2.setNumeroFilhos(2);
		funcCompras2.setTrabalhadorNoturno(false);
		funcCompras2.setRecebeValeTransporte(true);
		funcCompras2.setRecebePlanoSaude(true);
		funcCompras2.setSalarioBruto(3200.0);
		funcCompras2.setFeedback("Negocia com habilidade e eficiência com os fornecedores.");
		funcCompras2.setNotaDesempenho(9);
		funcCompras2.setEPI(false);
		funcCompras2.setPrecisaEPI(false);

		funcdao.save(funcCompras2);

		Funcionario funcCompras3 = new Funcionario();
		funcCompras3.setNome("Mariana");
		funcCompras3.setEmail("mariana@sgeu.com.br");
		funcCompras3.setTelefone("987654321");
		funcCompras3.setCpf("00943889030");
		funcCompras3.setNascimento(LocalDate.of(1993, 9, 22));
		funcCompras3.setMatricula(4);
		funcCompras3.setDepartamento(dptCompras);
		funcCompras3.setCargo("Estagiário de Compras");
		funcCompras3.setNumeroFilhos(0);
		funcCompras3.setTrabalhadorNoturno(false);
		funcCompras3.setRecebeValeTransporte(true);
		funcCompras3.setRecebePlanoSaude(false);
		funcCompras3.setSalarioBruto(1500.0);
		funcCompras3.setFeedback("Aprende rapidamente e auxilia nas atividades de compra.");
		funcCompras3.setNotaDesempenho(7);
		funcCompras3.setEPI(false);
		funcCompras3.setPrecisaEPI(false);

		funcdao.save(funcCompras3);

//VENDAS

		Funcionario funcVendas = new Funcionario();
		funcVendas.setNome("Vitin");
		funcVendas.setEmail("Vit@@sgeu.com.br");
		funcVendas.setTelefone("12365986598");
		funcVendas.setCpf("99007529021");
		funcVendas.setNascimento(LocalDate.of(1995, 5, 15));
		funcVendas.setMatricula(2);
		funcVendas.setDepartamento(dptVendas);
		funcVendas.setCargo("Vendedor");
		funcVendas.setNumeroFilhos(2);
		funcVendas.setTrabalhadorNoturno(false);
		funcVendas.setRecebeValeTransporte(true);
		funcVendas.setRecebePlanoSaude(true);
		funcVendas.setSalarioBruto(1890.75);
		funcVendas.setFeedback("Já vendeu a empresa 2x só pra ter férias prolongada.");
		funcVendas.setNotaDesempenho(6);
		funcVendas.setEPI(true);
		funcVendas.setPrecisaEPI(true);

		funcdao.save(funcVendas);

		Funcionario funcVendas1 = new Funcionario();
		funcVendas1.setNome("Lucas");
		funcVendas1.setEmail("lucas@sgeu.com.br");
		funcVendas1.setTelefone("987654321");
		funcVendas1.setCpf("48518130077");
		funcVendas1.setNascimento(LocalDate.of(1992, 8, 20));
		funcVendas1.setMatricula(6);
		funcVendas1.setDepartamento(dptVendas);
		funcVendas1.setCargo("Vendedor Sênior");
		funcVendas1.setNumeroFilhos(1);
		funcVendas1.setTrabalhadorNoturno(false);
		funcVendas1.setRecebeValeTransporte(true);
		funcVendas1.setRecebePlanoSaude(true);
		funcVendas1.setSalarioBruto(2200.0);
		funcVendas1.setFeedback("Constrói relacionamentos sólidos com os clientes e fecha grandes negócios.");
		funcVendas1.setNotaDesempenho(9);
		funcVendas1.setEPI(true);
		funcVendas1.setPrecisaEPI(true);

		funcdao.save(funcVendas1);

		Funcionario funcVendas2 = new Funcionario();
		funcVendas2.setNome("Carolina");
		funcVendas2.setEmail("carolina@sgeu.com.br");
		funcVendas2.setTelefone("123456789");
		funcVendas2.setCpf("72566385076");
		funcVendas2.setNascimento(LocalDate.of(1988, 12, 5));
		funcVendas2.setMatricula(7);
		funcVendas2.setDepartamento(dptVendas);
		funcVendas2.setCargo("Vendedor");
		funcVendas2.setNumeroFilhos(3);
		funcVendas2.setTrabalhadorNoturno(false);
		funcVendas2.setRecebeValeTransporte(true);
		funcVendas2.setRecebePlanoSaude(true);
		funcVendas2.setSalarioBruto(1800.0);
		funcVendas2.setFeedback("Aborda clientes de forma amigável e persuasiva, resultando em vendas consistentes.");
		funcVendas2.setNotaDesempenho(8);
		funcVendas2.setEPI(true);
		funcVendas2.setPrecisaEPI(true);

		funcdao.save(funcVendas2);

		Funcionario funcVendas3 = new Funcionario();
		funcVendas3.setNome("Rafael");
		funcVendas3.setEmail("rafael@sgeu.com.br");
		funcVendas3.setTelefone("987654321");
		funcVendas3.setCpf("05580255012");
		funcVendas3.setNascimento(LocalDate.of(1991, 4, 12));
		funcVendas3.setMatricula(8);
		funcVendas3.setDepartamento(dptVendas);
		funcVendas3.setCargo("Vendedor");
		funcVendas3.setNumeroFilhos(0);
		funcVendas3.setTrabalhadorNoturno(false);
		funcVendas3.setRecebeValeTransporte(true);
		funcVendas3.setRecebePlanoSaude(false);
		funcVendas3.setSalarioBruto(1500.0);
		funcVendas3.setFeedback("Consegue identificar as necessidades dos clientes e oferecer soluções adequadas.");
		funcVendas3.setNotaDesempenho(7);
		funcVendas3.setEPI(false);
		funcVendas3.setPrecisaEPI(false);

		funcdao.save(funcVendas3);

//FINANCEIRO
		Funcionario funcFinanceiro = new Funcionario();
		funcFinanceiro.setNome("Jampier");
		funcFinanceiro.setEmail("Jampi@sgeu.com.br");
		funcFinanceiro.setTelefone("12361236598");
		funcFinanceiro.setCpf("03476183084");
		funcFinanceiro.setNascimento(LocalDate.of(1986, 6, 15));
		funcFinanceiro.setMatricula(9);
		funcFinanceiro.setDepartamento(dptFinanceiro);
		funcFinanceiro.setCargo("Diretor Financeiro");
		funcFinanceiro.setNumeroFilhos(0);
		funcFinanceiro.setTrabalhadorNoturno(false);
		funcFinanceiro.setRecebeValeTransporte(false);
		funcFinanceiro.setRecebePlanoSaude(true);
		funcFinanceiro.setSalarioBruto(6500.75);
		funcFinanceiro.setFeedback("Rouba que é um catiço.");
		funcFinanceiro.setNotaDesempenho(0);
		funcFinanceiro.setEPI(false);
		funcFinanceiro.setPrecisaEPI(false);

		funcdao.save(funcFinanceiro);

		Funcionario funcFinanceiro1 = new Funcionario();
		funcFinanceiro1.setNome("Laura");
		funcFinanceiro1.setEmail("laura@sgeu.com.br");
		funcFinanceiro1.setTelefone("987654321");
		funcFinanceiro1.setCpf("43825542092");
		funcFinanceiro1.setNascimento(LocalDate.of(1990, 9, 12));
		funcFinanceiro1.setMatricula(10);
		funcFinanceiro1.setDepartamento(dptFinanceiro);
		funcFinanceiro1.setCargo("Analista Financeiro");
		funcFinanceiro1.setNumeroFilhos(1);
		funcFinanceiro1.setTrabalhadorNoturno(false);
		funcFinanceiro1.setRecebeValeTransporte(true);
		funcFinanceiro1.setRecebePlanoSaude(true);
		funcFinanceiro1.setSalarioBruto(4000.0);
		funcFinanceiro1.setFeedback("Realiza análises financeiras com precisão e fornece relatórios detalhados.");
		funcFinanceiro1.setNotaDesempenho(8);
		funcFinanceiro1.setEPI(true);
		funcFinanceiro1.setPrecisaEPI(false);

		funcdao.save(funcFinanceiro1);

		Funcionario funcFinanceiro2 = new Funcionario();
		funcFinanceiro2.setNome("Marcelo");
		funcFinanceiro2.setEmail("marcelo@sgeu.com.br");
		funcFinanceiro2.setTelefone("123456789");
		funcFinanceiro2.setCpf("94276534003");
		funcFinanceiro2.setNascimento(LocalDate.of(1985, 7, 25));
		funcFinanceiro2.setMatricula(11);
		funcFinanceiro2.setDepartamento(dptFinanceiro);
		funcFinanceiro2.setCargo("Assistente Financeiro");
		funcFinanceiro2.setNumeroFilhos(2);
		funcFinanceiro2.setTrabalhadorNoturno(false);
		funcFinanceiro2.setRecebeValeTransporte(true);
		funcFinanceiro2.setRecebePlanoSaude(true);
		funcFinanceiro2.setSalarioBruto(2800.0);
		funcFinanceiro2.setFeedback("Organiza as finanças com eficiência, garantindo o cumprimento dos prazos.");
		funcFinanceiro2.setNotaDesempenho(7);
		funcFinanceiro2.setEPI(false);
		funcFinanceiro2.setPrecisaEPI(false);

		funcdao.save(funcFinanceiro2);

		Funcionario funcFinanceiro3 = new Funcionario();
		funcFinanceiro3.setNome("Roberto");
		funcFinanceiro3.setEmail("roberto@sgeu.com.br");
		funcFinanceiro3.setTelefone("987654321");
		funcFinanceiro3.setCpf("80897546008");
		funcFinanceiro3.setNascimento(LocalDate.of(1993, 2, 18));
		funcFinanceiro3.setMatricula(12);
		funcFinanceiro3.setDepartamento(dptFinanceiro);
		funcFinanceiro3.setCargo("Estagiário Financeiro");
		funcFinanceiro3.setNumeroFilhos(0);
		funcFinanceiro3.setTrabalhadorNoturno(false);
		funcFinanceiro3.setRecebeValeTransporte(true);
		funcFinanceiro3.setRecebePlanoSaude(false);
		funcFinanceiro3.setSalarioBruto(1500.0);
		funcFinanceiro3.setFeedback("Auxilia nas tarefas financeiras, aprendendo rapidamente.");
		funcFinanceiro3.setNotaDesempenho(6);
		funcFinanceiro3.setEPI(false);
		funcFinanceiro3.setPrecisaEPI(false);

		funcdao.save(funcFinanceiro3);

//CRM
		Funcionario funcCRM = new Funcionario();
		funcCRM.setNome("Claudinho");
		funcCRM.setEmail("Bochecha@sgeu.com.br");
		funcCRM.setTelefone("12361236577");
		funcCRM.setCpf("74390647040");
		funcCRM.setNascimento(LocalDate.of(2003, 7, 25));

		funcCRM.setMatricula(13);
		funcCRM.setDepartamento(dptCRM);
		funcCRM.setCargo("Analista de CRM III");
		funcCRM.setNumeroFilhos(3);
		funcCRM.setTrabalhadorNoturno(false);
		funcCRM.setRecebeValeTransporte(true);
		funcCRM.setRecebePlanoSaude(true);
		funcCRM.setSalarioBruto(4500.75);
		funcCRM.setFeedback("Só abre o Google Analytics e fica jogando Clash Royale no banheiro.");
		funcCRM.setNotaDesempenho(4);
		funcCRM.setEPI(false);
		funcCRM.setPrecisaEPI(false);

		funcdao.save(funcCRM);

		Funcionario funcCRM1 = new Funcionario();
		funcCRM1.setNome("Carla");
		funcCRM1.setEmail("carla@sgeu.com.br");
		funcCRM1.setTelefone("987654321");
		funcCRM1.setCpf("07153099000");
		funcCRM1.setNascimento(LocalDate.of(1992, 5, 12));
		funcCRM1.setMatricula(14);
		funcCRM1.setDepartamento(dptCRM);
		funcCRM1.setCargo("Analista de CRM II");
		funcCRM1.setNumeroFilhos(2);
		funcCRM1.setTrabalhadorNoturno(false);
		funcCRM1.setRecebeValeTransporte(true);
		funcCRM1.setRecebePlanoSaude(true);
		funcCRM1.setSalarioBruto(3800.0);
		funcCRM1.setFeedback("Realiza análises de dados do CRM com precisão e recomenda ações estratégicas.");
		funcCRM1.setNotaDesempenho(7);
		funcCRM1.setEPI(false);
		funcCRM1.setPrecisaEPI(false);

		funcdao.save(funcCRM1);

		Funcionario funcCRM2 = new Funcionario();
		funcCRM2.setNome("Ricardo");
		funcCRM2.setEmail("ricardo@sgeu.com.br");
		funcCRM2.setTelefone("123456789");
		funcCRM2.setCpf("25307022093");
		funcCRM2.setNascimento(LocalDate.of(1991, 9, 18));
		funcCRM2.setMatricula(15);
		funcCRM2.setDepartamento(dptCRM);
		funcCRM2.setCargo("Analista de CRM I");
		funcCRM2.setNumeroFilhos(1);
		funcCRM2.setTrabalhadorNoturno(false);
		funcCRM2.setRecebeValeTransporte(true);
		funcCRM2.setRecebePlanoSaude(true);
		funcCRM2.setSalarioBruto(3200.0);
		funcCRM2.setFeedback("Identifica padrões nos dados do CRM e oferece insights valiosos para a equipe de vendas.");
		funcCRM2.setNotaDesempenho(8);
		funcCRM2.setEPI(false);
		funcCRM2.setPrecisaEPI(false);

		funcdao.save(funcCRM2);

		Funcionario funcCRM3 = new Funcionario();
		funcCRM3.setNome("Mariana");
		funcCRM3.setEmail("mariana@sgeu.com.br");
		funcCRM3.setTelefone("987654321");
		funcCRM3.setCpf("03551075034");
		funcCRM3.setNascimento(LocalDate.of(1994, 2, 10));
		funcCRM3.setMatricula(16);
		funcCRM3.setDepartamento(dptCRM);
		funcCRM3.setCargo("Estagiário de CRM");
		funcCRM3.setNumeroFilhos(0);
		funcCRM3.setTrabalhadorNoturno(false);
		funcCRM3.setRecebeValeTransporte(true);
		funcCRM3.setRecebePlanoSaude(false);
		funcCRM3.setSalarioBruto(1500.0);
		funcCRM3.setFeedback("Aprende rapidamente e auxilia na gestão dos dados do CRM.");
		funcCRM3.setNotaDesempenho(8);
		funcCRM3.setEPI(false);
		funcCRM3.setPrecisaEPI(false);

		funcdao.save(funcCRM3);

		Funcionario funcRh = new Funcionario();
		funcRh.setNome("Wanderleison");
		funcRh.setEmail("Sonson@sgeu.com.br");
		funcRh.setTelefone("55561236577");
		funcRh.setCpf("20419450017");
		funcRh.setNascimento(LocalDate.of(1999, 8, 25));
		funcRh.setMatricula(17);
		funcRh.setDepartamento(dptRH);
		funcRh.setCargo("Gestor de RH");
		funcRh.setNumeroFilhos(3);
		funcRh.setTrabalhadorNoturno(true);
		funcRh.setRecebeValeTransporte(true);
		funcRh.setRecebePlanoSaude(true);
		funcRh.setSalarioBruto(3800.55);
		funcRh.setFeedback("Trabalhador, honesto, disciplinado, competente, esforçado, o pilar da empresa.");
		funcRh.setNotaDesempenho(10);
		funcRh.setEPI(false);
		funcRh.setPrecisaEPI(false);

		funcdao.save(funcRh);
	}
}
