package top.yeyusmile.quartz;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.yeyusmile.common.RobotConfig;
import top.yeyusmile.service.DealMsgService;
import top.yeyusmile.utils.HttpUtil;
import top.yeyusmile.utils.JsonUtil;


import java.util.Date;

/**
 * 每秒获取信息
 *
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/3/17 9:01
 */

@Component
@Slf4j
public class MsgJob {

    @Autowired
    private DealMsgService dealMsgService;

    @Autowired
    private RobotConfig robotConfig;

    private String url = "http://127.0.0.1:8082";

    @Scheduled(cron = "*/2 * * * * ?")
    //@Scheduled(cron = "0 * * * * ?")
    public void executeJob() {
        url = robotConfig.getRobotUrl();
        log.info("MsgJob is running....");
        //http://localhost:8082/countMessage?sessionKey=rCPVMqVh
        String resp1 = HttpUtil.synHttpGet(url + "/countMessage?sessionKey=" +
                robotConfig.getSessionKey(), "",robotConfig.getUserAgent());
        try {
            JsonObject root = JsonUtil.getRoot(resp1);
            int count = root.get("data").getAsInt();
            if (count < 1) return;
            int readCount = count + 10;
            String resp2 = HttpUtil.synHttpGet(String.format(url + "/fetchMessage?sessionKey=%s&count=%s",
                    robotConfig.getSessionKey(), readCount), "",robotConfig.getUserAgent());
            //处理信息
            dealMsgService.dealMsg(resp2);
        } catch (Exception e) {
           log.error("msg job fail:{}",e.getMessage());

        }

    }
}
