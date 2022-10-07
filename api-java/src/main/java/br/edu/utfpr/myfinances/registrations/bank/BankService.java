package br.edu.utfpr.myfinances.registrations.bank;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import br.edu.utfpr.myfinances.registrations.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return (accountRepository.findByBank_Id(id).isPresent());
    }

    @Override
    public String getMessageLinkedRegisterException() {
        return "Não é possível deletar pois o banco está em uso.";
    }

}
