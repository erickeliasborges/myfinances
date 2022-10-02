package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudController;
import br.edu.utfpr.myfinances.registrations.account.entitys.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController extends GenericCrudController<Account, Account, Long> {

    @Autowired
    public AccountController(AccountService accountService) {
        super(accountService, Account.class, Account.class);
    }

}
