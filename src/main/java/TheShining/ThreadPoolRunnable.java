package TheShining;

import java.util.concurrent.BlockingQueue;

public class ThreadPoolRunnable implements Runnable {

    private Thread thread = null;
    private BlockingQueue<Runnable> taskQueue = null;
    private Boolean isOff = false;
    private boolean idle;

    public ThreadPoolRunnable(BlockingQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run()  {
        thread = Thread.currentThread();

        idle = true;
        while(!isOff) {
            try {
                synchronized (this) {
                    idle = false;
                }

                DelayedTask task = (DelayedTask)taskQueue.poll();
                if(task != null)
                    task.getRunnable().run();

                synchronized (this) {
                    idle = true;
                }

                Thread.sleep(1);
            } catch (Exception ex) {
            }
        }
    }

    public synchronized boolean isIdle() {
        return idle;
    }

    public void off() {
        isOff = true;
        if(this.thread != null)
            thread.interrupt();
    }

    public Boolean isOff() {
        return isOff;
    }
}
