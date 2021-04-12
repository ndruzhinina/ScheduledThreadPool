package TheShining;

import java.util.Random;

public class JackTyping implements Runnable {
    public static final Object obj = new Object();
    private String stringToType;

    public JackTyping(String stringToType) {
        this.stringToType = stringToType;

    }

    @Override
    public void run() {
        synchronized (obj) {
            Random random = new Random();
            try {
                for (int i = 0; i < stringToType.length(); i++) {
                    Character ch = stringToType.charAt(i);
                    System.out.print(ch);
                    playSound(ch);
                        if(ch == '\n')
                            Thread.sleep(1000);
                        else
                            Thread.sleep(random.nextInt(400) + 150);
                }
                System.out.println();
                SoundProvider.getInstance().playSound("returnbell");
                Thread.sleep(1700);
            }
            catch (InterruptedException e) {
            }
        }
    }

    private void playSound(Character ch) {
        if(ch == '\r') return;
        if(ch == '\n') {
            SoundProvider.getInstance().playSound("return");
        } else if(ch == ' ') {
            SoundProvider.getInstance().playSound("space");
        } else if(ch == 'a' || ch == 'o' || ch == 'e' || ch == 'y') {
            SoundProvider.getInstance().playSound("symbol1");
        } else if(ch == 'A' || ch == 'J' || ch == '.' || ch == 'l') {
            SoundProvider.getInstance().playSound("symbol2");
        } else
            SoundProvider.getInstance().playSound("symbol3");
    }
}
