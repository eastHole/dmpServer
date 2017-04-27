package test;


import com.chinagreentown.dmp.util.HttpUtil;
import com.chinagreentown.dmp.util.PhoneUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 解析手机号,将手机号的解析成, 如
 * 13858105202  浙江移动
 * <p>
 * Created by saicao on 2017/3/27.
 * 添加了两个JAR包
 * <p>
 * 解决之前的  301 Moved Permanently  问题
 */
public class ParseTel {
    public static void main(String args[]) throws IOException {
        System.out.println(getCarrierFromTel("1351571866"));
        // 输入路径
        File file = new File("D:\\线上手机号\\杨柳郡-clean.tsv");
        // 输出路径
        File file1 = new File("D:\\线上手机号\\杨柳郡-全部.tsv");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(file1));
            String s = null;
            // 按行读取输入的文件
            System.out.println("处理中......");
            while ((s = br.readLine()) != null) {


                    // 获取区域 如"浙江联通"
                        String carrier = ParseTel.getCarrierFromTel(s);
//                    String s2 = HttpUtil.phoneEncrypt(s);
                        String s1 = HttpUtil.getPhoneArea(s);
                    String newString = s +"\t\t\t\t"+carrier+"\t\t\t\t"+s1;
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


    /**
     * 解析手机号
     *
     * @param tel 手机号
     * @return 手机号归属地
     * @throws IOException
     */
    public static String getCarrierFromTel(String tel) throws IOException {
        // 调用淘宝API接口
        String urlString = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + tel;

        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        String redirect = connection.getHeaderField("Location");
        if (redirect != null) {
            connection = new URL(redirect).openConnection();
        }

        // 解决乱码问题
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gb2312"));
        String inputLine;
        StringBuffer sb = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();

        String jsonString = null;
        jsonString = sb.toString();

        jsonString = jsonString.replaceAll("^[__]\\w{14}+[_ = ]+", "[");
        String jsonString2 = jsonString + "]";

        JSONArray jsonArray = null;
        JSONObject jsonObject = null;

        // 将String转换为JSON对象
        jsonArray = JSONArray.fromObject(jsonString2);

        // 获取JSONArray的JSONObject对象，便于读取jsonArray里的键值对
        jsonObject = jsonArray.getJSONObject(0);

        // 健壮性,可能不是合法手机号, 如果存在的话则返回,如果不存在的话返回null
        if (jsonObject.containsKey("carrier")) {
            return jsonObject.getString("carrier");
        } else {
            return null;
        }
    }
}
