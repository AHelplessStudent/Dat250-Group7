package no.group7.restservice.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoteCompositeKey implements Serializable {

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "poll_id")
    private Long pollId;

    public VoteCompositeKey() {
    }

    public VoteCompositeKey(Long accountId, Long pollId) {
        this.accountId = accountId;
        this.pollId = pollId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteCompositeKey that = (VoteCompositeKey) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(pollId, that.pollId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, pollId);
    }
}
