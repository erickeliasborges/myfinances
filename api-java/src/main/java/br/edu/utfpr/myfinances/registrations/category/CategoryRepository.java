package br.edu.utfpr.myfinances.registrations.category;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericCrudRepository<Category, Long> {
}
