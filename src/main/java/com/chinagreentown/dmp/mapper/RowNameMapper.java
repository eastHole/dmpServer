package com.chinagreentown.dmp.mapper;

import com.chinagreentown.dmp.api.RowMapper;
import org.apache.hadoop.hbase.client.Result;

/**
 * Created by yun on 2017/4/24.
 */
public class RowNameMapper implements RowMapper<String> {
    @Override
    public String mapRow(Result result, int rowNum) throws Exception {
        return org.apache.hadoop.hbase.util.Bytes.toString(result.getRow());
    }
}
