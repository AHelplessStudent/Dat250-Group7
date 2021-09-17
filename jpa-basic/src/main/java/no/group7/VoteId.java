package no.group7;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VoteId implements Serializable {

    @Column(name = "voterId")
    private Long voterId;

    @Column(name = "pollId")
    private Long pollId;
}
