package org.exotic.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Exotic on 2017/10/27.
 */
public final class Response2Json {
    // 响应JSON格式
    public static class ResBody {
        String code;
        String msg;
        Object data;

        ResBody(String code, String msg, Object data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public Object getData() {
            return data;
        }
    }

    // code状态码
    public static String CODE_SUCCESS = "000";
    public static String CODE_NULL_PARAM = "100";
    public static String CODE_FORMAT_PARAM = "101";
    public static String CODE_EXCEPTION = "999";

    public static ResBody toJsonBean(String code,String msg,Object data){
        return new ResBody(code, msg, data);
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name","gzs");
        map.put("nage",23);
        System.out.println(JSON.toJSONString(Response2Json.toJsonBean(Response2Json.CODE_SUCCESS,"1000",map)));
    }
}
