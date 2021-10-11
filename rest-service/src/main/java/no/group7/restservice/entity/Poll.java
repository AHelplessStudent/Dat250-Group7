package no.group7.restservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)  // remove all votes if poll deleted
    @JsonManagedReference
    private List<Vote> votes;

    private String title;
    private LocalDateTime deadline;
    private boolean isPublic;

    public Poll(String title, LocalDateTime deadline, boolean isPublic) {
        super();
        this.title = title;
        this.deadline = deadline;
        this.isPublic = isPublic;
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
}
