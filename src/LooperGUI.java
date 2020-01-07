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

    private Button deleteButton(){
        Button delete = new Button();
        ImageView del = new ImageView(new Image("Graphics/Delete.png"));
        delete.setGraphic(del);
        delete.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0;");
        delete.setPrefSize(150,220);
        delete.setOnAction(e ->{
            deleteTracks();
        });
        return delete;
    }

    private Button logo(){
        Button logo = new Button();
        ImageView log = new ImageView(new Image("Graphics/Logo.png"));
        logo.setGraphic(log);
        logo.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0;");
        logo.setPrefSize(150,220);
        return logo;
    }

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
