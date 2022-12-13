package top.yeyusmile.mirai;

/**
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/4/14 16:01
 */

import lombok.Getter;
import lombok.Setter;

/**
 *  "id": 1097623249,
 *  *                     "name": "我的群_Jack",
 *  *                     "permission": "ADMINISTRATOR"
 */
@Getter
@Setter
public class Group {
    private Long id;
    private String name;
    private String permission;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
