package top.yeyusmile.mirai.msg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.yeyusmile.common.MsgType;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/15 11:21
 */
@Setter
@Getter
@NoArgsConstructor
public class XmlMsg {
    private String type = MsgType.MSGTYPE_XML;
    private String xml;

    public XmlMsg(String xml) {
        this.xml = xml;
    }
}


