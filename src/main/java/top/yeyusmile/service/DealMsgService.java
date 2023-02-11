package top.yeyusmile.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yeyusmile.common.MsgType;
import top.yeyusmile.common.MyTemplate;
import top.yeyusmile.common.QuestionQuen;
import top.yeyusmile.common.RobotConfig;
import top.yeyusmile.mirai.AddReq;
import top.yeyusmile.mirai.Sender;
import top.yeyusmile.mirai.msg.PlainMsg;
import top.yeyusmile.utils.JsonUtil;


import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 接收信息处理中心
 *
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/14 14:17
 */
@Slf4j
@Service
public class DealMsgService {

    @Autowired
    private RobotConfig robotConfig;


    private Gson gson = JsonUtil.getGson();

    @Autowired
    private MyTemplate myTemplate;


    @Autowired
    private ChatService chatService;

    @Autowired
    private QuestionQuen questionQuen;//chatgpt问题队列


    /**
     * @param raw 源数据
     * @throws Exception
     */
    public void dealMsg(String raw) throws Exception {

        JsonObject root = JsonUtil.getRoot(raw);
        int code = root.get("code").getAsInt();
        if (code != 0) {
            log.info(root.toString());
            return;
        }
        JsonArray data = root.get("data").getAsJsonArray();

        for (int i = 0; i < data.size(); i++) {
            JsonObject obj = data.get(i).getAsJsonObject();
            String msgChainType = obj.get("type").getAsString();//接收类型
            Sender sender = null;
            try {
                JsonObject senderjson = obj.get("sender").getAsJsonObject();
                sender = gson.fromJson(senderjson, Sender.class);
            } catch (Exception e) {
                //System.out.println(obj);
            }

            if (msgChainType.contains(MsgType.MSGTYPE_GROUP)) {//群消息
                dealGroupMsg(sender, obj);

            }

            if (msgChainType.contains(MsgType.MSGTYPE_FRIEDN)) {//好友消息
                dealFriendMsg(sender, obj);

            }


            if (msgChainType.contains(MsgType.MSGTYPE_NewFriendRequestEvent)) {//好友申请
                NewFriendRequestEvent(obj);

            }

            if (msgChainType.contains(MsgType.MSGTYPE_BotInvitedJoinGroupRequestEvent)) {//被群邀请申请
                BotInvitedJoinGroupRequestEvent(obj);

            }


        }

    }


    /**
     * 被邀请入群
     *
     * @param obj
     */
    private void BotInvitedJoinGroupRequestEvent(JsonObject obj) {
        AddReq beInvitedGroupReq = gson.fromJson(obj, AddReq.class);
        beInvitedGroupReq.setOperate(robotConfig.getAutoagreeGroup());//同意
        beInvitedGroupReq.setSessionKey(robotConfig.getSessionKey());
        myTemplate.opBeinviteGrop(beInvitedGroupReq);
    }

    /**
     * 好友申请同意
     *
     * @param obj
     */
    private void NewFriendRequestEvent(JsonObject obj) {
        AddReq addFriendReq = gson.fromJson(obj, AddReq.class);
        addFriendReq.setOperate(robotConfig.getAutoagreeFriend());//同意
        addFriendReq.setSessionKey(robotConfig.getSessionKey());
        myTemplate.opNewFried(addFriendReq);

    }


    /**
     * obj:
     * {
     * "type": "GroupMessage",
     * "messageChain": [
     * {
     * "type": "Source",
     * "id": 9010,
     * "time": 1649928060
     * },
     * {
     * "type": "Image",
     * "imageId": "{7943931D-C508-2485-2C2A-BAF7687154B8}.jpg",
     * "url": "http://gchat.qpic.cn/gchatpic_new/1545417642/750406452-2249698507-7943931DC50824852C2ABAF7687154B8/0?term=2",
     * "path": null,
     * "base64": null
     * }
     * ],
     * "sender": {
     * }
     * },
     * 处理群事件
     *
     * @param sender
     * @param obj
     */
    private void dealGroupMsg(Sender sender, JsonObject obj) throws UnsupportedEncodingException {

        JsonArray messagesChains = obj.get("messageChain").getAsJsonArray();
        for (int i = 0; i < messagesChains.size(); i++) {
            JsonObject msgObj = messagesChains.get(i).getAsJsonObject();
            String msgType = msgObj.get("type").getAsString();//消息类型
            if (msgType.contains(MsgType.MSGTYPE_PLAIN)) {//解析文本数据
                String msg = msgObj.get("text").getAsString();
                if ("菜单".equals(msg) || "功能".equals(msg)) {
                    List<Object> params = new ArrayList<>();
                    params.add(new PlainMsg(getMenu()));
                    myTemplate.sendMsg2Group(params, null, sender.getGroup().getId());
                    break;
                }

                if ("加群".equals(msg)) {
                    List<Object> params = new ArrayList<>();
                    params.add(new PlainMsg("249733992"));
                    myTemplate.sendMsg2Group(params, null, sender.getGroup().getId());
                    break;
                }

                if (msg != null && !"".equals(msg) && msg.length() > 1) {
                    log.info("accept from {}-{} msg:{}", sender.getId(), sender.getMemberName(), msg);
                    if (robotConfig.isPrefix(msg)){
                        switch (robotConfig.getModel()){
                            case 0:
                                chatService.openAI(msg.substring(robotConfig.prefixLength(msg)).trim(), sender);
                                break;
                            //model==0   openai
                            case 1:
                                questionQuen.add(sender, msg.substring(robotConfig.prefixLength(msg)).trim());
                                break;
                            ////model=1   chatgpt
                            case 2:
                                //model=2   免费Api
                                chatService.freeChatAPI(msg,sender);
                                break;

                            case 3:
                                //mode =3
                                chatService.xcAI(msg.substring(robotConfig.prefixLength(msg)).trim(),sender);
                                break;

                            default:
                        }
                    }

                }

            }


        }
    }


    /**
     * 这里是群好友消息事件 不处理
     *
     * @param sender
     * @param obj
     */
    private void dealFriendMsg(Sender sender, JsonObject obj) {
        // List<Object> params = new ArrayList<>();
        //params.add(new PlainMsg("不再处理好友消息。只处理群信息"));
        // myTemplate.sendMsg2Friend(params, null, sender.getId());
    }


    private String getMenu() {

        return "------菜单-功能------\n" +
                "在消息前加" + robotConfig.getStartPrefix() + "号即可提问AI\n" +
                "------夜雨------\n" +
                "----Power By Mirai----";

    }

}
