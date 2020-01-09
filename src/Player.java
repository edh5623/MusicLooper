import javax.sound.sampled.*;
import java.io.*;

/**
 * Thread used to play audio from a wav file on loop
 * @author Ethan Howes
 */
public class Player extends Thread{

    /** File the audio is being played from */
    private File wav;
    /** Line being used to play audio */
    private SourceDataLine line;
    /** Status used to know if the audio is currently playing or not */
    private boolean playing;

    /**
     * Player constructor which sets the file
     * that the audio is being played from and initializes
     * the playing status to false (not playing)
     * @param trackNum the track number that is playing
     */
    public Player(String trackNum){
        wav = new File("src/Tracks/track"+trackNum+".wav");
        playing = false;
    }

    /**
     * Sets up and begins audio playback on loop, playback line will be open
     * and playback will continue to loop until finish is called
     */
    @Override
    public void run() {
        try{

            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
                    16, 2, 4, 44100, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            line = (SourceDataLine)AudioSystem.getLine(info);

            line.open();
            line.start();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            AudioInputStream in = AudioSystem.getAudioInputStream(wav);

            // Load the audio data into a buffer
            int read;
            byte[] buff = new byte[1024];
            while ((read = in.read(buff)) > 0)
            {
                out.write(buff, 0, read);
            }
            out.flush();
            byte[] b = out.toByteArray();

            // Play the loaded audio data on loop
            playing = true;
            while(playing){
                line.write(b, 0, b.length);
            }
            in.close();
        }
        catch(IOException ioe){
            System.out.println("IO Exception");
            ioe.printStackTrace();
        }
        catch(LineUnavailableException lue){
            System.out.println("Line unavailable");
            lue.printStackTrace();
        }
        catch (UnsupportedAudioFileException uafe){
            System.out.println("Audio file not supported");
            uafe.printStackTrace();
        }
    }

    /**
     * Closes the playing line and sets the playing status
     * to not playing to end the playing loop
     */
    public void finish(){
        playing = false;
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
            System.out.println("Done Playing");
        }
    }
}
