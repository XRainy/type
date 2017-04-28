package com.type.pojo;

/**
 * @Author : dx
 * @Date : 2017/4/24
 * Description :
 */
public class OnlineRecord {
    private String onlineRecordId;
    private String userId1;
    private String userId2;
    private Integer userScore1;
    private Integer userScore2;
    private String userName1;
    private String userName2;
    public String getUserName1() {
        return userName1;
    }
    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }
    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public String getOnlineRecordId() {
        return onlineRecordId;
    }

    public void setOnlineRecordId(String onlineRecordId) {
        this.onlineRecordId = onlineRecordId;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

    public Integer getUserScore1() {
        return userScore1;
    }

    public void setUserScore1(Integer userScore1) {
        this.userScore1 = userScore1;
    }

    public Integer getUserScore2() {
        return userScore2;
    }

    public void setUserScore2(Integer userScore2) {
        this.userScore2 = userScore2;
    }

    @Override
    public String toString() {
        return "OnlineRecord{" +
                "onlineRecordId='" + onlineRecordId + '\'' +
                ", userId1='" + userId1 + '\'' +
                ", userId2='" + userId2 + '\'' +
                ", userScore1='" + userScore1 + '\'' +
                ", userScore2='" + userScore2 + '\'' +
                '}';
    }
}
