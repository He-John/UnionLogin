package com.ky.vmk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {

    public static String toJsonString(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }


    public static <T> T toObject(Object response, Class<T> claz) {
        return JSON.parseObject(toJsonString(response), claz);
    }
}
