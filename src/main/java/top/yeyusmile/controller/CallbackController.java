package top.yeyusmile.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yeyusmile.common.MyTemplate;
import top.yeyusmile.common.QuestionQuen;
import top.yeyusmile.mirai.msg.PlainMsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收发信端
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/3/17 9:49
 */
@RequestMapping({"/msg"})
@Controller
@CrossOrigin
@Slf4j
public class CallbackController {
    private static String TAG = "CallbackController:";


    @Autowired
    private MyTemplate myTemplate;

    @Autowired
    private QuestionQuen questionQuen;

    /**
     * 从队列取一个问题。取不到返回群id和问题
     * @return
     */
    @RequestMapping("/getQuestion")
    @ResponseBody
    public Map<String, String> getQuestion() {

        Map<String, String> map = new HashMap<>();

        if (questionQuen.isEmpty()) {
            map.put("question", "");
            map.put("gid", "");
            return map;
        }
        String ret = "no";
        try {
            ret = questionQuen.get().getMsg();
        } catch (Exception e) {
            log.error(TAG + "{}", e.getMessage());
        }

        log.info(TAG + "Question:{}", ret);

        if (ret == null) {
            map.put("question", "");
            map.put("gid", "");
            return map;
        }

        map.put("question", ret);
        map.put("gid", questionQuen.getCurrentMyPair().getSender().getGroup().getId().toString());
        return map;

    }


    /**
     * 将问题的答案给收信者
     * @param rawJson
     * @return
     */
    @RequestMapping("/todo")
    @ResponseBody
    public String todo(@RequestBody String rawJson) {

        log.info(TAG + "rawJson:{}", rawJson);
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(rawJson);
        JsonObject root = element.getAsJsonObject();
        String text = root.get("anser").getAsString();
        Long gid = root.get("gid").getAsLong();
        List<Object> params = new ArrayList<>();
        params.add(new PlainMsg(text));
        log.info("gid：{}", gid);
        myTemplate.sendMsg2Group(params, null, gid);
        return "success";
    }

}
