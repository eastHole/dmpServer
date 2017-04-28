package com.chinagreentown.dmp.service;

import com.chinagreentown.dmp.pojo.comInfoPojo.Comm;
import com.chinagreentown.dmp.pojo.usrBasAttrPojo.Attr;
import com.chinagreentown.dmp.pojo.usrCNetBhvrPojo.Bhvr;
import com.chinagreentown.dmp.pojo.usrPoiInfoPojo.Poi;
import org.json.JSONException;

import java.util.List;
import java.util.Map;

/**
 * Created by yun on 2017/4/17.
 */
public interface PrecisionMarketingService {

    /**
     * 根据手机号和日期  获取 用户画像
     *
     * @param date
     * @return
     */
    Map<String, Object> getUsrLabelInfo(String date, String phoneNum) throws NoSuchFieldException, JSONException, IllegalAccessException;


    /**
     * 获取生活地坐标
     *
     * @param date
     * @param phoneNum
     * @return
     */
    Map<String, Object> getLivePlace(String date, String phoneNum,String bdaccount);


    /**
     * 获取工作地坐标
     *
     * @param date
     * @param phoneNum
     * @return
     */
    Map<String, Object> getWorkPlace(String date, String phoneNum,String account);




    /**
     * 获取楼盘微观画像
     *
     * @param date
     * @param esateCode
     * @return
     */
    Map<String, Object>  getEsateMicro(String date, String esateCode) throws IllegalAccessException, NoSuchFieldException, JSONException;

    Object  getEsateInfo(String date,String EsateCode);

    /**
     * 根据通信对象获取 通信信息
     *
     * @param comenitys
     * @return key 为用户加密手机号  hbase 中取出的 通信信息对象
     * @throws NoSuchFieldException
     * @throws JSONException
     * @throws IllegalAccessException
     */
    Map<String, Object> getComMapDTO(List<Comm> comenitys) throws NoSuchFieldException, JSONException, IllegalAccessException;

    /**
     * 根据哦通信对象 获取消费信息
     *
     * @param comenitys hbase 中取出的 通信信息对象
     * @return key 为用户加密手机号
     * @throws JSONException
     */
    Map<String, Object> getConMapDTO(List<Comm> comenitys) throws JSONException;

    /**
     * 获取用户基本信息
     *
     * @param attrEnitys hbase 中取出的 用户基本信息对象
     * @return key 为用户加密手机号
     */
    Map<String, Object> getUserAttrDTO(List<Attr> attrEnitys) throws JSONException;

    /**
     * 生活位置信息 转 dto
     *
     * @param poiEnitys hbase 中取出的 位置信息对象
     * @return key 为用户加密手机号
     */
    Map<String, Object> getUsrPoiInfoLive(List<Poi> poiEnitys);

    /**
     * 工作坐标  转 dto
     *
     * @param poiEnitys hbase 中取出的 位置信息对象
     * @return key 为用户加密手机号
     */
    Map<String, Object> getUsrPoiInfoWork(List<Poi> poiEnitys);


    /**
     * c 网爱好  转前台dto
     *
     * @param cbhvrs
     * @return
     */
    Map<String, Object> getUsrCnetBehvr(List<Bhvr> cbhvrs) throws IllegalAccessException, JSONException;

    /**
     * @param Gbhvrs
     * @return
     * @description 解析固网数据
     */
    Map<String, Object> getUsrFixBhvr(List<com.chinagreentown.dmp.pojo.usrFixNetBhvr.Bhvr> Gbhvrs) throws IllegalAccessException, JSONException;

    /**
     * 通过楼盘编码获取用户工作坐标
     * @param esateCode
     * @return
     */
    Map<String ,Object>  getEsateUsersPOIWork(String date,String esateCode);

    /**
     * 生活热力图
     * @param date
     * @param esateCode
     * @return
     */
    Map<String,Object>  getEsateUserPOILive(String date,String esateCode);

    /**
     * 获取最近一天用户的画像图
     * @param pageSize
     * @param pageIndex
     * @param Date
     * @param lastKey
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws JSONException
     */
    Map<String, Object>   getRecentlyUserLabel(int pageSize,int pageIndex,String Date,String lastKey) throws IllegalAccessException, NoSuchFieldException, JSONException;




}
