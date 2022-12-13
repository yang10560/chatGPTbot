package top.yeyusmile.mirai.msg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.yeyusmile.common.MsgType;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/14 17:16
 */
@Setter
@Getter
@NoArgsConstructor
public class ImageMsg {
    private String type = MsgType.MSGTYPE_IMAGE;
    private String url;
    private String imageId;
    private String path;
    private String base64;

    public ImageMsg(String text) {
        this.url = text;
    }
}
