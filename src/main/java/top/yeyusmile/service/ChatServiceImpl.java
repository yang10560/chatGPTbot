package top.yeyusmile.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yeyusmile.common.MyTemplate;
import top.yeyusmile.common.RobotConfig;
import top.yeyusmile.mirai.Sender;
import top.yeyusmile.mirai.msg.PlainMsg;
import top.yeyusmile.utils.HttpUtil;
import top.yeyusmile.vo.AiResultVo;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/3/16 15:15
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private MyTemplate myTemplate;

    @Autowired
    private RobotConfig robotConfig;


    //官方api
    @Override
    public void openAI(String msg, Sender sender) {

        try {

            MediaType JSON = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(JSON, String.format("{\n" +
                    "    \"model\": \"text-davinci-003\",\n" +
                    "    \"prompt\": \"%s\",\n" +
                    "    \"temperature\": 1,\n" +
                    "    \"max_tokens\": 1000\n" +
                    "}", msg));


            HttpUtil.openAIReq("https://api.openai.com/v1/completions", robotConfig.getApikey(), body, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.error("OPENAI FAIL：{}", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String retStr = response.body().string();
                    JsonParser jsonParser = new JsonParser();
                    JsonElement element = jsonParser.parse(retStr);
                    JsonObject root = element.getAsJsonObject();
                    String text = sender.getMemberName() + ":" + root
                            .getAsJsonArray("choices")
                            .get(0).getAsJsonObject()
                            .get("text").getAsString();
                    List<Object> params = new ArrayList<>();
                    params.add(new PlainMsg(text));
                    log.info("OPENAI：{}", params);
                    myTemplate.sendMsg2Group(params, null, sender.getGroup().getId());
                }
            });


        } catch (Exception e) {
            log.error("OPENAI Exception：{}", e.getMessage());
        }
    }


    //chat-gpt
  /* @Override
    public void chatGPT(String msg, Sender sender) {

        MediaType JSON = MediaType.parse("application/json");

        //String conversationId = conversationIDMap.getOrDefault(sender.getId(), "noID");
        String payload = "{\"action\":\"next\",\"messages\":[{\"id\":\"%s\",\"role\":\"user\",\"content\":{\"content_type\":\"text\",\"parts\":[\"%s\"]}}],\"model\":\"text-davinci-002-render\",\"parent_message_id\":\"%s\"}";
        RequestBody body = RequestBody.create(JSON,
                String.format(payload, UUID.randomUUID(), msg, UUID.randomUUID())
        );


        HttpUtil.chatGPT("https://gpt.chatapi.art/backend-api/conversation",robotConfig.getUserAgent()
                ,robotConfig.getCf(), robotConfig.getSessionToken(),body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("CHAT-GPT ERROR :{}", e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonParser jsonParser = new JsonParser();
                String retStr = response.body().string();
                Stack<String> stack = new Stack<>();
                String[] split = retStr.split("\n");
                for (String item : split) {
                    try {
                        String jsonItem = item.substring("data: ".length());
                        JsonElement element = jsonParser.parse(jsonItem);
                        JsonObject root = element.getAsJsonObject();
                        String text = root
                                .getAsJsonObject("message").
                                getAsJsonObject("content")
                                .getAsJsonArray("parts")
                                .get(0).getAsString();
                        // System.out.println(text);
                        stack.push(text);

                    } catch (Exception e) {
                        // e.printStackTrace();
                    }
                }
                String pop = stack.pop();
                while (!stack.isEmpty() && "".equals(pop)) {
                    pop = stack.pop();
                }
                log.debug("CHATGPT MESSAGE：{}", pop);
                List<Object> params = new ArrayList<>();
                params.add(new PlainMsg(pop));
                log.info("CHATGPT：{}", params);
                myTemplate.sendMsg2Group(params, null, sender.getGroup().getId());

            }
        });
    }*/

    @Override
    public void freeChatAPI(String msg, Sender sender) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HttpUtil.freeOpenApi(msg, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("freeChatAPI error:{}", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String retStr = response.body().string();
                log.error("freeChatAPI ret:{}", retStr);
                AiResultVo resultVo = new Gson().fromJson(retStr, AiResultVo.class);
                if (resultVo != null && !resultVo.getChoices().isEmpty()) {
                    AiResultVo.RText rText = resultVo.getChoices().get(0);
                    log.error("freeChatAPI rText:{}", rText.getText());
                    List<Object> params = new ArrayList<>();
                    params.add(new PlainMsg(rText.getText()));
                    log.info("freeChatAPI：{}", params);
                    myTemplate.sendMsg2Group(params, null, sender.getGroup().getId());

                } else {
                    log.error("freeChatAPI : no result");
                }
            }
        });

    }

    @Override
    public void xcAI(String msg, Sender sender) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String url = "https://chatgpt.glimpse.top/requestChat?question=" + URLEncoder.encode(msg.trim()) + "&openid=";
        log.info("xcAi start :{}", msg);
        String rText = HttpUtil.synHttpGet(url,"","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF XWEB/6398");
        log.info("xcAi resp:{}", rText);

        if (rText != null && rText.length() > 0){
            List<Object> params = new ArrayList<>();
            params.add(new PlainMsg(rText));
            log.info("xcAi send：{}", params);
            myTemplate.sendMsg2Group(params, null, sender.getGroup().getId());
        }

        /*HttpUtil.xcAI(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("xcAI error:{}", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String rText = response.body().string();
                log.info("xcAi resp:{}", rText);

                if (rText != null && rText.length() > 0){
                    List<Object> params = new ArrayList<>();
                    params.add(new PlainMsg(rText));
                    log.info("xcAi send：{}", params);
                    myTemplate.sendMsg2Group(params, null, sender.getGroup().getId());
                }
            }
        });*/



    }
}
