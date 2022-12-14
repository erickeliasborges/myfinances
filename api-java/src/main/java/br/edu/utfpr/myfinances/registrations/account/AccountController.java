package br.edu.utfpr.myfinances.registrations.account;

import br.edu.utfpr.myfinances.generic.crud.GenericCrudController;
import br.edu.utfpr.myfinances.registrations.account.dtos.AccountOverviewDTO;
import br.edu.utfpr.myfinances.registrations.account.responses.DuplicatedAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("account")
public class AccountController extends GenericCrudController<Account, Account, Long> {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        super(accountService, Account.class, Account.class);
        this.accountService = accountService;
    }

    @PostMapping("duplicated")
    public ResponseEntity<DuplicatedAccount> duplicatedAccount(@RequestBody Account account) {
        Optional<Account> optionalAccount = accountService.findByAgencyAndNumberAndBank(account);
        return ResponseEntity.ok(new DuplicatedAccount(optionalAccount.isPresent() && (!Objects.equals(optionalAccount.get().getId(), account.getId()))));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<Account>> findByUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accountService.findByUser_Id(id));
    }

    @GetMapping("overview")
    public ResponseEntity<List<AccountOverviewDTO>> findOverview() {
        return ResponseEntity.ok(accountService.findOverview());
    }

}
