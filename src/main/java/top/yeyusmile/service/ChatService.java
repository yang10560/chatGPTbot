package top.yeyusmile.service;


import top.yeyusmile.mirai.Sender;

/**
 * 便民服务
 *
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/3/16 15:14
 */
public interface ChatService {




    /*
    openAi
    https://api.openai.com/v1/completions
     */

    void openAI(String msg, Sender sender);


    // void chatGPT(String msg, Sender sender);

    //免费
    void freeChatAPI(String msg, Sender sender);

    //"https://wenxin110.top/api/chat_gpt?text=" + encodeURI(q)
    void wxchatgpt(String msg, Sender sender);

    void forchangeAI(String trim, Sender sender);
}
