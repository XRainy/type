package com.type.controller.game.websocket.service.startup;

import com.type.controller.game.websocket.service.WebSocketService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @Author : dx
 * @Date : 2017/4/11
 * Description :
 * 开启新线程匹配用户，不开启新线程整个程序会阻塞；
 * 生产者消费者模型
 */
@Service
class MatchStart implements InitializingBean,Runnable{
    /**
     * spring加载完成后执行该方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Thread thread = new Thread(new MatchStart());
        thread.start();
    }

    @Override
    public void run() {
        WebSocketService.match();
    }
}
