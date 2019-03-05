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
public class FileToolkit {
    public FileToolkit() {
    }

    public static byte[] readFileToRaw(String filepath) throws IOException {
        FileInputStream fis = null;

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            fis = new FileInputStream(filepath);
            byte[] buffer = new byte[4096];
            boolean nReaded = false;

            int nReaded1;
            while((nReaded1 = fis.read(buffer)) > 0) {
                bos.write(buffer, 0, nReaded1);
            }

            byte[] var6 = bos.toByteArray();
            return var6;
        } finally {
            if(fis != null) {
                fis.close();
            }

        }
    }

    public static void deleteAll(File pathF) {
        internalDeleteFolder(pathF);
    }

    public static long getDirFilesSizeOneLayer(File pathF) {
        long totalSize = 0L;
        File[] subFiles = pathF.listFiles();
        File[] var7 = subFiles;
        int var6 = subFiles.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            File f = var7[var5];
            totalSize += f.length();
        }

        return totalSize;
    }

    public static String getCapatibleSizeStringInBytes(long size) {
        return size < 1024L?size + "Byte":(size < 1048576L?trunct1Float((float)size / 1024.0F) + "KB":(size < 1073741824L?trunct1Float((float)size / 1024.0F / 1024.0F) + "MB":trunct1Float((float)size / 1024.0F / 1024.0F / 1024.0F) + "GB"));
    }

    public static float trunct1Float(float value) {
        int cv = (int)(value * 10.0F);
        return (float)(cv / 10);
    }

    private static long copyLarge(InputStream input, OutputStream output) throws IOException {
        return copyLarge(input, output, new byte[4096]);
    }

    private static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
        long count = 0L;

        int n1;
        for(boolean n = false; -1 != (n1 = input.read(buffer)); count += (long)n1) {
            output.write(buffer, 0, n1);
        }

        return count;
    }

    public static void copyFile(String srcPath, String destPath) {
        File fromFile = new File(srcPath);
        File toFile = new File(destPath);
        FileInputStream input = null;
        FileOutputStream output = null;

        try {
            input = new FileInputStream(fromFile);
            output = new FileOutputStream(toFile);
            copyLarge(input, output);
        } catch (Exception var19) {

        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException var18) {

                }
            }

            if(output != null) {
                try {
                    output.close();
                } catch (IOException var17) {

                }
            }

        }

    }

    private static void internalDeleteFolder(File file) {
        if(file.isFile()) {
            file.delete();
        } else {
            File[] subFiles = file.listFiles();
            if(subFiles != null) {
                File[] var5 = subFiles;
                int var4 = subFiles.length;

                for(int var3 = 0; var3 < var4; ++var3) {
                    File f = var5[var3];
                    internalDeleteFolder(f);
                }

                file.delete();
            }
        }
    }

    public static int copy(InputStream from, String descPath) {
        try {
            deleteAll(new File(descPath));
            FileOutputStream ex = new FileOutputStream(descPath);
            byte[] buf = new byte[1024];

            int c;
            while((c = from.read(buf)) > 0) {
                ex.write(buf, 0, c);
            }

            from.close();
            ex.close();
            return 0;
        } catch (Exception var5) {
            var5.printStackTrace();
            return -1;
        }
    }
}
