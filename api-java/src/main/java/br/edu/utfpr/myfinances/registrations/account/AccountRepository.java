package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import br.edu.utfpr.myfinances.registrations.account.entitys.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends GenericCrudRepository<Account, Long> {
}
