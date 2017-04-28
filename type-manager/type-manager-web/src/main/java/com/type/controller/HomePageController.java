package com.type.controller;

import com.github.pagehelper.PageInfo;
import com.type.common.JsonUtils;
import com.type.pojo.OnlineRecord;
import com.type.pojo.TypeUser;
import com.type.service.user.OnlineRecordService;
import com.type.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);

    @Resource
    UserService userService;
    @Resource
    OnlineRecordService onlineRecordService;

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

    @RequestMapping("/updateById")
    @ResponseBody
    public String updateById(Model model,TypeUser typeUser){
        if(userService.updateById(typeUser)){
            return "1";
        }
        return "2";
    }
    @RequestMapping("/imgUpload")
    @ResponseBody
    public String imgUpload(Model model, MultipartFile file, HttpServletRequest request){
        String basePath = request.getSession().getServletContext().getRealPath("/");
        TypeUser typeUser = new TypeUser();
        logger.info("webapp路径："+basePath);
        try{
            String imgUrl = userService.uploadFile(file,basePath);
            logger.info("相对路径为："+imgUrl);
            typeUser.setUserName((String) request.getSession().getAttribute("userName"));
            typeUser.setImg(imgUrl);
            userService.updateImg(typeUser);
            model.addAttribute("imgUrl",imgUrl);
        }catch (Exception e){
            logger.error("头像上传失败："+e.getMessage());
            model.addAttribute("error","上传失败！");
        }
        logger.info("图片返回值："+JsonUtils.getJsonString(model));
        userService.uploadFile(file,basePath);
        return JsonUtils.getJsonString(model);
    }
    @RequestMapping("/onlineRecord")
    @ResponseBody
    public String onlineRecord(HttpServletRequest request,String size,String page){
        logger.info("分页信息：size："+size+"page"+page);
        String userName = (String) request.getSession().getAttribute("userName");
        TypeUser typeUser = userService.selectByName(userName);
        logger.info("用户信息："+typeUser);
        PageInfo<OnlineRecord> pageInfo = onlineRecordService.selectPageByUserId(typeUser.getUserId(),page,size);
        return JsonUtils.getJsonString(pageInfo);
    }
}
