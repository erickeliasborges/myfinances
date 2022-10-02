package br.edu.utfpr.myfinances.registrations.movement;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import br.edu.utfpr.myfinances.registrations.movement.entitys.Movement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementService extends GenericCrudService<Movement, Long> {

    @Autowired
    public MovementService(MovementRepository movementRepository) {
        super(movementRepository);
    }

}
