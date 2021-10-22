package com.wangqingyi.eventbus.message;

/**
 * 事件类
 *
 * @author WangQingYi
 * @since  2021/10/21
 */
public class MessageEvent {

    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
