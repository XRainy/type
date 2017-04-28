package com.type.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.type.mapper.user.OnlineRecordDao;
import com.type.pojo.OnlineRecord;
import com.type.service.user.OnlineRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : dx
 * @Date : 2017/4/24
 * Description :
 */
@Service
public class OnlineRecordServiceImpl implements OnlineRecordService{
    private static final Logger logger = LoggerFactory.getLogger(OnlineRecordServiceImpl.class);
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    OnlineRecordDao onlineRecordDao;
    @Override
    public boolean insert(OnlineRecord onlineRecord) {
        try {
            onlineRecordDao.insert(onlineRecord);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PageInfo<OnlineRecord> selectPageByUserId(String userId, String page, String size) {
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(size));
        List<OnlineRecord> list = onlineRecordDao.selectByUserId(userId);
        PageInfo<OnlineRecord> pageInfo = new PageInfo<OnlineRecord>(list);
        return pageInfo;
    }

}
