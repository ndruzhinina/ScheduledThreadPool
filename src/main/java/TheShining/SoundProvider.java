package TheShining;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.io.File;

public class SoundProvider {

    private final Clip clip0, clip1, clip2, clipReturn, clipReturnBell, clipSpace, clipHorror;
    private Clip previousClip;

    private SoundProvider() {

        clip0 = getClip("tw1a.wav");
        clip1 = getClip("tw2a.wav");
        clip2 = getClip("tw3a.wav");
        clipReturn = getClip("tw-return1.wav");
        clipReturnBell = getClip("tw-return-bell.wav");
        clipSpace = getClip("tw-space1.wav");
        clipHorror = getClip("mixkit-terror-transition-2484.wav");
    }

    private static SoundProvider instance;

    public static SoundProvider getInstance() {
        if (instance == null) instance = new SoundProvider();
        return instance;
    }

    public void playSound(String type) {
        Clip clipToPlay;
        switch (type) {
            case "symbol1":
                clipToPlay = clip0;
                break;
            case "symbol2":
                clipToPlay = clip1;
                break;
            case "symbol3":
                clipToPlay = clip2;
                break;
            case "return":
                clipToPlay = clipReturn;
                break;
            case "returnbell":
                clipToPlay = clipReturnBell;
                break;
            case "space":
                clipToPlay = clipSpace;
                break;
            case "horror":
                clipToPlay = clipHorror;
                break;
            default:
                clipToPlay = clip0;
        }

        if (clipToPlay != null) {
            previousClip = clipToPlay;
            clipToPlay.setFramePosition(0);
            clipToPlay.start();
        }
    }

    private static Clip getClip(String filePath) {
        try {
            File file = new File(filePath);
            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)) {
                DataLine.Info info = new DataLine.Info(Clip.class, audioStream.getFormat());
                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(audioStream);
                return clip;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void finalize() {
        closeAllClips();
    }

    private void closeAllClips() {
        if (clip0 != null) clip0.close();
        if (clip1 != null) clip1.close();
        if (clip2 != null) clip2.close();
        if (clipReturn != null) clipReturn.close();
        if (clipReturnBell != null) clipReturnBell.close();
        if (clipSpace != null) clipSpace.close();
        if (clipHorror != null) clipHorror.close();
    }
}
