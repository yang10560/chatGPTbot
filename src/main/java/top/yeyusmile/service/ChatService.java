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

    //https://chatgpt.glimpse.top/requestChat?question=%0A%0A%E4%BD%A0%E5%A5%BD&openid=

    void xcAI(String msg, Sender sender);

    void forchangeAI(String trim, Sender sender);
}
