package com.type.mapper.user;

import com.type.pojo.OnlineRecord;

import java.util.List;

/**
 * @Author : dx
 * @Date : 2017/4/24
 * Description :
 */

public interface OnlineRecordDao {
    /**
     * 插入记录
     * @param onlineRecord
     */
    public void insert(OnlineRecord onlineRecord);

    /**
     * 查询对战信息
     * @return
     */
    public List<OnlineRecord> selectByUserId(String userId);
}
