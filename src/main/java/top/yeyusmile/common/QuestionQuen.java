package top.yeyusmile.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.yeyusmile.mirai.Sender;
import top.yeyusmile.vo.MyPair;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 问题队列
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/12/17 13:58
 */
@Component
@Slf4j
public class QuestionQuen {
    private Queue<MyPair> questions = new ConcurrentLinkedQueue();

    private MyPair currentMyPair;

    public MyPair getCurrentMyPair() {
        return currentMyPair;
    }

    public void setCurrentMyPair(MyPair currentMyPair) {
        this.currentMyPair = currentMyPair;
    }

    public void add(Sender sender, String msg) {
        MyPair myPair = new MyPair(sender, msg);
        questions.offer(myPair);
    }

    public MyPair get() {
        MyPair myPair = questions.poll();
        currentMyPair = myPair;
        return currentMyPair;
    }

    public boolean isEmpty() {
        return questions.isEmpty();
    }

}
