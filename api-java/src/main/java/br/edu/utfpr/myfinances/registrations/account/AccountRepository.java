package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends GenericCrudRepository<Account, Long> {
}
