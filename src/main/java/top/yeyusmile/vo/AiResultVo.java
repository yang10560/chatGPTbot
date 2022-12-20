package top.yeyusmile.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * {
 *     "id": "cmpl-6PQd0g9UqFPZnUVdH5bpaM3bQgyyl",
 *     "object": "text_completion",
 *     "created": 1671518578,
 *     "model": "text-davinci-003",
 *     "choices": [
 *         {
 *             "text": "\n\n我今年23岁。",
 *             "index": 0,
 *             "logprobs": null,
 *             "finish_reason": "stop"
 *         }
 *     ],
 *     "usage": {
 *         "prompt_tokens": 7,
 *         "completion_tokens": 13,
 *         "total_tokens": 20
 *     }
 * }
 *
 * {"choices": [{"text": "请求过于频繁请稍后再试！恶意请求会被制裁哦！"}]}
 * @author 夜雨
 * @Web www.yeyusmile.top
 * @date 2022/12/20 15:09
 */
@Setter
@Getter
public class AiResultVo {
    private String id;
    private List<RText> choices;

    public static class RText {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
