package com.type.service.user;

import com.type.pojo.TypeUser;

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
}
