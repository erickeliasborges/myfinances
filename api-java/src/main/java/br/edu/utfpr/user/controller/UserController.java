package br.edu.utfpr.user.controller;

import br.edu.utfpr.generic.crud.controller.GenericController;
import br.edu.utfpr.user.model.UserModel;
import br.edu.utfpr.user.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
public class UserController extends GenericController<UserModel> {

    public UserController(UserRepository userRepository) {
        super(userRepository);
    }

}
