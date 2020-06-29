package com.song.sweet.service.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel
public class JournalDTO implements Serializable {

    private static final long serialVersionUID = 7668139997842303573L;

    private Long id;

    private LocalDateTime createdDate = LocalDateTime.now();

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
        return "JournalDTO{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", contentDate='" + contentDate + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
