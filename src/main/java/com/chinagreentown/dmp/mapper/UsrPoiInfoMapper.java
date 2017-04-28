package com.chinagreentown.dmp.mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.usrPoiInfoPojo.Poi;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yun on 2017/4/18.
 */
public class UsrPoiInfoMapper implements RowMapper<Poi> {


    @Override
    public Poi mapRow(Result result, int rowNum) throws Exception {
        Poi o = (Poi) BeanUtil.mapRow(result, Poi.class);
        o.setRowName(Bytes.toString(result.getRow()));
        return o;
    }


}
