package top.yeyusmile.mirai;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {
 *   "sessionKey":"YourSessionKey",
 *   "eventId":12345678,
 *   "fromId":123456,
 *   "groupId":654321,
 *   "operate":0,
 *   "message":""
 * }
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/14 20:16
 */
@NoArgsConstructor
@Getter
@Setter
public class AddReq {
    private String type;
    private String sessionKey;
    private Long eventId;
    private Long fromId;
    private Long groupId;
    private Integer operate;
    private String message;
    private String nick;
    private String groupName;

    /**
     * operate	说明
     * 0	同意添加好友
     * 1	拒绝添加好友
     * 2	拒绝添加好友并添加黑名单，不再接收该用户的好友申请
     *
     * 被邀请进群
     * operate	说明
     * 0	同意邀请
     * 1	拒绝邀请
     *
     * @param sessionKey
     * @param eventId
     * @param fromId
     * @param groupId
     * @param operate
     * @param message
     */

    public AddReq(String sessionKey, Long eventId, Long fromId, Long groupId, Integer operate, String message) {
        this.sessionKey = sessionKey;
        this.eventId = eventId;
        this.fromId = fromId;
        this.groupId = groupId;
        this.operate = operate;
        this.message = message;
    }


}
