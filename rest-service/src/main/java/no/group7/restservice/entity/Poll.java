package no.group7.restservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @JsonIgnore
    private Account account;

    @OneToMany(mappedBy = "poll", orphanRemoval = true)
    @JsonIgnore
    private List<Vote> votes;

    public Poll() {
    }

    public Poll(String title, String question, LocalDateTime endTime, LocalDateTime startTime, boolean isPublic, int num_yes, int num_no) {
        this.id = id;
        this.title = title;
        this.question = question;
        this.endTime = endTime;
        this.startTime = startTime;
        this.isPublic = isPublic;
        this.num_yes = num_yes;
        this.num_no = num_no;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getNum_yes() {
        return num_yes;
    }

    public void setNum_yes(int num_yes) {
        this.num_yes = num_yes;
    }

    public int getNum_no() {
        return num_no;
    }

    public void setNum_no(int num_no) {
        this.num_no = num_no;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return isPublic == poll.isPublic && num_yes == poll.num_yes && num_no == poll.num_no && Objects.equals(id, poll.id) && Objects.equals(title, poll.title) && Objects.equals(question, poll.question) && Objects.equals(endTime, poll.endTime) && Objects.equals(startTime, poll.startTime) && Objects.equals(account, poll.account) && Objects.equals(votes, poll.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, question, endTime, startTime, isPublic, num_yes, num_no, account, votes);
    }
}
