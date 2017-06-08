package com.type.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.type.mapper.user.SelfRecordDao;
import com.type.pojo.SelfRecord;
import com.type.service.user.SelfRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : dx
 * @Date : 2017/6/8
 * Description :
 */
@Service
public class SelfRecordServiceImpl implements SelfRecordService{
    private static final Logger logger = LoggerFactory.getLogger(OnlineRecordServiceImpl.class);
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    SelfRecordDao selfRecordDao;

    @Override
    public boolean insert(SelfRecord selfRecord) {
        try {
            selfRecordDao.insert(selfRecord);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PageInfo<SelfRecord> selectPageByUserId(String userId, String page, String size) {
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(size));
        List<SelfRecord> list = selfRecordDao.selectByUserId(userId);
        PageInfo<SelfRecord> pageInfo = new PageInfo<SelfRecord>(list);
        return pageInfo;
    }
}
