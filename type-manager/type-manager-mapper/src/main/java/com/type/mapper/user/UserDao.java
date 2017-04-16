package com.type.mapper.user;

import com.type.pojo.TypeUser;

/**
 * @Author : dx
 * @Date : 2017/4/2
 * Description :
 */
public interface UserDao {
    /**
     *
     * @param user
     * @return
     */
    public void insert(TypeUser user);

    /**
     *
     * @param id
     * @return
     */
    public TypeUser selectById(String id);

    /**
     *
     * @param name
     * @return
     */
    public TypeUser selectByName(String name);
}
