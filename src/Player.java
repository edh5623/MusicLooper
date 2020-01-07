import javax.sound.sampled.*;
import java.io.*;

public class Player extends Thread{

    private File wav;

    private SourceDataLine line;

    private String name;

    private boolean playing;

    public Player(String trackNum){
        name = "src/Tracks/track"+trackNum+".wav";
        wav = new File(name);
        playing = false;
    }

    @Override
    public void run() {
        try{

            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
                    16, 2, 4, 44100, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            line = (SourceDataLine)AudioSystem.getLine(info);

            line.open();
            line.start();
            playing = true;

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            AudioInputStream in = AudioSystem.getAudioInputStream(wav);

            int read;
            byte[] buff = new byte[1024];
            while ((read = in.read(buff)) > 0)
            {
                out.write(buff, 0, read);
            }
            out.flush();
            byte[] b = out.toByteArray();

            while(playing){
                line.write(b, 0, b.length);
            }
            in.close();
        }
        catch(IOException ioe){
            System.out.println("IO Exception!");
            ioe.printStackTrace();
        }
        catch(LineUnavailableException lue){
            System.out.println("Line unavailable!");
            lue.printStackTrace();
        }
        catch (UnsupportedAudioFileException uafe){
            System.out.println("Audio file not supported");
            uafe.printStackTrace();
        }
    }

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
