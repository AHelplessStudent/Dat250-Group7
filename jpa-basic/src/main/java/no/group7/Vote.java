package no.group7;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
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
