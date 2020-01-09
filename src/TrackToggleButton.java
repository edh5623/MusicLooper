import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Toggleable button to play audio on loop and stop playing
 * @author Ethan Howes
 */
public class TrackToggleButton extends ToggleButton {

    /** The player object used to play auio */
    private Player player;
    /** Unique track number that this button is in */
    private String trackNum;

    /**
     * Constructor for the track toggle button
     * Creates the button and images that go with it
     * and re-sizes it appropriately
     * Defines the button's action to play the audio file
     * associated with its track number on loop when clicked
     * and to stop playing when clicked again
     * @param trackNum The track number that the button is in
     */
    public TrackToggleButton(String trackNum){
        this.trackNum = trackNum;
        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0;");

        ImageView on = new ImageView(new Image("Graphics/TrackON.png"));
        on.setFitHeight(275);
        on.setFitWidth(133);

        ImageView off = new ImageView(new Image("Graphics/TrackOFF.png"));
        off.setFitHeight(275);
        off.setFitWidth(133);

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
        });
        this.setPrefSize(150,300);
    }

    /**
     * Checks whether the track this TrackToggleButton is responsible
     * for playing exists or not
     * @return true if the track exists, false if not
     */
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
