package com.chinagreentown.dmp.mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.usrCNetBhvrPojo.Bhvr;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;

/**
 * Created by yun on 2017/4/17.
 */
public class UsrCNetBhvrMapper implements RowMapper<Bhvr> {


    @Override
    public Bhvr mapRow(Result result, int rowNum) throws Exception {
        Bhvr o = (Bhvr) BeanUtil.mapRow(result, Bhvr.class);
        return o;
    }
}
