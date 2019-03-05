package com.prettyyes.user.core.event;

import com.prettyyes.user.model.v8.AddressEntity;

/**
 * Created by chengang on 2017/7/28.
 */

public class AddressSelectEvent {
    public AddressEntity addressEntity;

    public AddressSelectEvent(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }
}
