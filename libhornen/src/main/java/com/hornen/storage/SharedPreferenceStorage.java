package com.hornen.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hornen on 15/9/29.
 */
public class SharedPreferenceStorage implements IStorageProvider {

    private final Context cxt;
    private final String zoneName;

    public SharedPreferenceStorage(Context cxt) {
        this(cxt, "generic");
    }

    public SharedPreferenceStorage(Context cxt, String zoneName) {
        this.cxt = cxt.getApplicationContext();
        this.zoneName = zoneName;
    }

    @Override
    public void save(String key, Object obj) {
        this.setStringValue(key, obj.toString());
    }

    @Override
    public <X> X resolve(String key, Class<X> clazz) {

        String value = this.getStringValue(key);
        if (clazz == String.class) {
            return (X) value;
        }

        if (clazz == Short.class || clazz == Short.TYPE) {
            return (X) Short.valueOf(value);
        }

        if (clazz == Integer.class || clazz == Integer.TYPE) {
            return (X) Integer.valueOf(value);
        }

        if (clazz == Long.class || clazz == Long.TYPE) {
            return (X) Long.valueOf(value);
        }

        if (clazz == Double.class || clazz == Double.TYPE) {
            return (X) Double.valueOf(value);
        }

        if (clazz == Float.class || clazz == Float.TYPE) {
            return (X) Float.valueOf(value);
        }

        if (clazz == Boolean.class || clazz == Boolean.TYPE) {
            return (X) Boolean.valueOf(value);
        }

        if (clazz == Byte.class || clazz == Byte.TYPE) {
            return (X) Byte.valueOf(value);
        }

        throw new RuntimeException("can't resolve string to class");
    }

    @Override
    public boolean exist(String var1) {
        SharedPreferences sp = this.cxt.getSharedPreferences(this.zoneName, 0);
        return sp.contains(var1);
    }

    @Override
    public void remove(String var1) {
        SharedPreferences sp = this.cxt.getSharedPreferences(this.zoneName, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(var1);
        editor.commit();
    }

    @Override
    public void removeAll() {
        SharedPreferences sp = this.cxt.getSharedPreferences(this.zoneName, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public String getProviderIden() {
        return "SharedPreferencdStoreage";
    }

    private void setStringValue(String var1, String var2) {
        SharedPreferences sp = this.cxt.getSharedPreferences(this.zoneName, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(var1, var2);
        editor.commit();
    }

    private String getStringValue(String var) {
        SharedPreferences sp = this.cxt.getSharedPreferences(this.zoneName, 0);
        return sp.getString(var, "");
    }
}
