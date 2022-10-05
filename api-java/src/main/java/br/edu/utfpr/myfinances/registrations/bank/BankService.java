package br.edu.utfpr.myfinances.registrations.bank;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService extends GenericCrudService<Bank, Long> {

    @Autowired
    public BankService(BankRepository bankRepository) {
        super(bankRepository);
    }

}
