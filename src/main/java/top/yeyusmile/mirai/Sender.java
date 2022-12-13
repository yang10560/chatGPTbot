package top.yeyusmile.mirai;

/**
 *
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/14 15:59
 */

import lombok.Getter;
import lombok.Setter;

/**
 *  "sender": {
 *                 "id": 29491242,
 *                 "memberName": "夜雨",
 *                 "specialTitle": "",
 *                 "permission": "OWNER",
 *                 "joinTimestamp": 1588436049,
 *                 "lastSpeakTimestamp": 1649923067,
 *                 "muteTimeRemaining": 0,
 *                 "group": {
 *                     "id": 1097623249,
 *                     "name": "我的群_Jack",
 *                     "permission": "ADMINISTRATOR"
 *                 }
 *             }
 */
@Setter
@Getter
public class Sender {
    private Long id;
    private String memberName;
    private String specialTitle;
    private String permission;
    private Long joinTimestamp;
    private Long lastSpeakTimestamp;
    private Integer muteTimeRemaining;
    private Group group;

    @Override
    public String toString() {
        return "Sender{" +
                "id=" + id +
                ", memberName='" + memberName + '\'' +
                ", specialTitle='" + specialTitle + '\'' +
                ", permission='" + permission + '\'' +
                ", joinTimestamp=" + joinTimestamp +
                ", lastSpeakTimestamp=" + lastSpeakTimestamp +
                ", muteTimeRemaining=" + muteTimeRemaining +
                ", group=" + group +
                '}';
    }
}
