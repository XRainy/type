package com.type.controller.game;


import com.type.common.JsonUtils;
import com.type.controller.IndexController;
import com.type.pojo.TypeUser;
import com.type.common.IDUtils;
import com.type.pojo.SelfRecord;
import com.type.service.user.SelfRecordService;
import com.type.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author : dx
 * @Date : 2017/4/9
 * Description :
 */
@Controller
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private SelfRecordService selfRecordService;
    @Resource
    private UserService userService;
    @RequestMapping("/type/onlineGame")
    public String onlineGame(Model model, HttpServletRequest request){
        return "forward:/WEB-INF/type/onlineType.jsp";
    }
    @RequestMapping("/type/selfGame")
    public String selfGame(Model model, HttpServletRequest request){
        return "forward:/WEB-INF/type/selfType.jsp";
    }

    /**
     * ajax存储单人对战记录
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/type/selfRecord")
    @ResponseBody
    public String selfRecord(Model model,HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        String score = request.getParameter("score");
        String userName = (String) httpSession.getAttribute("userName");
        TypeUser typeUser = userService.selectByName(userName);
        String userId = typeUser.getUserId();
        SelfRecord selfRecord = new SelfRecord();
        selfRecord.setSelfRecordId(IDUtils.gengerateId());
        selfRecord.setCreateTime(new Date());
        selfRecord.setScore(Integer.parseInt(score));
        selfRecord.setUserId(userId);
        selfRecord.setUserName(userName);
        logger.info("SelfRecord:"+ JsonUtils.getJsonString(selfRecord));
        selfRecordService.insert(selfRecord);
        return "success";
    }
}
