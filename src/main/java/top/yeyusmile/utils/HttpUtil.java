package top.yeyusmile.utils;


import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2021/1/15 14:22
 */
@Slf4j
public class HttpUtil {


    /**
     * http post
     * 同步方法
     *
     * @param address
     * @param requestBody
     * @return
     */
    public static synchronized String synHttpPost(String address, String userAgent, RequestBody requestBody) {

        OkHttpClient client = new OkHttpClient();
        client.dispatcher().setMaxRequests(3000);
        client.dispatcher().setMaxRequestsPerHost(1000);
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .addHeader("user-agent", userAgent)
                .build();

        String ret = "";
        try {
            ret = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            log.error("http error:{}",e.getMessage());
        }
        return ret;
    }


    /**
     * http get
     * 同步方法
     *
     * @param address
     * @return
     */
    public static synchronized String synHttpGet(String address, String cookie, String userAgent) {

        OkHttpClient client = new OkHttpClient();
        client.dispatcher().setMaxRequests(3000);
        client.dispatcher().setMaxRequestsPerHost(1000);
        Request request = new Request.Builder()
                .url(address)
                .addHeader("cookie", cookie)
                .addHeader("user-agent", userAgent)
                .build();
        String ret = "";
        try {
            ret = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            log.error("http error:{}",e.getMessage());
        }

        return ret;


    }


    /**
     * openAI官方API
     *
     * @param address
     * @param requestBody
     * @return
     */
    public static synchronized void openAIReq(String address, String apikey, RequestBody requestBody, Callback callback) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .sslSocketFactory(SSLUtils.getSSLSocketFactory(), SSLUtils.getX509TrustManager())
                .hostnameVerifier(SSLUtils.getHostnameVerifier())
                .build();
        //OkHttpClient client = new OkHttpClient();
        client.dispatcher().setMaxRequests(3000);
        client.dispatcher().setMaxRequestsPerHost(1000);
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                //.addHeader("Authorization","Bearer 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") //我的
                .addHeader("Authorization", "Bearer " + apikey)
                //  .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36")
                .build();
        client.newCall(request).enqueue(callback);

    }


    /**
     * chat-gpt
     *
     * @param address
     * @param requestBody
     * @return
     */
    public static synchronized void chatGPT(String address, String userAgent, String cf,String sessionToken, RequestBody requestBody, Callback callback) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(180, TimeUnit.SECONDS)
                .connectTimeout(180, TimeUnit.SECONDS)
                .sslSocketFactory(SSLUtils.getSSLSocketFactory(), SSLUtils.getX509TrustManager())
                .hostnameVerifier(SSLUtils.getHostnameVerifier())
                .build();
        //OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+ sessionToken )
                .addHeader("Accept", "text/event-stream")
                .addHeader("referer", "https://chat.openai.com/chat")
                .addHeader("cookie", "cf_clearance=" + cf)
                .addHeader("user-agent", userAgent)
                .build();
        client.newCall(request).enqueue(callback);
    }


}
