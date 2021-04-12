import TheShining.DelayedTask;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class DelayedTaskTests {

    @Test
    public void getRunnableReturnsSuppliedRunnable() {
        Runnable runnable = () -> {
        };
        DelayedTask delayedTask = new DelayedTask(runnable, 0);
        assertEquals(delayedTask.getRunnable(), runnable);
    }

    @Test
    public void getDelayReturnsCurrentTimeToGo() {
        Runnable runnable = () -> {
        };
        DelayedTask delayedTask = new DelayedTask(runnable, 50);
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
        }

        long diff = Math.abs(delayedTask.getDelay(TimeUnit.MILLISECONDS) - 25L);
        assertTrue(diff <= 3);
    }

    @Test
    public void comparisonTest() {
        Runnable runnable = () -> {
        };
        DelayedTask delayedTask1 = new DelayedTask(runnable, 50);
        DelayedTask delayedTask2 = new DelayedTask(runnable, 100);
        assertTrue(delayedTask1.compareTo(delayedTask2) == -1);
    }
}
