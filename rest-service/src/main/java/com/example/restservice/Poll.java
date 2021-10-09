package com.example.restservice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Poll {

    @Id
    @GeneratedValue
    private Long pollId;

    @OneToMany(cascade = CascadeType.ALL)  // remove all votes if poll deleted
    private List<Vote> votes;

    private String title;
    private LocalDateTime deadline;
    private boolean isPublic;

    public Poll(String title, LocalDateTime deadline, boolean isPublic) {
        this.title = title;
        this.deadline = deadline;
        this.isPublic = isPublic;
    }

    public Poll() {
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
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
        return pollId;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return isPublic == poll.isPublic && pollId.equals(poll.pollId) && Objects.equals(title, poll.title) && Objects.equals(deadline, poll.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pollId, title, deadline, isPublic);
    }
}
