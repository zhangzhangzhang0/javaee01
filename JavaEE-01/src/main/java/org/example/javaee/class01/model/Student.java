package org.example.javaee.class01.model;

import java.util.Date;

public class Student {
    private Long id;
    private String name;
    private Date createTime;
    private Date updateTime;

    public Long getStudentId() { return id; }

    public void setStudentId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
