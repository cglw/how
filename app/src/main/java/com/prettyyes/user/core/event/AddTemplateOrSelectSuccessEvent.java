package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/5.
 */

public class AddTemplateOrSelectSuccessEvent {

    public String module_id;
    public String spu_type;

    public AddTemplateOrSelectSuccessEvent(String module_id, String spu_type) {
        this.module_id = module_id;
        this.spu_type = spu_type;
    }
}
