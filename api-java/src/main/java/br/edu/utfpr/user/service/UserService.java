package br.edu.utfpr.user.service;

import br.edu.utfpr.generic.crud.service.GenericService;
import br.edu.utfpr.user.model.UserModel;
import br.edu.utfpr.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<UserModel> {

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }
}
