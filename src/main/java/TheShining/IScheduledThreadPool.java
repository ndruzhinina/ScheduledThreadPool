package TheShining;

import java.util.List;

public interface IScheduledThreadPool {
    void submit(Runnable runnable, long delay);

    void off();

    void waitUntilAllTasksFinished();
}
