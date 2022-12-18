package top.yeyusmile.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yeyusmile.mirai.Sender;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPair {
    Sender sender;
    String msg;
}