package com.example.restservice;

import java.time.LocalDateTime;

public class Poll {

    private Long pollId;

    private String title;
    private LocalDateTime deadline;
    private boolean isPublic;

    public Poll(Long pollId, String title, LocalDateTime deadline, boolean isPublic) {
        this.pollId = pollId;
        this.title = title;
        this.deadline = deadline;
        this.isPublic = isPublic;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
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

    public Long getPollId() {
        return pollId;
    }
}
