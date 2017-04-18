package com.type.controller;

import com.type.common.JsonUtils;
import com.type.pojo.TypeUser;
import com.type.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author : dx
 * @Date : 2017/4/18
 * Description :
 */
@Controller
@RequestMapping("/user/page")
public class HomePageController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    UserService userService;

    /**
     * 获取个人信息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/getSelfInformation")
    @ResponseBody
    public String getSelfInformation(Model model, HttpServletRequest request){
        String userName = (String) request.getSession().getAttribute("userName");
        TypeUser typeUser = null;
        try {
            typeUser = userService.selectByName(userName);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return JsonUtils.getJsonString(typeUser);
    }
}
