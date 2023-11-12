package br.com.gestao.recursoshumanos.Service.FolhaPagamento;

import br.com.gestao.recursoshumanos.Service.DTO.FolhaPagamneto.SalarioEncargosDTO;
import org.springframework.stereotype.Service;

@Service
public class SalarioEncargosService {
    public SalarioEncargosDTO retornarTodosEncargos(double salarioBruto) {

        double inss = descontoINSS(salarioBruto);
        double fgts = salarioBruto * 0.08;

       // Como é pago 1 dia a mais, dividi o salário de forma que esse adicional venha parcelado ao longo do ano
        double contribuicaoSindical = salarioBruto / 22 / 12;

        SalarioEncargosDTO engargos = new SalarioEncargosDTO();
        engargos.setFgts(fgts);
        engargos.setInss(inss);
        engargos.setSindical(contribuicaoSindical);
        return engargos;
    }

    public double somarTodosEncargos(double salarioBruto) {

        double inss = descontoINSS(salarioBruto);
        double fgts = salarioBruto * 0.08;
        double contribuicaoSindical = salarioBruto / 22 / 12;

        return inss + fgts + contribuicaoSindical;
    }

    private double descontoINSS(double salarioBruto){
        double taxa = 0;

        if(salarioBruto <= 1320.00){
            taxa = 7.5;
        }else if(salarioBruto <= 2571.29){
            taxa = 9;
        }else if(salarioBruto <= 3856.94){
            taxa = 12;
        }else {
            taxa = 14;
        }

        return salarioBruto * taxa/100;
    }
}
