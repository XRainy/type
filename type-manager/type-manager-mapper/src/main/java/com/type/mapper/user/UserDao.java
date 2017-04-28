package com.type.mapper.user;

import com.type.pojo.TypeUser;

import java.util.List;

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

    /**
     *
     * @param user
     */
    public void updateById(TypeUser user);

    /**
     *
     */
    public void updateImg(TypeUser typeUser);

    /**
     *
     * @return
     */
    public List<TypeUser> selectAll();
}
