import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class TrackToggleButton extends ToggleButton {

    private Player player;

    private String trackNum;

    public TrackToggleButton(String trackNum){
        this.trackNum = trackNum;
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
                if(trackExists()) {
                    player = new Player(trackNum);
                    player.start();
                }
            }
            else{
                this.setGraphic(off);
                if(trackExists()) {
                    player.finish();
                }
            }
            //Call model method for track toggle
        });

        this.setPrefSize(150,300);
//        this.setPrefSize(Screen.getPrimary().getBounds().getWidth()/6,Screen.getPrimary().getBounds().getHeight());
    }

    private boolean trackExists(){
        File trackDir = new File("src/Tracks");
        File[] tracks = trackDir.listFiles();
        for(File f: tracks){
            if(f.getName().compareTo("track"+trackNum+".wav") == 0){
                return true;
            }
        }
        return false;
    }


}
