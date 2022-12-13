package top.yeyusmile.mirai.msg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.yeyusmile.common.MsgType;

/**
 * {
 * "type": "MusicShare",
 * "kind;
 * "title;
 * "summary;
 * "jumpUrl;
 * "pictureUrl;
 * "musicUrl;
 * "brief": "String"
 * }
 *
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/6/30 12:02
 */
@Setter
@Getter
@NoArgsConstructor
public class MusicShareMsg {
    private String type = MsgType.MSGTYPE_MUSICSHARE;
    private String kind;
    private String title;
    private String summary;
    private String jumpUrl;
    private String pictureUrl;
    private String musicUrl;
    private String brief;

    public MusicShareMsg(String kind, String title, String summary, String jumpUrl, String pictureUrl, String musicUrl, String brief) {
        this.kind = kind;
        this.title = title;
        this.summary = summary;
        this.jumpUrl = jumpUrl;
        this.pictureUrl = pictureUrl;
        this.musicUrl = musicUrl;
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "MusicShareMsg{" +
                "type='" + type + '\'' +
                ", kind='" + kind + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", jumpUrl='" + jumpUrl + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
