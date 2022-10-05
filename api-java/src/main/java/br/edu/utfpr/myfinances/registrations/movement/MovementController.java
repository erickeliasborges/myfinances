package br.edu.utfpr.myfinances.registrations.movement;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movement")
public class MovementController extends GenericCrudController<Movement, Movement, Long> {

    @Autowired
    public MovementController(MovementService movementService) {
        super(movementService, Movement.class, Movement.class);
    }

}
