package com.chinagreentown.dmp.service.impl;

import com.chinagreentown.dmp.mapper.*;
import com.chinagreentown.dmp.api.HbaseTemplate;
import com.chinagreentown.dmp.pojo.comInfoPojo.Comm;
import com.chinagreentown.dmp.pojo.usrBasAttrPojo.Attr;
import com.chinagreentown.dmp.pojo.usrCNetBhvrPojo.Bhvr;
import com.chinagreentown.dmp.pojo.usrPoiInfoPojo.Poi;
import com.chinagreentown.dmp.pojo.este_info.Assc;
import com.chinagreentown.dmp.pojo.este_info.Bas;
import com.chinagreentown.dmp.service.BaseQueryService;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yun on 2017/4/18.
 */
@Service
public class BaseQueryServiceImpl implements BaseQueryService {


    @Autowired
    private HbaseTemplate hbaseservice;
    //通信信息表明
    private final static String COMINFO = "com_info";
    //usr_poi_info 用户位置信息基础表
    private final static String USRPOI = "usr_poi_info";
    //attr  表
    private final static String USERATTR = "usr_bas_attr";
    //net爱好
    private final static String usr_c_net_bhvr = "usr_c_net_bhvr";
    //楼盘表
    private final static String este_info = "este_info";

    @Override
    public List<Comm> getUsrCom(String family, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<Comm> com_info = hbaseservice.find(COMINFO, scan, new UsrComInfoMapper());
        return com_info;
    }

    @Override
    public List<Poi> getUsrPoiInfo(String family, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<Poi> pois = hbaseservice.find(USRPOI, scan, new UsrPoiInfoMapper());
        return pois;
    }

    @Override
    public List<Poi> getUsrPoiInfo(String family, String column, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
        List<Poi> pois = hbaseservice.find(USRPOI, scan, new UsrPoiInfoMapper());
        return pois;
    }

    @Override
    public List<Attr> getUserAttr(String startrow, String family, FilterList list) {
        Scan scan = new Scan(startrow.getBytes());
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<Attr> attrs = hbaseservice.find(USERATTR, scan, new UsrBasAttrMapper());
        return attrs;
    }

    @Override
    public List<Attr> getUserAttr(String family, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<Attr> attrs = hbaseservice.find(USERATTR, scan, new UsrBasAttrMapper());
        return attrs;
    }


    @Override
    public List<Bhvr> getUsrBhvr(String family, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<Bhvr> bhvrs = hbaseservice.find(usr_c_net_bhvr, scan, new UsrCNetBhvrMapper());
        return bhvrs;
    }

    @Override
    public String getFirstData(String tableName) {
        PageFilter pageFilter = new PageFilter(1);
        Scan scan = new Scan();
        scan.setFilter(pageFilter);
        List<String> strings = hbaseservice.find(tableName, scan, new RowNameMapper());
        if (null != strings) {
            return strings.get(0);
        }
        return "";
    }

    @Override
    public List<Assc> getEsateAssc(String esateCode, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes("assc"));
        List<Assc> asscs = hbaseservice.find(este_info, scan, new EsateInfoAsscMapper());
        return asscs;
    }

    @Override
    public List<Bas> getEsateBas(String esateCode, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes("bas"));
        List<Bas> bass = hbaseservice.find(este_info, scan, new EsteInfoBasMapper());
        return bass;
    }


}
