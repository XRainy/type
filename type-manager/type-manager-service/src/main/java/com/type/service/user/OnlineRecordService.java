package com.type.service.user;

import com.github.pagehelper.PageInfo;
import com.type.pojo.OnlineRecord;

/**
 * @Author : dx
 * @Date : 2017/4/24
 * Description :
 */
public interface OnlineRecordService {
    boolean insert(OnlineRecord onlineRecord);
    PageInfo<OnlineRecord> selectPageByUserId(String userId, String page, String size);
}
