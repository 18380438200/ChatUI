package com.example.chatui;

/**
 * Created by apple on 17/12/20.
 */

public class MsgContentBean {
    private int type;
    private String content;

    public MsgContentBean(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
