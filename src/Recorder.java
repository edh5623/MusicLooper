import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Thread used to record audio and save it into a wav file
 * @author Ethan Howes
 */
public class Recorder extends Thread{

    /** File the audio is being saved to */
    private File wav;
    /** Line used to record the audio */
    private TargetDataLine line;

    /**
     * Recorder constructor which creates the file
     * that the audio recorded is being saved to
     * @param trackNum the track number that is recording
     */
    public Recorder(String trackNum){
        wav = new File("src/Tracks/track"+trackNum+".wav");
    }

    /**
     * Sets up and begins recording, recording line will be open
     * until finish is called
     */
    @Override
    public void run(){
        try{
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
                    16, 2, 4, 44100, false);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Error, line not supported");
                System.exit(-1);
            }

            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Beginning recording...");

            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, wav);
        }
        catch (LineUnavailableException lue){
            System.out.println("Line unavailable");
            lue.printStackTrace();
        }
        catch (IOException ioe){
            System.out.println("IO Exception");
            ioe.printStackTrace();
        }

    }

    /**
     * Closes the recording line and stops the recording
     */
    public void finish(){
        line.stop();
        line.flush();
        line.close();
        if(line.isActive()){
            System.out.println("Still active");
        }
        else if(line.isOpen()){
            System.out.println("Still open");
        }
        else {
            System.out.println("Done Recording");
        }
    }
}
