package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/7/27.
 */

public class ShipEntity {

    /**
     * ShipperCode : FAST
     * ShipperName : 快捷速递
     */

    private String ShipperCode;
    private String ShipperName;
    /**
     * letter : a
     * express_key : ANE
     * express_value : 安能物流
     */

    private String letter;
    private String express_key;
    private String express_value;

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String ShipperCode) {
        this.ShipperCode = ShipperCode;
    }

    public String getShipperName() {
        return ShipperName;
    }

    public void setShipperName(String ShipperName) {
        this.ShipperName = ShipperName;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getExpress_key() {
        return express_key;
    }

    public void setExpress_key(String express_key) {
        this.express_key = express_key;
    }

    public String getExpress_value() {
        return express_value;
    }

    public void setExpress_value(String express_value) {
        this.express_value = express_value;
    }
}
