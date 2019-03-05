package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/7.
 */

public class AppTimeEvent {
    private long server_time;

    private long kol_activity_create_time;
    private long kol_activity_start_time;
    private long kol_activity_end_time;

    public long getKol_activity_create_time() {
        return kol_activity_create_time;
    }

    public AppTimeEvent setKol_activity_create_time(long kol_activity_create_time) {
        this.kol_activity_create_time = kol_activity_create_time;
        return this;
    }

    public long getKol_activity_start_time() {
        return kol_activity_start_time;
    }

    public AppTimeEvent setKol_activity_start_time(long kol_activity_start_time) {
        this.kol_activity_start_time = kol_activity_start_time;
        return this;

    }

    public long getKol_activity_end_time() {
        return kol_activity_end_time;
    }

    public AppTimeEvent setKol_activity_end_time(long kol_activity_end_time) {
        this.kol_activity_end_time = kol_activity_end_time;
        return this;

    }

    public long getServer_time() {
        return server_time;
    }

    public void setServer_time(long server_time) {
        this.server_time = server_time;
    }

    public AppTimeEvent(long server_time) {
        this.server_time = server_time;
    }
}
