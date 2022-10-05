package br.edu.utfpr.myfinances.registrations.bank;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends GenericCrudRepository<Bank, Long> {
}
