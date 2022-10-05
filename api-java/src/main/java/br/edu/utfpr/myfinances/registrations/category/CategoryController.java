package br.edu.utfpr.myfinances.registrations.category;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController extends GenericCrudController<Category, Category, Long> {

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService, Category.class, Category.class);
    }

}
