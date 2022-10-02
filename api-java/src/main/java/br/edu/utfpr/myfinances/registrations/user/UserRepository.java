package br.edu.utfpr.myfinances.registrations.user;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericCrudRepository<User, Long> {

    User findByUsername(String username);

}
