package br.com.gestao.recursoshumanos.Service.FolhaPagamento;

import br.com.gestao.recursoshumanos.Model.Funcionario;
import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.SalarioBonificacaoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SalarioBonificacoesService {
    public SalarioBonificacaoDTO retornarTodasBonificacoes(Funcionario funcionario){
        double adicionalDesempenho = adicionalDesempenho(funcionario.getNotaDesempenho(), funcionario.getSalarioBruto());
        double adicionalAniversario = adicionalAniversario(funcionario.getNascimento());

        SalarioBonificacaoDTO bonificacoes = new SalarioBonificacaoDTO();
        bonificacoes.setBonusDesempenho(adicionalDesempenho);
        bonificacoes.setBonusAniversario(adicionalAniversario);

        return bonificacoes;
    }

    public double somarTodasBonificacoes(Funcionario funcionario){
        double adicionalDesempenho = adicionalDesempenho(funcionario.getNotaDesempenho(), funcionario.getSalarioBruto());
        double adicionalAniversario = adicionalAniversario(funcionario.getNascimento());

        return adicionalAniversario + adicionalDesempenho;
    }
    private double adicionalDesempenho(int notaDesempenho, double salarioBruto) {
        return switch (notaDesempenho) {
            case 3 -> salarioBruto * 0.03;
            case 4 -> salarioBruto * 0.04;
            case 5 -> salarioBruto * 0.05;
            case 6 -> salarioBruto * 0.08;
            case 7 -> salarioBruto * 0.1;
            case 8 -> salarioBruto * 0.12;
            case 9 -> salarioBruto * 0.14;
            case 10 -> salarioBruto * 0.2;
            default -> 0;
        };
    }

    private double adicionalAniversario(LocalDate nascimento){
        int mesAgora = LocalDate.now().getMonthValue();
        int mesNaceu = nascimento.getMonthValue();

        return (mesNaceu == mesAgora) ? 50.00 : 0;
    }
}
