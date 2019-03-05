package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.CategoryModel;

import java.util.List;

/**
 * Created by chengang on 2017/6/23.
 */

public class CategoryRequest extends BaseRequest<List<CategoryModel>> {

     @Override
    public String setExtraUrl() {
        return "/app/spuCategory/getList";
    }

}
