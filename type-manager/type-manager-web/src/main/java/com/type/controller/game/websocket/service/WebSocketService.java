package com.type.controller.game.websocket.service;

import com.type.common.IDUtils;
import com.type.common.JsonUtils;
import com.type.controller.game.websocket.WebsocketEndPoint;
import com.type.controller.game.websocket.domain.UserMatch;
import com.type.controller.game.websocket.domain.UserWebSocket;
import com.type.controller.game.websocket.domain.WebSocketData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : dx
 * @Date : 2017/4/10
 * Description :处理游戏过程
 */
@Service
public class WebSocketService{
    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    private static Random random = new Random();
    //匹配用户
    public static void match(){
        BlockingQueue queue = WebsocketEndPoint.getUserQueue();
        UserWebSocket userWebSocket1 = null;
        UserWebSocket userWebSocket2 = null;
        while(true){
            try {
                logger.info("匹配中。。。");
                userWebSocket1 = (UserWebSocket)queue.take();
                logger.info("用户1就绪。。");
                userWebSocket2 = (UserWebSocket)queue.take();
                logger.info("用户2就绪。。");
                UserMatch userMatch = new UserMatch();
                userMatch.setUserMatchId(IDUtils.gengerateId());
                userMatch.setCountLetter(52);
                userMatch.setUserWebSocket1(userWebSocket1);
                userMatch.setUserWebSocket2(userWebSocket2);
                Queue<String> letterQueue = new ArrayBlockingQueue<String>(userMatch.getCountLetter());
                letterQueue.add("start");
                for (int i=0;i<userMatch.getCountLetter()-2;i++){
                    char ch = (char) (random.nextInt(26)+65);
                    letterQueue.add(""+ch);
                }
                letterQueue.add("end");
                userMatch.setLetterQueue(letterQueue);
                Set<String> letterSet = ConcurrentHashMap.<String>newKeySet();
                userMatch.setLetterSet(letterSet);
                logger.info("匹配结果---"+userMatch);
                WebsocketEndPoint.getMatchMap().put(userMatch.getUserMatchId(),userMatch);
                new SendLetter(userMatch).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回系统繁忙
     * @param session
     * @return
     */
    public void busy(WebSocketSession session){
        WebSocketData webSocketData = new WebSocketData();
        webSocketData.setState(1);
        TextMessage tm = new TextMessage(JsonUtils.getJsonString(webSocketData));
        try {
            session.sendMessage(tm);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


    //因为有多组用户，所以需要多线程发送字母，每个线程为一组用户服务
    static class SendLetter extends Thread{
        public UserMatch userMatch;
        @Override
        public void run(){
            Queue letterQueue = userMatch.getLetterQueue();
            int j=letterQueue.size();
            for (int i = 0; i < j; i++) {
                try {
                    //1秒发送1个字母
                    Thread.sleep((long) 500.0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WebSocketData data = new WebSocketData();
                data.setUserMatchId(userMatch.getUserMatchId());
                String letter = (String) letterQueue.poll();
                if(!userMatch.getLetterSet().contains(letter)){
                    userMatch.getLetterSet().add(letter);
                    if(null != letter){
                        data.setLetter(letter);
                        data.setState(2);
                        TextMessage message;
                        if(!"end".equals(letter)){
                            logger.info("发送信息："+data);
                            message = new TextMessage(JsonUtils.getJsonString(data));
                            try {
                                userMatch.getUserWebSocket1().getSession().sendMessage(message);
                                userMatch.getUserWebSocket2().getSession().sendMessage(message);
                            } catch (IOException e) {
                                logger.error(e.getMessage());
                            }
                        }else{
                            data.setState(4);
                            data.setScore(userMatch.getUserWebSocket1().getScore());
                            data.setOpponentScore(userMatch.getUserWebSocket2().getScore());
                            message = new TextMessage(JsonUtils.getJsonString(data));
                            try {
                                logger.info("发送信息："+data);
                                userMatch.getUserWebSocket1().getSession().sendMessage(message);
                            } catch (IOException e) {
                                logger.error(e.getMessage());
                            }
                            data.setScore(userMatch.getUserWebSocket2().getScore());
                            data.setOpponentScore(userMatch.getUserWebSocket1().getScore());
                            try {
                                logger.info("发送信息："+data);
                                userMatch.getUserWebSocket2().getSession().sendMessage(message);
                            } catch (IOException e) {
                                logger.error(e.getMessage());
                            }
                        }

                    }
                }
            }
        }

        public SendLetter(UserMatch userMatch) {
            this.userMatch = userMatch;
        }

        public UserMatch getUserMatch() {
            return userMatch;
        }

        public void setUserMatch(UserMatch userMatch) {
            this.userMatch = userMatch;
        }
    }

}
