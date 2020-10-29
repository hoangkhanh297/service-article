package com.khanh.task;

import com.khanh.message.TraceableRequest;
import io.vertx.core.Handler;

import java.util.ArrayList;
import java.util.List;

public class Flow<T extends TraceableRequest> extends Task<T> {
    private final List<Task<? super T>> mWorkerList = new ArrayList<>();
    private final List<Integer> alwaysRunIndex = new ArrayList<>();

    @Override
    public void exec(T input, Handler<T> whenDone) {
        runOneTask(mWorkerList, input, whenDone, 0);

    }

    private void runOneTask(List<Task<? super T>> taskList, T input, Handler<T> whenAllDone, final int step) {
        if (step == taskList.size()) {
            //this is the end => do return here
            whenAllDone.handle(input);
        } else if (!input.isBreakWorkFlow() || alwaysRunIndex.contains(step)) {
            taskList.get(step).run(input, entries -> runOneTask(taskList, (T) entries, whenAllDone, step + 1));
        } else {
            //skip and move to next task
            runOneTask(taskList, input, whenAllDone, step + 1);
        }

    }

    public Flow<T> addTask(Task<? super T> task) {
        mWorkerList.add(task);
        return this;
    }

    public Flow<T> addAlwaysRunTask(Task<? super T> task) {
        alwaysRunIndex.add(mWorkerList.size());
        return addTask(task);
    }
}
