package br.edu.utfpr.myfinances.registrations.user;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudService;
import br.edu.utfpr.myfinances.registrations.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericCrudService<User, Long> {

    private AccountRepository accountRepository;

    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        super(userRepository);
        passwordEncoder = new BCryptPasswordEncoder();
        this.accountRepository = accountRepository;
    }

    @Override
    public User save(User requestBody) throws Exception {
        requestBody.setPassword(passwordEncoder.encode(requestBody.getPassword()));
        return super.save(requestBody);
    }

    @Override
    public User update(User requestBody) throws Exception {
        requestBody.setPassword(passwordEncoder.encode(requestBody.getPassword()));
        return super.update(requestBody);
    }

    @Override
    public Boolean linkedRegister(Long id) {
        return (accountRepository.findByUser_Id(id).isPresent());
    }

    @Override
    public String getMessageLinkedRegisterException() {
        return "Não é possível deletar pois o usuário está em uso.";
    }

}
