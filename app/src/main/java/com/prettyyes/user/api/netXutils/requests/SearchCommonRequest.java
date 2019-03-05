package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.SearchCommonRes;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchCommonRequest extends BaseRequest<List<SearchCommonRes>> {

    @Override
    public String setExtraUrl() {
        return "/app/search/search";
    }

    private String group_limit;
    private String key_word;
    private int page;
    private int page_size;
    private String search_type;


    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("key_word",key_word);
        arrayMap.put("page",page);
        arrayMap.put("page_size",page_size);
        arrayMap.put("search_type",search_type);
        arrayMap.put("group_limit",group_limit);
        return super.setParams();
    }

    public SearchCommonRequest setSearch_type(String search_type) {
        this.search_type = search_type;
        return this;
    }

    public SearchCommonRequest setPage(int page) {
        this.page = page;
        return this;
    }

    public SearchCommonRequest setPage_size(int page_size) {
        this.page_size = page_size;
        return this;

    }

    public SearchCommonRequest setKey_word(String key_word) {
        this.key_word = key_word;
        return this;

    }

    public SearchCommonRequest setGroup_limit(String group_limit) {
        this.group_limit = group_limit;
        return this;

    }

}
