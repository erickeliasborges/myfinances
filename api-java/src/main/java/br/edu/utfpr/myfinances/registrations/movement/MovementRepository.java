package br.edu.utfpr.myfinances.registrations.movement;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import br.edu.utfpr.myfinances.registrations.movement.entitys.Movement;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends GenericCrudRepository<Movement, Long> {
}
