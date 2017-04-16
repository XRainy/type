package com.type.controller.game.websocket.domain;

import java.util.Queue;
import java.util.Set;

/**
 * @Author : dx
 * @Date : 2017/4/10
 * Description :
 */
public class UserMatch {
    private String userMatchId;
    private Queue letterQueue;
    private Set letterSet;
    private int countLetter;
    private UserWebSocket userWebSocket1;
    private UserWebSocket userWebSocket2;

    @Override
    public String toString() {
        return "UserMatch{" +
                "userMatchId='" + userMatchId + '\'' +
                ", letterQueue=" + letterQueue +
                ", countLetter=" + countLetter +
                ", userWebSocket1=" + userWebSocket1 +
                ", userWebSocket2=" + userWebSocket2 +
                '}';
    }

    public String getUserMatchId() {
        return userMatchId;
    }

    public void setUserMatchId(String userMatchId) {
        this.userMatchId = userMatchId;
    }

    public Queue getLetterQueue() {
        return letterQueue;
    }

    public void setLetterQueue(Queue letterQueue) {
        this.letterQueue = letterQueue;
    }

    public int getCountLetter() {
        return countLetter;
    }

    public void setCountLetter(int countLetter) {
        this.countLetter = countLetter;
    }

    public UserWebSocket getUserWebSocket1() {
        return userWebSocket1;
    }

    public void setUserWebSocket1(UserWebSocket userWebSocket1) {
        this.userWebSocket1 = userWebSocket1;
    }

    public UserWebSocket getUserWebSocket2() {
        return userWebSocket2;
    }

    public void setUserWebSocket2(UserWebSocket userWebSocket2) {
        this.userWebSocket2 = userWebSocket2;
    }

    public Set getLetterSet() {
        return letterSet;
    }

    public void setLetterSet(Set letterSet) {
        this.letterSet = letterSet;
    }
}
