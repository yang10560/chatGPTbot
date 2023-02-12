package top.yeyusmile.mytest;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yeyusmile.MainApp;
import top.yeyusmile.mirai.Group;
import top.yeyusmile.mirai.Sender;

import top.yeyusmile.service.ChatService;


import top.yeyusmile.service.DealMsgService;
import top.yeyusmile.utils.HttpUtil;
import top.yeyusmile.utils.RSAUtils;
import top.yeyusmile.vo.AiResultVo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/3/16 0:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApp.class)
public class MyTest {


    @Autowired
    private ChatService chatService;

    private Sender sender;

    @PostConstruct
    public void setSender() {
        Sender sender = new Sender();
        sender.setId(29491242l);
        Group group = new Group();
        group.setId(249733992l);
        sender.setGroup(group);
        this.sender = sender;
    }

    @Test
    public void test1() {

        chatService.openAI("java实现冒泡排序", sender);
        try {
            Thread.sleep(480000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  /*  @Test
    public void test2(){

        chatService.chatGPT("你好",sender);
        try {
            Thread.sleep(480000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    //免费api测试
    @Test
    public void test2() {


       chatService.freeChatAPI("你好",sender);

        try {
            Thread.sleep(250000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //xcAI
    @Test
    public void test3() {


        chatService.xcAI("你好",sender);

        try {
            Thread.sleep(250000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void forchangeAITest() {


        chatService.forchangeAI("你好",sender);

        try {
            Thread.sleep(250000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
