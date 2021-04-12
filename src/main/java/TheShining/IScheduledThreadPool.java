package TheShining;

public interface IScheduledThreadPool {
    void submit(Runnable runnable, long delay);

    void off();

    void waitUntilAllTasksFinished();
}
