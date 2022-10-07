package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import br.edu.utfpr.myfinances.registrations.movement.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends GenericCrudService<Account, Long> {

    private MovementRepository movementRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, MovementRepository movementRepository) {
        super(accountRepository);
        this.movementRepository = movementRepository;
    }

    @Override
    public Boolean linkedRegister(Long id) {
        return (movementRepository.findByAccount_Id(id).isPresent());
    }

    @Override
    public String getMessageLinkedRegisterException() {
        return "Não é possível deletar pois a conta está em uso.";
    }

}
