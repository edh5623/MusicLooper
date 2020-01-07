import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrackToggleButton extends ToggleButton {

    private Player player;

    public TrackToggleButton(String trackNum){

        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0;");

        ImageView on = new ImageView(new Image("Graphics/TrackON.png"));
        on.setFitHeight(275);
        on.setFitWidth(133);
//        on.setFitHeight(Screen.getPrimary().getBounds().getHeight()/2);
//        on.setFitWidth(Screen.getPrimary().getBounds().getWidth()/10);

        ImageView off = new ImageView(new Image("Graphics/TrackOFF.png"));
        off.setFitHeight(275);
        off.setFitWidth(133);
//        off.setFitHeight(Screen.getPrimary().getBounds().getHeight()/2);
//        off.setFitWidth(Screen.getPrimary().getBounds().getWidth()/10);

        this.setGraphic(off);

        this.setOnAction(e ->{
            if (this.isSelected()) {
                this.setGraphic(on);
                player = new Player(trackNum);
                player.start();
            }
            else{
                player.finish();
                this.setGraphic(off);
            }
            //Call model method for track toggle
        });

        this.setPrefSize(150,300);
//        this.setPrefSize(Screen.getPrimary().getBounds().getWidth()/6,Screen.getPrimary().getBounds().getHeight());
    }


}
