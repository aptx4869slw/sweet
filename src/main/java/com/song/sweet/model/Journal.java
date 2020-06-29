package com.song.sweet.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "journal")
public class Journal implements Serializable {

    private static final long serialVersionUID = -475125670729446291L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "content_date")
    private String contentDate;

    @Column(name = "content")
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
