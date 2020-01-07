import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class RecordButton extends ToggleButton {

    private Recorder recorder;

    private ArrayList<RecordButton> button_list;

    private int trackNum;

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
            //TODO: When a memo has been recorded, have a little black dot in the corner
            // to show that there is a recording on that track
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
            //Call model method for recording
        });

        this.setPrefSize(150,150);
//        this.setPrefSize((Screen.getPrimary().getBounds().getWidth()/6),(Screen.getPrimary().getBounds().getHeight()/3));
    }

    public void setButtonList(ArrayList<RecordButton> button_list) {
        this.button_list = button_list;
    }

    public int getTrackNum(){
        return this.trackNum;
    }

    public void disableOthers(){
        for(RecordButton button: button_list){
            if(button.getTrackNum() != this.trackNum){
                button.setDisable(true);
            }
        }
    }

    public void enableOthers(){
        for(RecordButton button: button_list){
            button.setDisable(false);
        }
    }
}
