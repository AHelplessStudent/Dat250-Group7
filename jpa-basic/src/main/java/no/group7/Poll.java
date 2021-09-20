package no.group7;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "poll")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollId;

    private String title;
    private LocalDateTime deadline;
    private boolean isPublic;

    @ManyToOne
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)  // remove all votes if poll deleted
    private List<Vote> votes;

    private String printVotes() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (Vote voteEntity : votes) {
            result.append("\n          ");
            result.append(voteEntity).append(", ");
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public String toString() {
        return "Poll [title=" + title + ", deadline=" + deadline + ", votes=" + printVotes() + "]";
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

    public Account getUserAcc() {
        return account;
    }

    public void setUserAcc(Account account) {
        this.account = account;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> voteEntities) {
        this.votes = voteEntities;
    }
}
