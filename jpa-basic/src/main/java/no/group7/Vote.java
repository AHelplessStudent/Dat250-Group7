package no.group7;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // true=yes, false=no
    private boolean type;

    @Override
    public String toString() {
        return "Vote [type=" + type + "]";
    }

}
