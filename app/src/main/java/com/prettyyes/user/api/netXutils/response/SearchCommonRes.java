package com.prettyyes.user.api.netXutils.response;

import java.util.List;

/**
 * Created by chengang on 2017/7/17.
 */

public class SearchCommonRes extends BaseRes{
    private String search_type;
    private List<SearchItemModel> list;
    private String key_word;

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public List<SearchItemModel> getList() {
        return list;
    }

    public void setList(List<SearchItemModel> list) {
        this.list = list;
    }
}
