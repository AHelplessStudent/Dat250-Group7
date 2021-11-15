package no.group7.restservice.controller;

import no.group7.restservice.entity.Account;
import no.group7.restservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    //////////////////////////////////////
    //// GET-REQUESTS                 ////
    //////////////////////////////////////
    @GetMapping()
    public ResponseEntity<List<Account>> allAccounts() {
        return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Account> oneAccount(@PathVariable(value = "id") Long id) {
        // TODO check if exists
        return new ResponseEntity<>(accountRepository.findById(id).get(), HttpStatus.OK);
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
    public ResponseEntity<Account> postAccount(@RequestBody Account acc) {
        return new ResponseEntity<>(accountRepository.save(acc), HttpStatus.OK);
    }


    //////////////////////////////////////
    //// PUT-REQUESTS                 ////
    //////////////////////////////////////
    /*
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