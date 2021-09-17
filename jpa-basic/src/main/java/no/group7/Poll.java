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
    private UserAcc userAcc;

    @OneToMany(mappedBy = "pollID")
    private List<Vote> votes;

    @Override
    public String toString() {
        return "Poll [title=" + title + ", deadline=" + deadline + "]";
    }

}
