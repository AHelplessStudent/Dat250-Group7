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
        return new ResponseEntity<>(accountRepository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/authid/{authId}")
    public ResponseEntity<Account> oneAccount(@PathVariable(value = "authId") String authId) {
        System.out.println(accountRepository.findByAuthId(authId));
        return new ResponseEntity<>(accountRepository.findByAuthId(authId), HttpStatus.OK);
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
    public ResponseEntity<Account> postAccount(@RequestBody Account vote) {
        return new ResponseEntity<>(accountRepository.save(vote), HttpStatus.OK);
    }

    
}