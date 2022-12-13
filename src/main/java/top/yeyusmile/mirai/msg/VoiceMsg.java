package top.yeyusmile.mirai.msg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.yeyusmile.common.MsgType;

/**
 *
 * {
 *     "type": "Voice",
 *     "voiceId": "23C477720A37FEB6A9EE4BCCF654014F.amr",
 *     "url": "https://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
 *     "path": null,
 *     "base64": null,
 *     "length": 1024,
 * }
 *
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/7/23 12:08
 */
@Setter
@Getter
@NoArgsConstructor
public class VoiceMsg {
    private String type = MsgType.MSGTYPE_VOICE;
    private String url;

    public VoiceMsg(String url) {
        this.url = url;
    }
}
