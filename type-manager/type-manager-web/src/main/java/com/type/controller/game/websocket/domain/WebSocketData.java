package com.type.controller.game.websocket.domain;

/**
 * @Author : dx
 * @Date : 2017/4/10
 * Description :
 * 用于转换json传输数据,服务器
 */
public class WebSocketData {
    private int state;//1表示服务器队列已经满，2表示服务端发送字母信息，3表示对方消除了相关字母,4表示游戏结束,5表示客户端发送过来要消灭的字母
    private String letter;//字母信息
    private int score;
    private int opponentScore;
    private String userMatchId;

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getUserMatchId() {
        return userMatchId;
    }

    public void setUserMatchId(String userMatchId) {
        this.userMatchId = userMatchId;
    }

    @Override
    public String toString() {
        return "WebSocketData{" +
                "state=" + state +
                ", letter='" + letter + '\'' +
                ", score=" + score +
                ", opponentScore=" + opponentScore +
                ", userMatchId='" + userMatchId + '\'' +
                '}';
    }
}
