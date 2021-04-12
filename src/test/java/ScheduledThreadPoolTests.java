import TheShining.ScheduledThreadPool;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduledThreadPoolTests {

    @Test
    public void tasksExecutedWithDelay() {
        ScheduledThreadPool pool = new ScheduledThreadPool(2);

        AtomicBoolean b = new AtomicBoolean(false);

        LocalDateTime start = LocalDateTime.now();
        AtomicReference<LocalDateTime> end = new AtomicReference<>();
        pool.submit(() -> {
            end.set(LocalDateTime.now());
            b.set(true);
        }, 50);

        while(b.get() == false) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}
        }

        assertTrue(ChronoUnit.MILLIS.between(start, end.get()) > 50);
    }
}
