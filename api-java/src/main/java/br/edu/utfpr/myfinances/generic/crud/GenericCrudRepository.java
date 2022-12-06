package br.edu.utfpr.myfinances.generic.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GenericCrudRepository<T, ID> extends JpaRepository<T, ID> {
}
