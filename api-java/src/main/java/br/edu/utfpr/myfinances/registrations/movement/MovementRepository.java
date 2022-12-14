package br.edu.utfpr.myfinances.registrations.movement;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends GenericCrudRepository<Movement, Long> {

    Optional<List<Movement>> findByAccount_Id(Long id);

    Optional<List<Movement>> findByCategory_Id(Long id);

}
