package com.song.sweetgirl.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Journal implements Serializable {

    private static final long serialVersionUID = -475125670729446291L;

    private Long id;

    private LocalDateTime createdDate;

    private String contentDate;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getContentDate() {
        return contentDate;
    }

    public void setContentDate(String contentDate) {
        this.contentDate = contentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", contentDate='" + contentDate + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
