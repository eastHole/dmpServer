package com.chinagreentown.dmp;

import com.chinagreentown.dmp.util.HttpUtil;
import com.chinagreentown.dmp.util.PhoneUtil;
import com.google.common.collect.Maps;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by yun on 2017/4/24.
 */
public class test1 {
    public static void main(String args[]) throws IOException {
//        System.out.println(getCarrierFromTel("1351571866"));

        // 输入路径
        File file = new File("D:\\线上手机号\\全部手机号信息-带归属地信息.tsv");
        // 输出路径
        File file1 = new File("D:\\线上手机号\\s.tsv");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(file1));
            String s = null;
            // 按行读取输入的文件
            System.out.println("处理中......");
            int i=1;
            HashMap<String , Integer> objectObjectHashMap = Maps.newHashMap();
            while ((s = br.readLine()) != null) {
                if(s.contains("浙江")) {
                    s=s.substring(s.length()-2);
                    if (objectObjectHashMap.containsKey(s)) {
                        Integer s1 = objectObjectHashMap.get(s);
                        s1++;
                        objectObjectHashMap.put(s, s1);
                        continue;
                    }
                    int a=0;
                    objectObjectHashMap.put(s,a);
                }


                // 获取区域 如"浙江联通"
//                String carrier = ParseTel.getCarrierFromTel(s);
////                    String s2 = HttpUtil.phoneEncrypt(s);
//                String s1 = HttpUtil.getPhoneArea(s);
//                String newString = s +"\t\t\t\t"+carrier+"\t\t\t\t"+s1;
//                bufferWritter.write(newString);
//                bufferWritter.newLine();
            }
            Set<String> strings = objectObjectHashMap.keySet();
            for (String s2:strings) {
                String newString = s2 +"\t\t\t\t"+objectObjectHashMap.get(s2);
                bufferWritter.write(newString);
                bufferWritter.newLine();
            }

            /**
             * 注意关闭，否则不刷新
             */
            bufferWritter.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
