package top.yeyusmile.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/14 15:14
 */
@Slf4j
public class JsonUtil {

    private static volatile Gson gson = null;

    public static JsonObject getRoot(String resp){
        JsonObject root = null;
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(resp);
             root = element.getAsJsonObject();
        }catch (Exception e){
            log.error("json error:{}",e.getMessage());
        }
        return root;
    }

    public static Gson getGson(){
        synchronized (JsonUtil.class){
            if(gson == null){
                return new Gson();
            }
        }
        return gson;
    }
}
