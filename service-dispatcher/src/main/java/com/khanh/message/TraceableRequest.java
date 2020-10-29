package com.khanh.message;

public interface TraceableRequest {
    String getRequestId();

    void setStopFlag();

    void setStopFlag(boolean isSuccess);

    boolean isBreakWorkFlow();

    boolean isSuccessFlow();

    default String getMsgType() {
        return this.getClass().getSimpleName();
    }

    default String getServiceId() {
        return "n/a";
    }
}
