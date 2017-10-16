package org.springblade.core.toolbox.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;


public class JsonKit {

    public static String toJson(Object object) {
        return JSONObject.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
    }

    public static String toJsonWithPascalName(Object object) {
        return JSON.toJSONString(object, SerializeConfig.globalInstance, new SerializeFilter[]{new PascalNameFilter()}, "yyyy-MM-dd HH:mm:ss", JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteDateUseDateFormat);
    }

    public static JSONObject parse(String text) {
        return JSONObject.parseObject(text);
    }

    public static <T> T parse(String text, Class<T> clazz) {
        return JSONObject.parseObject(text, clazz);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (!(text.startsWith("[") && text.endsWith("]"))) {
            text = "[" + text + "]";
        }
        return JSONObject.parseArray(text, clazz);
    }
	
}

