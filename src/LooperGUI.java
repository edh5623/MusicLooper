import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Ethan Howes
 */
public class LooperGUI extends Application{

    private BorderPane main;

    private HBox tracks;

    private ArrayList<RecordButton> recordList;

    @Override
    public void init() {
        main = new BorderPane();
        recordList = new ArrayList<>();
    }

    private void makeTracks(){
        tracks = new HBox();
        for(int i = 0; i<6; i++){
            RecordButton rb = new RecordButton(Integer.toString(i));
            TrackToggleButton ttb = new TrackToggleButton(Integer.toString(i));
            VBox v = new VBox(ttb, rb);
            tracks.getChildren().add(v);
            recordList.add(rb);
        }
        for(RecordButton button: recordList){
            button.setButtonList(recordList);
        }
    }

    @Override
    public void start(Stage stage){
        makeTracks();

        main.setCenter(tracks);

        Scene scene = new Scene(main);

        stage.setScene(scene);
        stage.setTitle("Music Looper");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        //TODO: Add option to save recordings in the future, for now,
        // delete all recorded tracks when program is closed
        //Delete all created tracks
        File trackDir = new File("src/Tracks");
        File[] tracks = trackDir.listFiles();
        for(File f: tracks){
            System.out.println("Deleting "+f.getName());
            f.delete();
        }

        super.stop();
    }

    public static void main(String[] args) {
        // Possibly implement command line args for the future
//        if (args.length != 1) {
//            System.out.println("Usage: java MusicLooper Number of Loops (max 7)");
//            System.exit(-1);
//        }
//        else {
//            Application.launch(args);
//        }
        Application.launch(args);
    }
}
