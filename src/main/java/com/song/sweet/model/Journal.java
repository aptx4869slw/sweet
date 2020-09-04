package com.song.sweet.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "journal")
public class Journal implements Serializable {

    private static final long serialVersionUID = -475125670729446291L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", dataType = "Integer", example = "1")
    private Long id;

    @Column(name = "created_date")
    @ApiModelProperty(value = "创建日期", dataType = "datetime", example = "1970-01-01 00:00:00")
    private LocalDateTime createdDate;

    @Column(name = "content_date")
    @ApiModelProperty(value = "创建日期", dataType = "String", example = "1970-01-01 00:00:00")
    private String contentDate;

    @Column(name = "content")
    @ApiModelProperty(value = "内容", dataType = "String", example = "content")
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
