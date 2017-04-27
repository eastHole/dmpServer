package com.chinagreentown.dmp.Mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.ComInfoPojo.Comm;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yun on 2017/4/18.
 */
public class UsrComInfoMapper implements RowMapper<Comm> {


    @Override
    public Comm mapRow(Result result, int rowNum) throws Exception {
        Comm o = (Comm) BeanUtil.mapRow(result, Comm.class);
        o.setRowName(Bytes.toString(result.getRow()));
        return o;
    }
}
