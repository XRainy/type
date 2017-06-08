package com.type.mapper.user;

import com.type.pojo.SelfRecord;

import java.util.List;

/**
 * @Author : dx
 * @Date : 2017/6/8
 * Description :
 */
public interface SelfRecordDao {
    /**
     * 插入记录
     * @param selfRecord
     */
    public void insert(SelfRecord selfRecord);

    /**
     * 查询对战信息
     * @return
     */
    public List<SelfRecord> selectByUserId(String userId);
}
