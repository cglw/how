package com.prettyyes.user.core.event;

import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/6/30.
 */

public class UnitSelectEvent {
    private ArrayList<SpuInfoEntity>spuInfoEntities;

    public UnitSelectEvent(ArrayList<SpuInfoEntity> spuInfoEntities) {
        this.spuInfoEntities = spuInfoEntities;
    }

    public ArrayList<SpuInfoEntity> getSpuInfoEntities() {
        return spuInfoEntities;
    }

    public void setSpuInfoEntities(ArrayList<SpuInfoEntity> spuInfoEntities) {
        this.spuInfoEntities = spuInfoEntities;
    }
}
