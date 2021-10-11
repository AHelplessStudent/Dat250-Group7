package no.group7.restservice.controller;

import no.group7.restservice.entity.Account;
import no.group7.restservice.exception.AccountNotFound;
import no.group7.restservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    //////////////////////////////////////
    //// GET-REQUESTS                 ////
    //////////////////////////////////////
    @GetMapping()
    public List<Account> allAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("{id}")
    public Account oneAccount(@PathVariable(value = "id") Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFound(id));
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
                    return accountRepository.save(account);
                })
                .orElseGet(() -> {
                    newAccount.setAccountId(id);
                    return accountRepository.save(newAccount);
                });
    }
}