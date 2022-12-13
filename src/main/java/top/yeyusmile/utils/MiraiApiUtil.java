package top.yeyusmile.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/14 13:30
 */
public class MiraiApiUtil {

    /**
     * get 异步
     *
     * @param address
     * @param callback
     */
    public static void httpGet(String address, String sessionKey, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        client.dispatcher().setMaxRequests(3000);
        client.dispatcher().setMaxRequestsPerHost(1000);
        Request request = new Request.Builder()
                .url(address)
                .addHeader("sessionKey", sessionKey)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36")
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * post 异步
     *
     * @param address
     * @param requestBody
     * @param callback
     */
    public static void httpPost(String address,String sessionKey, RequestBody requestBody, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        client.dispatcher().setMaxRequests(3000);
        client.dispatcher().setMaxRequestsPerHost(1000);
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .addHeader("sessionKey", sessionKey)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36")
                .build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * post 异步带头
     *
     * @param address
     * @param requestBody
     * @param callback
     */
    public static void httpPost(String address,String sessionKey, RequestBody requestBody,String header, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        client.dispatcher().setMaxRequests(3000);
        client.dispatcher().setMaxRequestsPerHost(1000);
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .addHeader("gid", header)
                .addHeader("sessionKey", sessionKey)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36")
                .build();
        client.newCall(request).enqueue(callback);
    }


}
