package br.edu.utfpr.myfinances.registrations.category;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import br.edu.utfpr.myfinances.registrations.movement.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends GenericCrudService<Category, Long> {

    private MovementRepository movementRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, MovementRepository movementRepository) {
        super(categoryRepository);
        this.movementRepository = movementRepository;
    }

    @Override
    public Boolean linkedRegister(Long id) {
        return (movementRepository.findByCategory_Id(id).isPresent());
    }

    @Override
    public String getMessageLinkedRegisterException() {
        return "Não é possível deletar pois a categoria está em uso.";
    }

}
