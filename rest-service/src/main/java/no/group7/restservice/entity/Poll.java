package no.group7.restservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO remove json managed reference (handeled by DTO now)
    @OneToMany(cascade = CascadeType.ALL)  // remove all votes if poll deleted
    @JsonManagedReference
    private List<Vote> votes;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    private boolean isPublic;

    // Not sure if fetch type is correct, should probably add orphanRemoval = true
    // and cascadetype.
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    public Poll(String title, LocalDateTime deadline, LocalDateTime startTime, boolean isPublic) {
        super();
        this.title = title;
        this.deadline = deadline;
        this.startTime = startTime;
        this.isPublic = isPublic;
        this.votes = new ArrayList<>();
    }

    public Poll() {
    }

    public void setPollId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Poll [title=" + title + ", deadline=" + deadline + "]";
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Long getPollId() {
        return id;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void addVote(Vote v) {
        votes.add(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return isPublic == poll.isPublic && id.equals(poll.id) && Objects.equals(title, poll.title) && Objects.equals(deadline, poll.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, deadline, isPublic);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
