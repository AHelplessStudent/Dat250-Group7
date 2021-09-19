package no.group7;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VoteId implements Serializable {

    @Column(name = "voter_id")
    private Long voterId;

    @Column(name = "poll_id")
    private Long pollId;
}
