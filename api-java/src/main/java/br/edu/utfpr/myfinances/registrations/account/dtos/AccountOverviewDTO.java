package br.edu.utfpr.myfinances.registrations.account.dtos;

import br.edu.utfpr.myfinances.registrations.account.Account;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountOverviewDTO {

    private Account account;
    private BigDecimal saldo;
    private BigDecimal saldoPrevisto;
    private BigDecimal receitas;
    private BigDecimal receitasPrevistas;
    private BigDecimal despesas;
    private BigDecimal despesasPrevistas;
    private BigDecimal transferenciasRecebidas;
    private BigDecimal transferenciasRecebidasPrevistas;
    private BigDecimal transferenciasEnviadas;
    private BigDecimal transferenciasEnviadasPrevistas;

}
