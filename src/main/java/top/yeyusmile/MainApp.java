package top.yeyusmile;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.DecimalFormat;

/**
 * A Camel Application
 */
@ServletComponentScan(basePackages = "top.yeyusmile.common")
@SpringBootApplication
@EnableScheduling
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class,args);

        int byteToMb = 1024 * 1024;
        //显示JVM总内存
        long totalMem =Runtime.getRuntime().totalMemory()/byteToMb;
        //显示JVM尝试使用的最大内存
        long maxMem = Runtime.getRuntime().maxMemory()/byteToMb;
        //空暇内存
        long freeMem = Runtime.getRuntime().freeMemory()/byteToMb;
        System.out.println("total mem:"+totalMem);
        System.out.println("jvm mem:"+maxMem);
        System.out.println("free mem:"+freeMem);
    }


}

