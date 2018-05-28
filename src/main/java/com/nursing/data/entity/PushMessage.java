package com.nursing.data.entity;

import com.google.gson.Gson;

/**
 * Created by Jack on 2016/1/20.
 * 推送消息
 */
public class PushMessage {

    public int msgID;
    public int msgType;
    public String msgTitle;
    public String msgmemo;
    public String msgTime;
    public int msgread;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
