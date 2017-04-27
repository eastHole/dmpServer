package com.chinagreentown.dmp.Mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.este_info.Assc;
import com.chinagreentown.dmp.pojo.este_info.Bas;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yun on 2017/4/25.
 */
public class EsateInfoAsscMapper implements RowMapper<Assc> {
    @Override
    public Assc mapRow(Result result, int rowNum) throws Exception {
        Assc o = (Assc) BeanUtil.mapRow(result, Bas.class);
        o.setRowName(Bytes.toString(result.getRow()));
        return o;
    }
}
