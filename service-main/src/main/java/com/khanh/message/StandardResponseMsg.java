package com.khanh.message;

public class StandardResponseMsg extends StandardMessage {
    private int resultCode;
    private String description;
    private String partnerCode;

    public StandardResponseMsg() {

    }
    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

}
