package br.edu.utfpr.myfinances.registrations.user;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends GenericCrudRepository<User, Long> {

    User findByUsername(String username);

}
