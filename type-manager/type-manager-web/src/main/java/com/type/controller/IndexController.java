package com.type.controller;

import com.type.common.IDUtils;
import com.type.common.JsonUtils;
import com.type.pojo.Record;
import com.type.pojo.TypeUser;
import com.type.pojo.enumeration.TypeUserState;
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
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

   private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    /**
     * 跳转游戏首页
     * @param model
     * @return
     */

    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("city","china");
        return "forward:/WEB-INF/user/login.jsp";
    }

    /**
     * 跳转用户注册页面
     * @return
     */
    @RequestMapping("/goToRegister")
    public String goToRegister(){
        return "forward:/WEB-INF/user/register.jsp";
    }
    /**
     * 用户注册
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model, TypeUser typeUser){
        logger.debug(JsonUtils.getJsonString(typeUser));
        typeUser.setCreateTime(new Date(System.currentTimeMillis()));
        String record = JsonUtils.getJsonString(new Record());
        typeUser.setRecord(record);
        typeUser.setState(TypeUserState.UNACTIVE.getValue());
        typeUser.setUserId(IDUtils.gengerateId());
        logger.debug(JsonUtils.getJsonString(typeUser));
        boolean flag = userService.insert(typeUser);
        System.out.println(flag);
        return "forward:/WEB-INF/user/login.jsp";
    }


    /**
     * 登录
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model,HttpServletRequest request){
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        HttpSession session = request.getSession();
        session.setAttribute("userName",userName);

        logger.info("login取得的用户名和密码为："+userName+","+password);
        TypeUser user = userService.selectByName(userName);
        boolean result = false;
        if(user != null && user.getPassword().equals(password)){
            result = true;
        }
        if(result){
            return "forward:/WEB-INF/user/page/homepage.jsp";
        }
        return "forward:/500.jsp";
    }

    /**
     * 验证用户名和密码是否正确
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/loginVolidate")
    @ResponseBody
    public String loginVolidate(Model model, HttpServletRequest request){
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        Map<String, Boolean> map = new HashMap<>();
        TypeUser user = userService.selectByName(userName);
        boolean result = false;
        if(user != null && user.getPassword().equals(password)){
            result = true;
        }
        map.put("valid", result);
        return JsonUtils.getJsonString(map);
    }

    /**
     * 判断用户名是否存在，存在返回true,用于注册验证
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/userNameExistTrue")
    @ResponseBody
    public String userNameExistTrue(Model model, HttpServletRequest request){
        String userName = request.getParameter("userName");
        Map<String, Boolean> map = new HashMap<>();
        TypeUser user = userService.selectByName(userName);
        boolean result = false;
        if(user != null && user.getUserName().equals(userName)){
            result = true;
        }
        map.put("valid", result);
        return JsonUtils.getJsonString(map);
    }
    /**
     * 判断用户名是否存在，不存在返回true，用于登录验证
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/userNameExistFalse")
    @ResponseBody
    public String userNameExistFalse(Model model, HttpServletRequest request){
        String userName = request.getParameter("userName");
        Map<String, Boolean> map = new HashMap<>();
        TypeUser user = userService.selectByName(userName);
        boolean result = true;
        if(user != null && user.getUserName().equals(userName)){
            result = false;
        }
        map.put("valid", result);
        return JsonUtils.getJsonString(map);
    }

}
