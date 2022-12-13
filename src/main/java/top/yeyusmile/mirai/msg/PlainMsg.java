package top.yeyusmile.mirai.msg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.yeyusmile.common.MsgType;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/14 17:13
 */
@Setter
@Getter
@NoArgsConstructor
public class PlainMsg {
    private String type = MsgType.MSGTYPE_PLAIN;
    private String text;

    public PlainMsg(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PlainMsg{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
