package test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 清洗，过滤手机号
 * <p>
 * Created by saicao on 2017/4/12.
 */
public class CleanTel {
    private static String str = null;
    // 提取出的纯数据字符串
    private static String str2 = "";
    private static Set<String> set = new HashSet<String>();
    private static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // 输入路径
        File file = new File("D:\\线上手机号\\全部手机号.tsv");
        // 输出路径
        File file1 = new File("D:\\线上手机号\\全部手机号-clean.tsv");


        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(file1));
            String s = null;


            /**
             *  遍历txt文件(此txt为长度大于11位的),取出字符串中的全部的数字,组成新字符串
             *  对新字符串中1开头的,大于11位的,截取新字符串的11位
             */

            while ((s = br.readLine()) != null) {
                // 替换空格,第一位有的是空格...
                str = s.trim().replaceAll(" ", "");


                if (str != null && !"".equals(str) && str.length()>=11) {
                    for (int i = 0; i < str.length(); i++) {
                        if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                            str2 = str2 + str.charAt(i);
                        }
                    }
                    if (str2.length() >= 11 && str2.charAt(0) == 49) {
                        //System.out.println(str2);
                        str2 = str2.trim().substring(0, 11);
                        //System.out.println(str2);
                        set.add(str2);
                    }
                    str2 = "";
                }
            }

            // 遍历Set集合, 将结果写出
            for (String ss : set) {
                System.out.println(ss);
                bufferWritter.write(ss);
                bufferWritter.newLine();
            }

            /**
             * 关闭，否则不刷新
             */
            bufferWritter.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
