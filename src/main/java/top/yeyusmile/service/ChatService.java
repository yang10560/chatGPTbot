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

}
