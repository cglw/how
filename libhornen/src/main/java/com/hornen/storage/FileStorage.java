package com.hornen.storage;

import android.content.Context;

import com.hornen.common.MD5;
import com.hornen.data.JsonProcessorBasedFastJson;
import com.hornen.io.StreamToolkit;

import java.io.File;
import java.io.IOException;

/**
 * Created by Hornen on 15/9/29.
 */
public class FileStorage implements IStorageProvider {

    private final String zoneName;
    private final Context cxt;
    private final JsonProcessorBasedFastJson jsonProcessor;

    public FileStorage(Context cxt) {
        this(cxt, "generic");
    }

    public FileStorage(Context cxt, String zoneName) {
        this.cxt = cxt.getApplicationContext();
        this.zoneName = zoneName;
        jsonProcessor = new JsonProcessorBasedFastJson();
    }

    @Override
    public void save(String key, Object obj) {

        byte[] raw = this.jsonProcessor.serialize(obj);
        String path = this.getDataFilePath(key);

        try {
            StreamToolkit.writeRawToFile(raw, path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageException("cannot save data via file storage", e);
        }
    }

    @Override
    public <X> X resolve(String key, Class<X> clazz) {
        String path = this.getDataFilePath(key);
        if (!(new File(path)).exists()) {
            return null;
        } else {
            byte[] raw1;
            try {
                raw1 = StreamToolkit.readRawFromFile(path);
            } catch (IOException var6) {
                return null;
//                throw new StorageException("can not parse this raw via filestorage", var6);

            }

            return this.jsonProcessor.deserialize(raw1, clazz);
        }
    }

    @Override
    public boolean exist(String key) {
        String path = this.getDataFilePath(key);
        return new File(path).exists();
    }

    @Override
    public void remove(String key) {
        String path = this.getDataFilePath(key);
        (new File(path)).delete();
    }

    @Override
    public void removeAll() {
        File[] files = this.cxt.getDir(this.zoneName, 0).listFiles();
        File[] var5 = files;
        int var4 = files.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            File file = var5[var3];
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @Override
    public String getProviderIden() {
        return "FileStorage";
    }

    private String getDataFilePath(String key) {
        String fileName = MD5.getMD5Str(key);
        String dirPath = this.cxt.getDir(this.zoneName, 0).getAbsolutePath();
        return dirPath + "/" + fileName;
    }
}
