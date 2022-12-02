package br.edu.utfpr.myfinances.registrations.bank;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import br.edu.utfpr.myfinances.registrations.account.Account;
import br.edu.utfpr.myfinances.registrations.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService extends GenericCrudService<Bank, Long> {

    private AccountRepository accountRepository;

    @Autowired
    public BankService(BankRepository bankRepository, AccountRepository accountRepository) {
        super(bankRepository);
        this.accountRepository = accountRepository;
    }

    @Override
    public Boolean linkedRegister(Long id) {
        Optional<List<Account>> optionalAccounts = accountRepository.findByBank_Id(id);
        return (optionalAccounts.isPresent()) && (!optionalAccounts.get().isEmpty());
    }

    @Override
    public String getMessageLinkedRegisterException() {
        return "Não é possível deletar pois o banco está em uso.";
    }

}
