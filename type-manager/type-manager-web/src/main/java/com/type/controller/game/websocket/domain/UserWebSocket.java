package com.type.controller.game.websocket.domain;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Author : dx
 * @Date : 2017/4/10
 * Description :
 * 描述用户游戏交互
 */
public class UserWebSocket {
    private String userName;//用户名
    private WebSocketSession session;//前后台会话通道
    private int state;//用户受否在匹配中1表示需要匹配，2表示正在游戏；
    private int score;//得分

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserWebSocket{" +
                "userName='" + userName + '\'' +
                ", session=" + session +
                ", state=" + state +
                ", score=" + score +
                '}';
    }
}
