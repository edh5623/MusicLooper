import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

/**
 * Creates the application window and accepts input from
 * the user via buttons.
 * @author Ethan Howes
 */
public class LooperGUI extends Application{

    /** The main pane containing all of the buttons */
    private BorderPane main;
    /** The HBox containing all of the recording/playback tracks */
    private HBox tracks;
    /** List of all recording buttons, used to disable others when one is in use*/
    private ArrayList<RecordButton> recordList;

    /**
     * Initializes the main BorderPane and list of RecordButton's
     */
    @Override
    public void init() {
        main = new BorderPane();
        recordList = new ArrayList<>();
    }


    /**
     * Deletes all tracks recorded in the tracks folder
     */
    private void deleteTracks(){
        File trackDir = new File("src/Tracks");
        File[] tracks = trackDir.listFiles();
        for(File f: tracks){
            if(f.delete()){
                System.out.println("Deleting "+f.getName());
            }
            else{
                System.out.println("Failed to delete " + f.getName());
            }
        }
    }

    /**
     * Creates and returns the delete button which
     * calls deleteTracks when clicked
     * @return a delete tracks button
     */
    private Button deleteButton(){
        Button delete = new Button();
        ImageView del = new ImageView(new Image("Graphics/Delete.png"));
        delete.setGraphic(del);
        delete.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0;");
        delete.setPrefSize(150,220);
        delete.setOnAction(e -> deleteTracks());
        return delete;
    }

    /**
     * Creates and returns the logo button which is purely aesthetic
     * @return the logo button
     */
    private Button logo(){
        Button logo = new Button();
        ImageView log = new ImageView(new Image("Graphics/Logo.png"));
        logo.setGraphic(log);
        logo.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0;");
        logo.setPrefSize(150,220);
        return logo;
    }

    /**
     * Creates all of the record and play buttons in vbox's and
     * puts them in the tracks hbox along with the delete and
     * logo buttons at the end
     */
    private void makeTracks(){
        tracks = new HBox();
        for(int i = 0; i<6; i++){
            RecordButton rb = new RecordButton(Integer.toString(i));
            TrackToggleButton ttb = new TrackToggleButton(Integer.toString(i));
            VBox v = new VBox(ttb, rb);
            v.setPrefHeight(460);
            tracks.getChildren().add(v);
            recordList.add(rb);
        }
        for(RecordButton button: recordList){
            button.setButtonList(recordList);
        }
        ImageView div = new ImageView(new Image("Graphics/Divider.png"));
        div.setFitHeight(455);
        tracks.getChildren().add(div);

        VBox vb = new VBox(deleteButton(), logo());
        vb.setPrefHeight(420);
        tracks.getChildren().add(vb);
    }

    /**
     * Initializes the stage object with the main BorderPane
     * and sets the tracks in the center of it
     * @param stage
     */
    @Override
    public void start(Stage stage){
        makeTracks();

        main.setCenter(tracks);

        Scene scene = new Scene(main);
        stage.setScene(scene);
        stage.setTitle("Music Looper");
        stage.show();
    }

    /**
     * Closes the GUI
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    /**
     * Starts the application
     * @param args
     */
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
