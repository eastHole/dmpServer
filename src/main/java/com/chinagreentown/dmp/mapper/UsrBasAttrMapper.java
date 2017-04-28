package com.chinagreentown.dmp.mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.usrBasAttrPojo.Attr;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yun on 2017/4/18.
 */
public class UsrBasAttrMapper implements RowMapper<Attr> {


    @Override
    public Attr mapRow(Result result, int rowNum) throws Exception {
        Attr o = (Attr) BeanUtil.mapRow(result, Attr.class);
        o.setRowName(Bytes.toString(result.getRow()));
        return o;
    }
}
