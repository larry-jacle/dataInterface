package com.gildata.byinterserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.TreeMap;

/**
 * Created by LiChao on 2018/11/9
 */
public class SignatureUtil {

    private final static Logger logger = LoggerFactory.getLogger(SignatureUtil.class);

    public final static String secretKey = "ldjfdreew34533kdsdsreqd$557*3fdk23$$*522321223";

    /**
     * MD5(Sign=scretKey+”&”+timestamp=timestamp+”&”+data=utf-8(data)+”&”+datasize=datasize+”&”+datatype=datatype)
     * @param paramMap
     * @return
     * @throws Exception
     */
    public  static String getSignStr(TreeMap<String, String> paramMap) throws Exception
    {
        String signStr = "";
        // 对map的value进行UTF-8编码
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(URLEncoder.encode(secretKey,"UTF-8"));
        String tmpVal = "";
        for (String key : paramMap.keySet())
        {
            strBuffer.append("&");

            tmpVal = paramMap.get(key);
            if (tmpVal.contains("[") || tmpVal.contains("]"))
            {
                key = URLEncoder.encode(key, "UTF-8");
            }

            strBuffer.append(key);
            strBuffer.append("=");
            strBuffer.append(URLEncoder.encode(tmpVal == null ? "" : tmpVal, "UTF-8"));
        }
//        System.out.println("##加密前:##" + strBuffer.toString());
        // md5加密
        signStr = MD5Utils.MD5Encode(strBuffer.toString(),"UTF-8").toUpperCase();
        System.out.println("##加密后:##" + signStr);
        return signStr;
    }

}
