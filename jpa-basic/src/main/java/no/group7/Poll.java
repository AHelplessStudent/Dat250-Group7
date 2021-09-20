package no.group7;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "poll")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollId;

    private String title;
    private Date deadline;
    private boolean isPublic;

    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "Poll")
    private List<Vote> votes;

    private String printVotes() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (Vote vote : votes) {
            result.append("\n          ");
            result.append(vote).append(", ");
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
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

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
