package br.edu.utfpr.myfinances.registrations.category;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends GenericCrudRepository<Category, Long> {

    Optional<Category> findByName(String name);

}
