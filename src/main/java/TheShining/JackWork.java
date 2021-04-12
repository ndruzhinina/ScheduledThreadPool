package TheShining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JackWork
{
    public static void main(String[] args) throws InterruptedException, IOException {

        List<String> jackIdeas = new ArrayList<>();
        jackIdeas.add("All work and no play makes Jack a dull boy.");
        jackIdeas.add("     All work and no play" + System.lineSeparator() + "     makes Jack a dull boy.");
        jackIdeas.add("All work " + System.lineSeparator() + "   and no play" + System.lineSeparator() + "      makes Jack " + System.lineSeparator() +"         a dull boy.");
        jackIdeas.add("All work and no play makes " + System.lineSeparator() + "         Jack" + System.lineSeparator() + "a dull boy.");

        ScheduledThreadPool pool = new ScheduledThreadPool(3);
        Random random = new Random();

        pool.submit(new JackTyping(jackIdeas.get(random.nextInt(jackIdeas.size()))),
                random.nextInt(1000));

        for(int i = 1; i <= 2; i++) {
            int ideaNum = random.nextInt(jackIdeas.size());
            int delayMs = random.nextInt(60000) + 10000;
            JackTyping task = new JackTyping(jackIdeas.get(ideaNum));
            pool.submit(task, delayMs);
        }

        do {
            int ideaNum = random.nextInt(jackIdeas.size());
            int delayMs = random.nextInt(60000) + 10000;
            JackTyping task = new JackTyping(jackIdeas.get(ideaNum));
            pool.submit(task, delayMs);
            Thread.sleep(30000);
        } while(System.in.available() == 0);

        SoundProvider.getInstance().playSound("horror");
        pool.waitUntilAllTasksFinished();
        Thread.sleep(500);
        pool.off();
    }
}
