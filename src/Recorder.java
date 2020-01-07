import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Recorder extends Thread{

    private File wav;

    private TargetDataLine line;

    public Recorder(String trackNum){
        wav = new File("src/Tracks/track"+trackNum+".wav");
    }

    @Override
    public void run(){
        try{
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
                    16, 2, 4, 44100, false);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Error, line not supported");
                System.exit(0);
            }

            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Beginning recording...");

            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, wav);
        }
        catch (LineUnavailableException lue){
            System.out.println("Line unavailable!");
            lue.printStackTrace();
        }
        catch (IOException ioe){
            System.out.println("IO Exception!");
            ioe.printStackTrace();
        }

    }

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
