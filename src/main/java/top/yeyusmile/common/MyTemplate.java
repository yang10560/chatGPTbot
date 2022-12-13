package top.yeyusmile.common;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yeyusmile.mirai.AddReq;
import top.yeyusmile.mirai.PushMessage;
import top.yeyusmile.utils.MiraiApiUtil;

import java.io.IOException;
import java.util.List;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/3/15 23:46
 */
@Component
@Slf4j
public class MyTemplate {

    @Autowired
    private RobotConfig config;


    /**
     * 发群信息
     *
     * @param params
     * @return
     */
    public String sendMsg2Group(List<Object> params, Integer quote, Long group) {
        PushMessage pushMessage = new PushMessage(config.getSessionKey(), group, params);
        pushMessage.setQuote(quote);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(pushMessage).replaceAll("小思","小明"));


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("请求{}：", new Gson().toJson(pushMessage));

        MiraiApiUtil.httpPost(config.getRobotUrl() + "/sendGroupMessage", config.getSessionKey(),
                body, group + "", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        log.error(e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String respStr = response.body().string();
                        log.info("respStr:{}", respStr);
                        //{"code":20,"msg":"Bot被禁言"}

                        response.close();
                    }
                });
        return "resp";
    }


    /**
     * 好友申请操作
     *
     * @param addFriendReq
     * @return
     */
    public String opNewFried(AddReq addFriendReq) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(addFriendReq));
        log.info("请求{}：", new Gson().toJson(addFriendReq));
        MiraiApiUtil.httpPost(config.getRobotUrl() + "/resp/newFriendRequestEvent",
                config.getSessionKey(), body, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        log.error(e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        log.info(response.body().string());
                        response.close();
                    }
                });
        return "resp";
    }

    /**
     * 被邀请进群处理
     *
     * @param addGroup
     * @return
     */
    public String opBeinviteGrop(AddReq addGroup) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(addGroup));
        log.info("请求{}：", new Gson().toJson(addGroup));
        MiraiApiUtil.httpPost(
                config.getRobotUrl() + "/resp/botInvitedJoinGroupRequestEvent",
                config.getSessionKey(), body, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        log.error(e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        log.info(response.body().string());
                        response.close();
                    }
                });
        return "resp";
    }


    /**
     * 禁言解禁
     *
     * @return type 1禁言  0解禁  time=null
     */
    public String mute(Integer time, Long memberId, Long group, int type) {
        PushMessage pushMessage = new PushMessage(config.getSessionKey(), group, null);
        pushMessage.setTime(time);
        pushMessage.setMemberId(memberId);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(pushMessage));
        log.info("请求禁言/解禁{}：", new Gson().toJson(pushMessage));
        String url = "";
        if (type == 1) {
            url = config.getRobotUrl() + "/mute";
        } else {
            url = config.getRobotUrl() + "/unmute";
        }
        MiraiApiUtil.httpPost(url, config.getSessionKey(),
                body, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        log.error(e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println(response.body().string());
                        response.close();
                    }
                });
        return "resp";
    }

    /**
     * 发信息给好友
     *
     * @param params
     * @param quote
     * @param id
     */
    public void sendMsg2Friend(List<Object> params, Integer quote, Long id) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PushMessage pushMessage = new PushMessage(config.getSessionKey(), id, params);
        pushMessage.setQuote(quote);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(pushMessage));
        log.info("请求{}：", new Gson().toJson(pushMessage));
        MiraiApiUtil.httpPost(config.getRobotUrl() + "/sendFriendMessage", config.getSessionKey(),
                body, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        log.error(e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println(response.body().string());
                        response.close();
                    }
                });
    }



}
