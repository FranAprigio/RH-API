package br.com.gestao.recursoshumanos.Service.FolhaPagamento;

import br.com.gestao.recursoshumanos.Model.Funcionario;
import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.SalarioBeneficiosDTO;
import org.springframework.stereotype.Service;

@Service
public class SalarioBeneficiosService {

    static final double PRECO_VALE_TRANSPORTE_EM_REAIS = 3.00;
    static final int TOTAL_DIAS_TRABALHADOS_MES = 22;
    static final double VALOR_PLANO_DE_SAUDE_EM_REAIS = 300.00;
    static final double PORCENTAGEM_ADICIONAL_NOTURNO_HORA_TRABALHADA = 20;
    static final double VALOR_MAXIMO_EM_REAIS_PARA_RECEBER_SALARIO_FAMILIA = 1754.18;
    static final double VALOR_POR_FILHO_EM_REAIS = 56.47;

    public SalarioBeneficiosDTO retornarTodosBeneficios(Funcionario funcionario ) {

        double adicionalValeTransporte = adicionalValeTransporte(funcionario.isRecebeValeTransporte(),funcionario.getSalarioBruto());

        double adicionalPlanoSaude = adicionalPlanoSaude(funcionario.isRecebePlanoSaude());

        double adicionalNoturno = adicionalNoturno(funcionario.isTrabalhadorNoturno(), funcionario.getSalarioBruto());

        double totalAcrescentado = adicionalNoturno + adicionalPlanoSaude + adicionalValeTransporte;

        double adicionalSalarioFamilia = adicionalSalarioFamilia(funcionario.getNumeroFilhos(),
                funcionario.getSalarioBruto() + totalAcrescentado);

        SalarioBeneficiosDTO beneficios = new SalarioBeneficiosDTO();
        beneficios.setValeTransporte(adicionalValeTransporte);
        beneficios.setPlanoSaude(adicionalPlanoSaude);
        beneficios.setAdicionalNoturno(adicionalNoturno);
        beneficios.setSalarioFamilia(adicionalSalarioFamilia);

        return beneficios;
    }

    public double somarTodosBeneficios(Funcionario funcionario ) {

        double adicionalValeTransporte = adicionalValeTransporte(funcionario.isRecebeValeTransporte(), funcionario.getSalarioBruto());
        double adicionalPlanoSaude = adicionalPlanoSaude(funcionario.isRecebePlanoSaude());
        double adicionalNoturno = adicionalNoturno(funcionario.isTrabalhadorNoturno(), funcionario.getSalarioBruto());

        double totalAcrescentado = adicionalNoturno + adicionalPlanoSaude + adicionalValeTransporte;

        double adicionalSalarioFamilia = adicionalSalarioFamilia(funcionario.getNumeroFilhos(),
                funcionario.getSalarioBruto() + totalAcrescentado);

        return totalAcrescentado + adicionalSalarioFamilia;
    }

    private double adicionalValeTransporte(boolean recebeVale, double salarioBruto){
        if(!recebeVale){
            return 0;
        }

        double descontoMaximoSalarioFuncionario = salarioBruto * 0.06;

        double valorGastosEmPassagensMensais = PRECO_VALE_TRANSPORTE_EM_REAIS * TOTAL_DIAS_TRABALHADOS_MES * 2;
        double descontoFuncionario = Math.min(valorGastosEmPassagensMensais, descontoMaximoSalarioFuncionario);

        return valorGastosEmPassagensMensais - descontoFuncionario;
    }

    private double adicionalPlanoSaude(boolean recebePlano){
        if(!recebePlano){
            return 0;
        }
        return VALOR_PLANO_DE_SAUDE_EM_REAIS / 2;
    }

    private double adicionalNoturno(boolean trabalhaNoite, double salarioBruto){
        if(!trabalhaNoite){
            return 0;
        }

        double valorHoraServico = salarioBruto / 8 / 22 ;

        return valorHoraServico * 7 * 0.20 * 22;
    }

    private double adicionalSalarioFamilia(int filhos, double salario){
        if (filhos <= 0 || salario > VALOR_MAXIMO_EM_REAIS_PARA_RECEBER_SALARIO_FAMILIA){
            return 0;
        }

        return filhos > 4 ? VALOR_POR_FILHO_EM_REAIS * 4 : VALOR_POR_FILHO_EM_REAIS * filhos;
    }
}
