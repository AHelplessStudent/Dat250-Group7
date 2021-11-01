package no.group7.restservice.DTO;

import no.group7.restservice.entity.Account;
import no.group7.restservice.entity.Poll;
import no.group7.restservice.entity.Vote;
import no.group7.restservice.repository.AccountRepository;
import no.group7.restservice.repository.PollRepository;
import no.group7.restservice.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MaptoDTO {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private AccountRepository accountRepository;

    // probably not needed as we don't want to query individual votes.
    @Autowired
    private VoteRepository voteRepository;

    // getAccounts
    public Collection<AccountDTO> getAccounts() {
        return (Collection<AccountDTO>) accountRepository.findAll()
                .stream()
                .map(this::convertToAccountDTO);
    }

    // getPolls
    public Collection<PollDTO> getPolls() {
        return (Collection<PollDTO>) pollRepository.findAll()
                .stream()
                .map(this::convertToPollDTO);
    }

    // getAccountById
    public AccountDTO getAccountById(Long id) {
        return convertToAccountDTO(accountRepository.getById(id));
    }

    // getPollByID
    public PollDTO getPollById(Long id) {
        return convertToPollDTO(pollRepository.getById(id));
    }

    // getPollsByAccountId
    public Collection<PollDTO> getPollsByAccountId(Long id) {
        return pollRepository.findAll().stream()
                .filter(poll -> poll.getAccount().getId().equals(id))
                .map(this::convertToPollDTO)
                .collect(Collectors.toList());
    }


    private AccountDTO convertToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setFirstname(account.getFirstName());
        accountDTO.setLastname(account.getLastName());
        accountDTO.setPollIds((Collection<Long>) account.getPolls().stream().mapToLong(Poll::getPollId));
        return accountDTO;
    }


    private PollDTO convertToPollDTO(Poll poll) {
        PollDTO pollDTO = new PollDTO();
        pollDTO.setId(poll.getPollId());
        pollDTO.setTitle(poll.getTitle());
        pollDTO.setPublic(poll.isPublic());
        pollDTO.setAccountName(poll.getAccount().getFirstName() + " " + poll.getAccount().getLastName());
        pollDTO.setNum_yes(poll.getVotes().stream().mapToInt(Vote::getNum_yes).sum());
        pollDTO.setNum_no(poll.getVotes().stream().mapToInt(Vote::getNum_no).sum());

        // TODO add startTime and endTime
        return pollDTO;
    }

}
