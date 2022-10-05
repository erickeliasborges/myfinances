package br.edu.utfpr.myfinances.registrations.movement;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementService extends GenericCrudService<Movement, Long> {

    @Autowired
    public MovementService(MovementRepository movementRepository) {
        super(movementRepository);
    }

    //TODO: criar regras de negocio, como nas transferencias validar saldo, nao deixar incluir movimentações com saldo negativo, etc

}
