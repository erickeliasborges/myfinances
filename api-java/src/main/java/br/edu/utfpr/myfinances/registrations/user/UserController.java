package br.edu.utfpr.myfinances.registrations.user;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
public class UserController extends GenericCrudController<User, User, Long> {

    private UserService userService;

    public UserController(UserService userService) {
        super(userService, User.class, User.class);
        this.userService = userService;
    }

    @RequestMapping(value = "/bytoken", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> findUserByToken(Authentication authentication) {
        return ResponseEntity.ok(userService.findByUsername(authentication.getName()));
    }

}
