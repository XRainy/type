package com.type.pojo;

import java.util.Date;

/**
 * @Author : dx
 * @Date : 2017/6/8
 * Description :
 */
public class SelfRecord {
    private String selfRecordId;
    private String userId;
    private String userName;
    private Integer score;
    private Date createTime;

    public String getSelfRecordId() {
        return selfRecordId;
    }

    public void setSelfRecordId(String selfRecordId) {
        this.selfRecordId = selfRecordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
