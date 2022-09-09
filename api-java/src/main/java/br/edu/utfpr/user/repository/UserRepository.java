package br.edu.utfpr.user.repository;

import br.edu.utfpr.generic.crud.repository.GenericRepository;
import br.edu.utfpr.user.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<UserModel> {
}
