package com.hornen.data;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by Hornen on 15/11/24.
 */
public class JsonProcessorBasedFastJson implements IJsonProcessor {
    @Override
    public byte[] serialize(Object var1) {
        String json = JSON.toJSONString(var1);

        try {
            return json.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("unsupported encoding utf-8");
        }
    }

    @Override
    public <T> T deserialize(byte[] var1, Class<T> clazz) {
        try {
            return JSON.parseObject(new String(var1, "utf-8"), clazz);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("unsupported encoding utf-8");
        }
    }
}
