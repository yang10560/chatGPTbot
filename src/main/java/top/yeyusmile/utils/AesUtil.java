package top.yeyusmile.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
 
public class AesUtil {
 
    //加密
    public static String encrypt(String data, String key, String ivStr) {
        //偏移量
        byte[] iv = ivStr.getBytes();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int length = dataBytes.length;
            //计算需填充长度
            if (length % blockSize != 0) {
                length = length + (blockSize - (length % blockSize));
            }
            byte[] plaintext = new byte[length];
            //填充
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            //设置偏移量参数
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encryped = cipher.doFinal(plaintext);
 
            return Base64.getMimeEncoder().encodeToString(encryped);
 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    //解密
    public static String desEncrypt(String data, String key, String ivStr) {
 
        byte[] iv = ivStr.getBytes();
 
        try {
            byte[] encryp = Base64.getMimeDecoder().decode(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] original = cipher.doFinal(encryp);
            return new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //测试
    public static void main(String[] args) {
        String date = "你好";
        String key = "L#$@XowPu!uZ&c%u";
        String ivStr = "2auvLZzxz7bo#^84";
        String encrypt = encrypt(date,key, ivStr);
        String desencrypt = desEncrypt(encrypt, key, ivStr);
        System.out.println("加密后:"+encrypt);
        System.out.println("解密后:"+desencrypt);

        //  "zI5vqVUiedqWs6p9csg0Ew=="

    }
 
}