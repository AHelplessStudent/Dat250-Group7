package no.group7.restservice.controller;

import no.group7.restservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    //////////////////////////////////////
    //// GET-REQUESTS                 ////
    //////////////////////////////////////
    /*@GetMapping()
    public Collection<AccountDTO> allAccounts() {
        return maptoDTO.getAccounts();
    }

    @GetMapping("{id}")
    public AccountDTO oneAccount(@PathVariable(value = "id") Long id) {
        return maptoDTO.getAccountById(id);
    }

    //////////////////////////////////////
    //// DELETE-REQUESTS              ////
    //////////////////////////////////////
    @DeleteMapping("{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountRepository.deleteById(id);
    }

    //////////////////////////////////////
    //// POST-REQUESTS                ////
    //////////////////////////////////////
    @PostMapping()
    public Account postAccount(@RequestBody Account vote) {
        return accountRepository.save(vote);
    }

    //////////////////////////////////////
    //// PUT-REQUESTS                 ////
    //////////////////////////////////////
    @PutMapping("{id}")
    public Account replaceAccount(@PathVariable("id") Long id, @RequestBody Account newAccount) {
        return accountRepository.findById(id)
                .map(account -> {
                    account.setFirstName(newAccount.getFirstName());
                    account.setLastName(newAccount.getLastName());
                    account.setPassword(newAccount.getPassword());
                    account.setUsername(newAccount.getUsername());
                    account.setPolls(newAccount.getPolls());
                    return accountRepository.save(account);
                })
                .orElseGet(() -> {
                    newAccount.setAccountId(id);
                    return accountRepository.save(newAccount);
                });
    }*/
}