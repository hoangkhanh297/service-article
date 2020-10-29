package com.khanh.message;

public abstract class BaseTraceableRequest implements TraceableRequest {

    private StopFlag stopFlag = new StopFlag();

    @Override
    public void setStopFlag() {
        stopFlag.setStop(true);
    }

    @Override
    public void setStopFlag(boolean isSuccess) {
        stopFlag.setStop(true);
        stopFlag.setSuccess(isSuccess);
    }

    @Override
    public boolean isBreakWorkFlow(){
        return stopFlag.isStop();
    }

    @Override
    public boolean isSuccessFlow() {
        return stopFlag.isSuccess();
    }

}
