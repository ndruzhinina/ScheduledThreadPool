package TheShining;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class ScheduledThreadPool implements IScheduledThreadPool {

    private final List<ThreadPoolRunnable> threadPoolRunnables = new ArrayList<>();
    private final BlockingQueue<DelayedTask> taskQueue = new DelayQueue<>();
    private Boolean isOff = false;

    public ScheduledThreadPool(int poolCapacity) {
        for(int i = 0; i < poolCapacity; i++) {
            ThreadPoolRunnable threadPoolRunnable = new ThreadPoolRunnable(taskQueue);
            threadPoolRunnables.add(threadPoolRunnable);
            new Thread(threadPoolRunnable).start();
        }
    }

    public synchronized void submit(Runnable runnable, long delay) {
        if(isOff)
            throw new IllegalStateException("Thread pool is off");
        taskQueue.offer(new DelayedTask(runnable, delay));
    }

    @Override
    public synchronized void off() {
        isOff = true;
        for(ThreadPoolRunnable threadPoolRunnable : threadPoolRunnables) {
            threadPoolRunnable.off();
        }
    }

    @Override
    public synchronized void waitUntilAllTasksFinished() {
        while(this.taskQueue.size() > 0 || threadPoolRunnables.stream().anyMatch(x -> !x.isIdle())) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
