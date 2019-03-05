package com.hornen.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Hornen on 15/9/29.
 */
public class StreamToolkit {

    public static void writeRawToFile(byte[] raw, String outPath) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(outPath));

        try {
            writeRawToStream(raw, fos);
        } finally {
            if (null != fos) {
                fos.flush();
                fos.close();
            }
        }
    }

    public static void writeRawToStream(byte[] raw, OutputStream oos) throws IOException {
        oos.write(raw);
    }

    public static byte[] readRawFromFile(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));

        byte[] reads;
        try {
            reads = readRawFromStream(fis);
        } finally {
            if (null != fis) {
                fis.close();
            }
        }

        return reads;
    }


    public static byte[] readRawFromStream(InputStream iis) throws IOException {
        byte[] buffer = new byte[1024];
        int reads = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        while((reads = iis.read(buffer)) > 0) {
            baos.write(buffer, 0, reads);
        }

        return baos.toByteArray();
    }

    public static void writeText(String path, String text) throws IOException {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(path);
            fos.write(text.getBytes());
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException var8) {

                }
            }

        }

    }

    public static String readText(String path) throws Exception {
        FileInputStream fis = null;

        String var5;
        try {
            fis = new FileInputStream(path);
            int len = fis.available();
            byte[] raw = new byte[len];
            fis.read(raw);
            var5 = new String(raw);
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException var10) {

                }
            }

        }

        return var5;
    }

    public static void writeTo(InputStream iis, OutputStream oos) throws IOException {
        byte[] buf = new byte[10240];

        int reads;
        while((reads = iis.read(buf)) > 0) {
            oos.write(buf, 0, reads);
        }

    }
}
