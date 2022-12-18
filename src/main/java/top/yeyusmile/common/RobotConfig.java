package top.yeyusmile.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.yeyusmile.utils.HttpUtil;
import top.yeyusmile.utils.JsonUtil;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/3/17 8:05
 */
@Slf4j
@Component
@ConfigurationProperties("yeyu")
public class RobotConfig {


    private String robotQQ;

    private String sessionKey;


    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    //@Value("${yeyu.host}")
    private String host;
    // @Value("${yeyu.port}")
    private String port;
    // @Value("${yeyu.token}")
    private String token;

    private Integer model;

    private String apikey;

    private String sessionToken;

    private String userAgent;

    private String cf;

    private String startPrefix;


    private Integer autoagreeFriend;

    private Integer  autoagreeGroup;



    /*mirai config start*/

    @PostConstruct
    public void miraiConfig() {

        try {
            //http://localhost:8082/verify
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, String.format("{\"verifyKey\":%s}", token));

            String resp = HttpUtil.synHttpPost(getRobotUrl() + "/verify", userAgent, body);
            JsonObject root = JsonUtil.getRoot(resp);


            String session = root.get("session").getAsString();
            sessionKey = session;
            log.info("session:{}", session);
            body = RequestBody.create(JSON, String.format("{\"sessionKey\": \"%s\",\"qq\": %s}",
                    session, robotQQ));
            String resp1 = HttpUtil.synHttpPost(getRobotUrl() + "/bind", userAgent, body);
            log.info("bind:{}", resp1);
        } catch (Exception e) {
            log.error("bind error:{}",e.getMessage());
        }
        ///
        System.out.println(toString());

    }

    /*mirai config end*/


    @Override
    public String toString() {
        return "RobotConfig{" +
                "robotQQ='" + robotQQ + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", token='" + token + '\'' +
                ", model=" + model +
                ", apikey='" + apikey + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", cf='" + cf + '\'' +
                ", startPrefix='" + startPrefix + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }



    /**
     * 机器人请求地址
     *
     * @return
     */
    public String getRobotUrl() {
        return "http://" + host + ":" + port;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRobotQQ(String robotQQ) {
        this.robotQQ = robotQQ;
    }

    public String getRobotQQ() {
        return robotQQ;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setStartPrefix(String startPrefix) {
        this.startPrefix = startPrefix;
    }

    public String getStartPrefix() {
        return startPrefix;
    }


    public Integer getAutoagreeFriend() {
        return autoagreeFriend == null ? 0 : autoagreeFriend;
    }

    public void setAutoagreeFriend(Integer autoagreeFriend) {
        this.autoagreeFriend = autoagreeFriend;
    }

    public Integer getAutoagreeGroup() {
        return autoagreeGroup == null ? 0 : autoagreeGroup;
    }

    public void setAutoagreeGroup(Integer autoagreeGroup) {
        this.autoagreeGroup = autoagreeGroup;
    }
}
