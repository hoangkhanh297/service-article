package com.khanh.task;
import com.khanh.message.TraceableRequest;
import io.vertx.core.Handler;


/**
 * Created by dat on 3/22/18.
 */
public abstract class Task<T extends TraceableRequest> {


    public void run(T input, Handler<T> whenDone) {
        exec(input, output -> {
            if (whenDone != null) {
                whenDone.handle(output);
            }
        });

    }

    /**
     * Run task without callback
     *
     * @param input task's input
     */
    public void run(T input) {
        run(input, null);
    }


    protected abstract void exec(T input, Handler<T> whenDone);
}

