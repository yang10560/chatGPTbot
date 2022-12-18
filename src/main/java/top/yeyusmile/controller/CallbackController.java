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
import java.util.List;

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
     * 从队列取一个问题。取不到返回no
     * @return
     */
    @RequestMapping("/getQuestion")
    @ResponseBody
    public String getQuestion() {

        if (questionQuen.isEmpty()) return "no";
        String ret = "no";
        try {
            ret = questionQuen.get().getMsg();
        } catch (Exception e) {
            log.error(TAG + "{}", e.getMessage());
        }

        log.info(TAG + "Question:{}", ret);

        return ret == null ? "no" : ret;
    }


    /**
     * 将问题的答案给收信者
     * @param anser
     * @return
     */
    @RequestMapping("/todo")
    @ResponseBody
    public String todo(@RequestBody String anser) {

        log.info(TAG + "答案:{}", anser);
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(anser);
        JsonObject root = element.getAsJsonObject();
        String text = root.get("anser").getAsString();
        List<Object> params = new ArrayList<>();
        params.add(new PlainMsg(text));
        log.info("sender：{}", questionQuen.getCurrentMyPair().getSender());
        myTemplate.sendMsg2Group(params, null, questionQuen.getCurrentMyPair().
                getSender().getGroup().getId());
        return "success";
    }

}
