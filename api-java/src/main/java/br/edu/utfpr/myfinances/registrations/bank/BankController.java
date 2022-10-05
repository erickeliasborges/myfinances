package br.edu.utfpr.myfinances.registrations.bank;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank")
public class BankController extends GenericCrudController<Bank, Bank, Long> {

    @Autowired
    public BankController(BankService bankService) {
        super(bankService, Bank.class, Bank.class);
    }

}
