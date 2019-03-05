package com.hornen.eventbus;

import android.os.Handler;
import android.os.Message;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Hornen on 15/9/29.
 */
public class EventsController {

    /**
     * to set eventbus is enable
     */
    private boolean enable = true;

    /**
     * dealer callback holder map
     */
    private Map<Integer, Set<Action<EventParameter>>> dealers = new HashMap();

    private Handler dispatcher = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            EventsController.this.dispatchMessage(message);

            return true;
        }
    });


    /**
     * dispatch event bus to handle events
     * @param message
     */
    private void dispatchMessage(Message message) {

        if(this.enable) {
            int receiverId = message.what;
            EventParameter params = (EventParameter)message.obj;

            if(this.dealers.containsKey(Integer.valueOf(receiverId))) {
                Iterator it = ((Set)this.dealers.get(Integer.valueOf(receiverId))).iterator();

                while(it.hasNext()) {
                    ((Action)it.next()).exectue(params);
                }
            }

        }

    }

    /**
     * set eventbus enable status
     * @param enable
     */
    public void setEnable(boolean enable) { this.enable = enable;};

    /**
     * register a dealer callback to handle event with eventbus
     * @param dealerId
     * @param callback
     */
    public void registDealer(int dealerId, Action<EventParameter> callback) {
        if(!this.dealers.containsKey(Integer.valueOf(dealerId))) {
            this.dealers.put(Integer.valueOf((dealerId)), new CopyOnWriteArraySet<Action<EventParameter>>());
        }

        this.dealers.get(Integer.valueOf(dealerId)).add(callback);
    }

    /**
     * remove dealer callback without to handle event
     * @param dealerId
     * @param callback
     */
    public void unregistDealer(int dealerId, Action<EventParameter> callback) {
        if(this.dealers.containsKey(Integer.valueOf(dealerId))) {
            this.dealers.get(Integer.valueOf(dealerId)).remove(callback);
        }
    }

    /**
     * clear all dealer events callback
     * @param dealerId
     */
    public void clearDealer(int dealerId) {
        if(this.dealers.containsKey(Integer.valueOf(dealerId))) {
            this.dealers.get(Integer.valueOf(dealerId)).clear();
        }
    }

    /**
     * Send a event to dealer
     * @param dealerId
     * @param param
     */
    public void fire(int dealerId, EventParameter param) {
        param.fireTime = System.currentTimeMillis();

        Message message = this.dispatcher.obtainMessage();
        message.what = dealerId;
        message.obj = param;
        this.dispatcher.sendMessage(message);
    }


}
