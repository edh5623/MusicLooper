import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class TrackToggleButton extends ToggleButton {


    public TrackToggleButton(){
        this.setText("OFF");

        this.setOnAction(e ->{
            //TODO: Make graphics for on and off states
            if (this.isSelected()) {
                this.setText("ON");
            }
            else{
                this.setText("OFF");
            }
            //Call controller method for track toggle
        });

        this.setStyle("-fx-font: 22 arial;");
        this.setPrefSize(150,300);
    }


}
