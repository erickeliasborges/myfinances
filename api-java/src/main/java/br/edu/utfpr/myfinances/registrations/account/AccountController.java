package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController extends GenericCrudController<Account, Account, Long> {

    @Autowired
    public AccountController(AccountService accountService) {
        super(accountService, Account.class, Account.class);
    }

    //TODO: criar endpoint para mostrar saldo da conta, com base nas movimentacoes de receita(ganhos) e despesas

}
