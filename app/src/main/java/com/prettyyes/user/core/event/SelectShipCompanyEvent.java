package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/6/14.
 */

public class SelectShipCompanyEvent {
    public SelectShipCompanyEvent(String shipCompany_name, String ship_code) {
        this.shipCompany_name = shipCompany_name;
        this.ship_code=ship_code;
    }

    private String shipCompany_name;
    private String ship_code;

    public String getShip_code() {
        return ship_code;
    }

    public void setShip_code(String ship_code) {
        this.ship_code = ship_code;
    }

    public String getShipCompany_name() {
        return shipCompany_name;
    }

    public void setShipCompany_name(String shipCompany_name) {
        this.shipCompany_name = shipCompany_name;
    }
}
