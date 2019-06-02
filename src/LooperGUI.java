import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;

//TODO: Add delete track button?

/**
 * @author Ethan Howes
 */
public class LooperGUI extends Application{

    private BorderPane main;

    private HBox tracks;

//    @Override
//    public void init() {
//
//    }

    private Button recordButton(){
        Button record = new Button("Record");
        record.setPrefSize(150,200);
        return record;
    }

    private ToggleButton onOffButton(){
        ToggleButton onOff = new ToggleButton("On");
        onOff.setPrefSize(150, 300);
        return onOff;
    }

    private void makeTracks(){
        tracks = new HBox();
        for(int i = 0; i<6; i++){
            VBox v = new VBox(new TrackToggleButton(Integer.toString(i)), new RecordButton(Integer.toString(i)));
            tracks.getChildren().add(v);
        }
    }

    @Override
    public void start(Stage stage){
        this.main = new BorderPane();

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
