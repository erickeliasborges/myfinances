package br.edu.utfpr.myfinances.registrations.user;

import br.edu.utfpr.myfinances.generic.crud.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, Long> {

    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User save(User requestBody) throws Exception {
        requestBody.setPassword(passwordEncoder.encode(requestBody.getPassword()));
        return super.save(requestBody);
    }
}
