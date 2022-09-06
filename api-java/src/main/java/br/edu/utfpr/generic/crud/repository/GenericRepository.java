package br.edu.utfpr.generic.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface GenericRepository<T> extends JpaRepository<T, Long> {

}
