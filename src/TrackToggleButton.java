import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class TrackToggleButton extends ToggleButton {


    public TrackToggleButton(){

        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0;");

        ImageView on = new ImageView(new Image("Graphics/TrackON.png"));
        on.setFitHeight(275);
        on.setFitWidth(133);

        ImageView off = new ImageView(new Image("Graphics/TrackOFF.png"));
        off.setFitHeight(275);
        off.setFitWidth(133);

        this.setGraphic(off);

        this.setOnAction(e ->{
            //TODO: Make graphics for on and off states
            if (this.isSelected()) {
                this.setGraphic(on);
            }
            else{
                this.setGraphic(off);
            }
            //Call controller method for track toggle
        });

        this.setPrefSize(150,300);
    }


}
