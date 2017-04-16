package com.type.controller.game;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : dx
 * @Date : 2017/4/9
 * Description :
 */
@Controller
public class GameController {
    @RequestMapping("/type/onlineGame")
    public String onlineGame(Model model, HttpServletRequest request){
        return "forward:/WEB-INF/type/onlineType.jsp";
    }
    @RequestMapping("/type/selfGame")
    public String selfGame(Model model, HttpServletRequest request){
        return "forward:/WEB-INF/type/selfType.jsp";
    }
}
