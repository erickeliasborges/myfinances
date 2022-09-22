package br.edu.utfpr.myfinances.registrations.user;

import br.edu.utfpr.myfinances.generic.crud.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
public class UserController extends GenericController<User, Long> {

    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }

}
