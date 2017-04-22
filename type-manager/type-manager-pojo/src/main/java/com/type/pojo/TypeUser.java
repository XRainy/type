package com.type.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author : dx
 * @Date : 2017/4/2
 * Description :
 */
public class TypeUser {
    private String userId;
    private String userName;
    private String password;
    private String record;
    private Date createTime;
    private String email;
    private int gender;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;
    private String stringBirthday;
    private int state;
    private String friendId;
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStringBirthday() {
        return stringBirthday;
    }

    public void setStringBirthday(String stringBirthday) {
        this.stringBirthday = stringBirthday;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    @Override
    public String toString() {
        return "TypeUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", record='" + record + '\'' +
                ", createTime=" + createTime +
                ", email='" + email + '\'' +
                ", state=" + state +
                ", friendId='" + friendId + '\'' +
                '}';
    }
}
