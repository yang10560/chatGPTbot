package top.yeyusmile.mytest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yeyusmile.MainApp;
import top.yeyusmile.mirai.Group;
import top.yeyusmile.mirai.Sender;

import top.yeyusmile.service.ChatService;


import javax.annotation.PostConstruct;

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

    //wxchat
    @Test
    public void test3() {


        chatService.wxchatgpt("你好",sender);

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
