package com.sus.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/3/18.
 */
public class StringUtilSelf {

    public static String toUTF8(String param) {
        if(StringUtils.isNotEmpty(param)){

            try {
                return new String(param.getBytes("ISO8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("字符码转换错误！");
            }
        }
        return param;
    }

}
