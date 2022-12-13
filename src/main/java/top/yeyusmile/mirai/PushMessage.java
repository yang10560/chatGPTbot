package top.yeyusmile.mirai;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 夜雨
 */

/**
 * {
 *   "sessionKey":"rCPVMqVh",
 *   "target":873388675,
 *   "messageChain":[
 *     { "type":"Plain", "text":"hello\n" },
 *     { "type":"Plain", "text":"world" },
 *     { "type":"Plain", "text":",mirai robot" },
 *     { "type":"Image", "url":"https://i0.hdslb.com/bfs/album/67fc4e6b417d9c68ef98ba71d5e79505bbad97a1.png" }
 *   ]
 * }
 */
@Setter
@Getter
public class PushMessage {


    private String sessionKey;
    private Long target;//引用
    private Integer quote;
    private List<Object> messageChain;

    public PushMessage() {
    }

    public PushMessage(String sessionKey, Long target, List<Object> messageChain) {
        this.sessionKey = sessionKey;
        this.target = target;
        this.messageChain = messageChain;
    }

    //禁言[POST] /mute
//    {
//        "sessionKey":"YourSessionKey",
//            "target":123456789,//群
//            "memberId":987654321,//Q
//            "time":1800
//    }
    private Long memberId;
    private Integer time;//秒

    //解禁[POST] /unmute
//    {
//        "sessionKey":"YourSessionKey",
//            "target":123456789,
//            "memberId":987654321
//    }








}
