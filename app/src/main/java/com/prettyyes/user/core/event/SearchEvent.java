package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/5.
 */

public class SearchEvent {
    private String search_key_word;

    public String getSearch_key_word() {
        return search_key_word;
    }

    public void setSearch_key_word(String search_key_word) {
        this.search_key_word = search_key_word;
    }

    public SearchEvent(String search_key_word) {
        this.search_key_word = search_key_word;
    }
}
