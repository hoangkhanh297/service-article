package com.khanh.work_flow;

import com.khanh.common_task.BaseHttpCallingTask;
import com.khanh.message.HttpSubmitInfo;
import com.khanh.message.StandardRequestMsg;
import io.vertx.core.Handler;

public class TestTask extends BaseHttpCallingTask<StandardRequestMsg> {
    @Override
    protected void exec(StandardRequestMsg input, Handler<StandardRequestMsg> whenDone) {
        HttpSubmitInfo httpSubmitInfo = new HttpSubmitInfo();
        httpSubmitInfo.setUrl("http://localhost:8081/service");
        httpSubmitInfo.setMethod("GET");
        submitToPartner(input, httpSubmitInfo, done->{

        });

    }
}
