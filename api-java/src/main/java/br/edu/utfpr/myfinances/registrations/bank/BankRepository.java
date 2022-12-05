package br.edu.utfpr.myfinances.registrations.bank;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends GenericCrudRepository<Bank, Long> {

    Optional<Bank> findByName(String name);

}
