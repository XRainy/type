package com.type.service.user.impl;

import com.type.mapper.user.UserDao;
import com.type.pojo.TypeUser;
import com.type.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : dx
 * @Date : 2017/4/3
 * Description :
 */
@Service
public class UserServiceImpl implements UserService{
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
            System.out.print(e.getMessage());
            return false;
        }
    }

    @Override
    public TypeUser selectByName(String name) {
        return userDao.selectByName(name);
    }
}
