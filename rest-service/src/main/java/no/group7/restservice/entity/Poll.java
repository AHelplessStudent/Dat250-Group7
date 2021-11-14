package no.group7.restservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String question;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    private boolean isPublic;
    private int num_yes;
    private int num_no;

    // Not sure if fetch type is correct
    // and cascadetype.
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;


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

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(endTime);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPollId() {
        return id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
