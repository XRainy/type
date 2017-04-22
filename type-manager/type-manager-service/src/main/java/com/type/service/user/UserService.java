package com.type.service.user;

import com.type.pojo.TypeUser;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : dx
 * @Date : 2017/4/3
 * Description :
 */
public interface UserService {
    /**
     * 插入用户
     * @param typeUser
     * @return
     */
    public boolean insert(TypeUser typeUser);

    /**
     * 查询用户
     * @param name
     * @return
     */
    public TypeUser selectByName(String name);

    /**
     * 修改信息
     * @param user
     * @return
     */
    public boolean updateById(TypeUser user);

    /**
     * 文件上传
     * @param uploadFile
     * @return
     */
    public String uploadFile(MultipartFile uploadFile,String basePath);

    /**
     * 修改图片
     */
    public boolean updateImg(TypeUser typeUser);

}
