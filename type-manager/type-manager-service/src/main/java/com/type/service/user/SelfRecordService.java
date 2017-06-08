package com.type.service.user;

import com.github.pagehelper.PageInfo;
import com.type.pojo.SelfRecord;

/**
 * @Author : dx
 * @Date : 2017/6/8
 * Description :
 */
public interface SelfRecordService {
    boolean insert(SelfRecord selfRecord);
    PageInfo<SelfRecord> selectPageByUserId(String userId, String page, String size);
}
