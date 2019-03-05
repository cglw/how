package com.prettyyes.user.app.adapter.searchholder;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.model.search.SearchBaseEntity;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchTopicModel  extends SearchBaseEntity  implements BaseType{
    /**
     * id : 2
     * topic_name : 测试专题2
     * topic_content :
     */

    private int id;
    private String topic_name;
    private String topic_content;

    @Override
    public void setType(String type) {
        super.setType("topic");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }
    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}