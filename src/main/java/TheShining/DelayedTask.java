package TheShining;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed {

    private Runnable runnable;
    private long timeToRun;

    public DelayedTask(Runnable runnable, long delay) {
        this.runnable = runnable;
        this.timeToRun = System.currentTimeMillis() + delay;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long timeDiff = timeToRun - System.currentTimeMillis();
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed obj) {
        if (timeToRun < ((DelayedTask) obj).timeToRun) {
            return -1;
        }
        if (timeToRun > ((DelayedTask) obj).timeToRun) {
            return 1;
        }
        return 0;
    }
}
