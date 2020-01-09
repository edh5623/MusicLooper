import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Toggleable button to record save and stop recording audio
 * @author Ethan Howes
 */
public class RecordButton extends ToggleButton {

    /** The recorder object used to record audio */
    private Recorder recorder;
    /** List of all other record buttons used in the program */
    private ArrayList<RecordButton> button_list;
    /** Unique track number that this button is in */
    private int trackNum;

    /**
     * Constructor for the record button
     * Creates the button and images that go with it
     * and re-sizes it appropriately
     * Defines the button's action to record when pressed
     * and stop recording and save when pressed again.
     * @param trackNum The track number that the button is in
     */
    public RecordButton(String trackNum){
        this.button_list = new ArrayList<>();
        this.trackNum = Integer.parseInt(trackNum);
        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0;");

        ImageView record = new ImageView(new Image("Graphics/Record.png"));
        record.setFitHeight(130);
        record.setFitWidth(130);

        ImageView recording = new ImageView(new Image("Graphics/Recording.png"));
        recording.setFitHeight(130);
        recording.setFitWidth(130);

        this.setGraphic(record);

        this.setOnAction(e ->{
            if (this.isSelected()) {
                this.disableOthers();
                this.setGraphic(recording);
                recorder = new Recorder(trackNum);
                recorder.start();
            }
            else{
                recorder.finish();
                this.setGraphic(record);
                this.enableOthers();
            }
        });

        this.setPrefSize(150,150);
    }

    /**
     * Sets the list of other buttons in the program
     * @param button_list list of other RecordButtons
     */
    public void setButtonList(ArrayList<RecordButton> button_list) {
        this.button_list = button_list;
    }

    /**
     * Returns this button's track number
     * @return the track number (trackNum)
     */
    public int getTrackNum(){
        return this.trackNum;
    }

    /**
     * Disables all other buttons in the button list
     * besides the one calling this function
     */
    public void disableOthers(){
        for(RecordButton button: button_list){
            if(button.getTrackNum() != this.trackNum){
                button.setDisable(true);
            }
        }
    }

    /**
     * Enables all buttons in the the button list
     */
    public void enableOthers(){
        for(RecordButton button: button_list){
            button.setDisable(false);
        }
    }
}
