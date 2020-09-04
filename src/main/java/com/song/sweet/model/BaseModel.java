package com.song.sweet.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Liwen
 * @Description // 实体类公共基类
 * @Version 1.0.0
 * @create 2020-09-02 14:00
 */
@Data
@MappedSuperclass
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -7725509382637500436L;

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "主键ID", dataType = "Integer", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(name = "create_by")
    @ApiModelProperty(value = "创建人", dataType = "String", example = "SYSTEM")
    private String createBy = "SYSTEM";

    @CreatedDate
    @Column(name = "create_date")
    @ApiModelProperty(value = "创建日期", dataType = "datetime", example = "1970-01-01 00:00:00")
    private LocalDateTime createDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "update_by")
    @ApiModelProperty(value = "修改人", dataType = "String", example = "updateBy")
    private String updateBy;

    @LastModifiedDate
    @Column(name = "update_date")
    @ApiModelProperty(value = "修改日期", dataType = "datetime", example = "1970-01-01 00:00:00")
    private LocalDateTime updateDate;

    @Column(name = "del_flag")
    @ApiModelProperty(value = "删除标识", dataType = "Integer", example = "0", notes = "0.未删除,1:已删除")
    private Boolean delFlag = Boolean.FALSE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}
