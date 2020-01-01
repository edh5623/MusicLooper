import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class RecordButton extends ToggleButton {

    private Recorder recorder;

    public RecordButton(String trackNum){
        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0;");

        ImageView record = new ImageView(new Image("Graphics/Record.png"));
        record.setFitHeight(130);
        record.setFitWidth(130);
//        record.setFitHeight(130);
//        record.setFitWidth(130);

        ImageView recording = new ImageView(new Image("Graphics/Recording.png"));
        recording.setFitHeight(130);
        recording.setFitWidth(130);
//        recording.setFitHeight(130);
//        recording.setFitWidth(130);

        ImageView recorded = new ImageView(new Image("Graphics/Recorded.png"));
        record.setFitHeight(130);
        record.setFitWidth(130);

        this.setGraphic(record);

        this.setOnAction(e ->{
            //TODO: When a memo has been recorded, have a little black dot in the corner
            // to show that there is a recording on that track
            if (this.isSelected()) {
                this.setGraphic(recording);
                recorder = new Recorder(trackNum);
                recorder.start();
            }
            else{
                recorder.finish();
                this.setGraphic(record);
            }
            //Call model method for recording
        });

        this.setPrefSize(150,150);
//        this.setPrefSize((Screen.getPrimary().getBounds().getWidth()/6),(Screen.getPrimary().getBounds().getHeight()/3));
    }

}
