import javafx.scene.control.ToggleButton;

public class RecordButton extends ToggleButton {

    public RecordButton(){
        this.setText("RECORD");

        this.setOnAction(e ->{
            if (this.isSelected()) {
                this.setText("RECORDING...");
            }
            else{
                this.setText("RECORD");
            }
            //Call Controller method for recording
        });

        this.setStyle("-fx-font: 16 arial;");
        this.setPrefSize(150,150);
    }

}
