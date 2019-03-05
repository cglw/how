package com.hornen.eventbus;

/**
 * Created by Hornen on 15/9/29.
 */
public class EventParameter {
    /**
     * event create time
     */
    public long createTime;

    /**
     * event handle time
     */
    public long fireTime;

    /**
     * event parameter
     */
    public Object obj;

    /**
     * event tag
     */
    public String tag;

    /**
     * create a default event object with param
     * @param obj
     * @return
     */
    public static EventParameter create(Object obj) {
        EventParameter param = new EventParameter();
        param.createTime = System.currentTimeMillis();
        param.obj = obj;
        return param;
    }

    public static EventParameter create() {
        return create(null);
    }
}
