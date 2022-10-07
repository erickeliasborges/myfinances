package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends GenericCrudRepository<Account, Long> {

    Optional<List<Account>> findByBank_Id(Long bank_id);

    Optional<List<Account>> findByUser_Id(Long user_id);

}
