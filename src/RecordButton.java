import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RecordButton extends ToggleButton {

    public RecordButton(){
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
                this.setGraphic(recording);
            }
            else{
                this.setGraphic(record);
            }
            //Call Controller method for recording
        });

//        this.setStyle("-fx-font: 16 arial;");
        this.setPrefSize(150,150);
    }

}
