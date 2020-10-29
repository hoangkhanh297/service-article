package com.khanh.work_flow;

import com.khanh.message.StandardRequestMsg;
import com.khanh.task.Flow;

public class TestWorkFlow extends Flow<StandardRequestMsg> {
    public TestWorkFlow() {
        addTask(new TestTask());
    }
}
