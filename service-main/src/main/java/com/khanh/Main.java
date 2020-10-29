package com.khanh;

import com.khanh.message.StandardRequestMsg;
import com.khanh.work_flow.TestWorkFlow;

public class Main {
    public static void main(String[] args) {
        new TestWorkFlow().run(new StandardRequestMsg());
    }
}
