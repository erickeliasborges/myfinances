package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends GenericCrudService<Account, Long> {

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        super(accountRepository);
    }

}
