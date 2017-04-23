package com.type.service.user.impl;

import com.type.common.IDUtils;
import com.type.mapper.user.UserDao;
import com.type.pojo.TypeUser;
import com.type.service.user.UserService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author : dx
 * @Date : 2017/4/3
 * Description :
 */
@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public boolean insert(TypeUser typeUser){
        try{
            userDao.insert(typeUser);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public TypeUser selectByName(String name) {
        TypeUser typeUser = null;
        typeUser = userDao.selectByName(name);
        if(null != typeUser && null != typeUser.getBirthday()){
            DateTime dateTime = new DateTime(typeUser.getBirthday().getTime());
            typeUser.setStringBirthday(dateTime.toString("yyyy/MM/dd"));
        }
        return typeUser;

    }
    @Transactional
    @Override
    public boolean updateById(TypeUser user) {
        try{
            userDao.updateById(user);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    public String uploadFile(MultipartFile uploadFile,String basePath){
        String result = null;

        OutputStream outputStream = null;
        try {
            // 上传文件功能实现
            // 判断文件是否为空
            if (uploadFile.isEmpty()){
                logger.info("文件为空");
                return null;
            }
            // 上传文件以日期为单位分开存放，可以提高图片的查询速度
            String relativePath = "img/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
                    + new SimpleDateFormat("MM").format(new Date()) + "/"
                    + new SimpleDateFormat("dd").format(new Date());
            String filePath = basePath+relativePath;
            // 取原始文件名
            String originalFilename = uploadFile.getOriginalFilename();
            // 新文件名
            String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
            result = filePath + "/" + newFileName;
            logger.info("最后路径:"+result);
            File dir = new File(filePath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            outputStream = new FileOutputStream(result);
            byte[] b = uploadFile.getBytes();
            outputStream.write(b);
            logger.info("1");
            outputStream.flush();
            logger.info("2"+relativePath+newFileName);
            return "/"+relativePath+"/"+newFileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("3");
        return null;
    }
    @Transactional
    @Override
    public boolean updateImg(TypeUser typeUser) {
        try{
            userDao.updateImg(typeUser);
            return  true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }


}
