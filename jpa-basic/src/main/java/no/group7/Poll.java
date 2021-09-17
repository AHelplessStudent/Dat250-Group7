package no.group7;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String title;
    private Date deadline;
    private boolean isPublic;

    @Override
    public String toString() {
        return "Poll [title=" + title + ", deadline=" + deadline + "]";
    }

}
