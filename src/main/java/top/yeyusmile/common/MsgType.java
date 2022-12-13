package top.yeyusmile.common;

/**
 * https://docs.mirai.mamoe.net/mirai-api-http/api/MessageType.html#source
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/14 14:40
 */

public class MsgType {
    //消息链类型
    public static final String MSGTYPE_FRIEDN = "FriendMessage";//消息类型_好友
    public static final String MSGTYPE_GROUP = "GroupMessage";//消息类型_群
    public static final String MSGTYPE_STRANGER = "StrangerMessage";//陌生人信息
    public static final String MSGTYPE_OTHERCLIENT = "OtherClientMessage";//其他客户短信息
    public static final String MSGTYPE_GROUP_TEMP = "TempMessage";//消息类型_群临时信息

    //消息类型
    //https://docs.mirai.mamoe.net/mirai-api-http/api/MessageType.html#source
    public static final String MSGTYPE_SOURCE = "Source";
    public static final String MSGTYPE_QUOTE = "Quote";
    public static final String MSGTYPE_AT = "At";
    public static final String MSGTYPE_ATALL = "AtAll";
    public static final String MSGTYPE_FACE = "Face";
    public static final String MSGTYPE_PLAIN = "Plain";
    public static final String MSGTYPE_IMAGE = "Image";
    public static final String MSGTYPE_FLASHIMAGE = "FlashImage";
    public static final String MSGTYPE_VOICE = "Voice";
    public static final String MSGTYPE_XML = "Xml";
    public static final String MSGTYPE_JSON = "Json";
    public static final String MSGTYPE_APP = "App";
    public static final String MSGTYPE_POKE = "Poke";
    public static final String MSGTYPE_DICE = "Dice";
    public static final String MSGTYPE_MARKETFACE = "MarketFace";
    public static final String MSGTYPE_MUSICSHARE = "MusicShare";
    public static final String MSGTYPE_FORWARDMESSAGE = "ForwardMessage";
    public static final String MSGTYPE_FILE = "File";
    public static final String MSGTYPE_MIRAICODE = "MiraiCode";





    //事件：

    public static final String MSGTYPE_GroupRecallEvent = "GroupRecallEvent";//群撤回
    public static final String MSGTYPE_MemberCardChangeEvent = "MemberCardChangeEvent";//群名片改变
    public static final String MSGTYPE_MemberJoinRequestEvent = "MemberJoinRequestEvent";//用户入群申请（Bot需要有管理员权限）
    public static final String MSGTYPE_BotInvitedJoinGroupRequestEvent =
            "BotInvitedJoinGroupRequestEvent";//Bot被邀请入群申请

    public static final String MSGTYPE_MemberLeaveEventKick = "MemberLeaveEventKick";//成员被踢出群（该成员不是Bot）
    public static final String MSGTYPE_MemberLeaveEventQuit = "MemberLeaveEventQuit";//成员主动离群（该成员不是Bot）
    public static final String MSGTYPE_NewFriendRequestEvent = "NewFriendRequestEvent";//好友申请


}
