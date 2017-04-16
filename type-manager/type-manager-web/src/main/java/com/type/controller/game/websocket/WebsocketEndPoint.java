package com.type.controller.game.websocket;

import com.type.common.JsonUtils;
import com.type.controller.game.websocket.domain.UserMatch;
import com.type.controller.game.websocket.domain.UserWebSocket;
import com.type.controller.game.websocket.domain.WebSocketData;
import com.type.controller.game.websocket.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : dx
 * @Date : 2017/4/9
 * Description :
 */

public class WebsocketEndPoint extends TextWebSocketHandler{
    private static final Logger logger = LoggerFactory.getLogger(WebsocketEndPoint.class);
    //存储等待匹配的用户
    private static BlockingQueue<UserWebSocket> userQueue = new ArrayBlockingQueue<UserWebSocket>(100);
    //存储已经匹配的用户
    private static Map<String,UserMatch> matchMap = new ConcurrentHashMap<String,UserMatch>();
    @Resource
    private WebSocketService webSocketService;

    /**
     * 建立连接成功之后把用户会话存入userQueue
     * @param session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("connect to the websocket success......");
        Map map=session.getAttributes();
        UserWebSocket userWebSocket = new UserWebSocket();
        userWebSocket.setUserName((String)map.get("userName"));
        userWebSocket.setState(1);
        userWebSocket.setSession(session);
        try {
            userQueue.put(userWebSocket);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }

    }
    //结束后存入数据库
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("websocket connection closed......");
    }

    /**
     * 接收客户端信息
     * @param session
     * @param message
     */
    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message){
        logger.info("接收的客户端信息为："+message.getPayload());
        WebSocketData webSocketData = (WebSocketData) JsonUtils.getObjectFromJson(message.getPayload(), WebSocketData.class);
        UserMatch userMatch = null;
        if(webSocketData.getState() == 5){
            userMatch = matchMap.get(webSocketData.getUserMatchId());
            if(userMatch.getLetterSet().contains(webSocketData.getLetter())){
                userMatch.getLetterSet().remove(webSocketData.getLetter());
            }else{
                logger.info("字母在不存在！");
                return;
            }
        }
        logger.info("匹配信息："+userMatch);
        webSocketData.setState(3);
        TextMessage returnMessage = null;
        UserWebSocket userWebSocket1 = userMatch.getUserWebSocket1();
        UserWebSocket userWebSocket2 = userMatch.getUserWebSocket2();
        if(userWebSocket1.getSession().equals(session)){
            userWebSocket1.setScore(userWebSocket1.getScore()+1);
            webSocketData.setScore(userWebSocket1.getScore());
            returnMessage = new TextMessage(JsonUtils.getJsonString(webSocketData));
            try {
                userWebSocket1.getSession().sendMessage(returnMessage);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            webSocketData.setScore(userWebSocket2.getScore());
            returnMessage = new TextMessage(JsonUtils.getJsonString(webSocketData));
            try {
                userWebSocket2.getSession().sendMessage(returnMessage);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }else {
            userWebSocket2.setScore(userWebSocket2.getScore()+1);
            webSocketData.setScore(userWebSocket2.getScore());
            returnMessage = new TextMessage(JsonUtils.getJsonString(webSocketData));
            try {
                userWebSocket2.getSession().sendMessage(returnMessage);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            webSocketData.setScore(userWebSocket1.getScore());
            returnMessage = new TextMessage(JsonUtils.getJsonString(webSocketData));
            try {
                userWebSocket1.getSession().sendMessage(returnMessage);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static BlockingQueue<UserWebSocket> getUserQueue() {
        return userQueue;
    }

    public static void setUserQueue(BlockingQueue<UserWebSocket> userQueue) {
        WebsocketEndPoint.userQueue = userQueue;
    }

    public static Map<String, UserMatch> getMatchMap() {
        return matchMap;
    }

    public static void setMatchMap(Map<String, UserMatch> matchMap) {
        WebsocketEndPoint.matchMap = matchMap;
    }
}
