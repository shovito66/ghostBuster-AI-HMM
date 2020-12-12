package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView_XXX extends VBox {
    Button timeIncrementButton;
    Button viewButton;
    HBox[] Hboxes;
    Button[][] buttons;
    int size;
    int totatlRow;
    int totatlCol;
    boolean isviewClicked = false;

    public MainView_XXX(int totatlRow, int totatlCol){
        this.totatlRow = totatlRow;
        this.totatlRow = totatlCol;

//        this.setMinHeight(40*totatlRow+40);
//        this.minWidth(60*this.totatlCol);
        Hboxes = new HBox[totatlRow+1];
        buttons = new Button[totatlRow][totatlCol];

        for (int i=0; i<this.totatlRow; i++){
            Hboxes[i] = new HBox();
            for(int j=0; j<this.totatlCol; j++){
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(60,40);
                buttons[i][j].setMnemonicParsing(false);
                //buttons[i][j].setStyle("-fx-background-color: #F83535; -fx-border-color: black");
                //buttons[i][j].setStyle("-fx-background-color: #1482EA; -fx-border-color: black");
                //buttons[i][j].setLayoutX(0+j*60);
                //buttons[i][j].setLayoutY(40+i*40);
                Hboxes[i].getChildren().add(buttons[i][j]);
            }
            this.getChildren().add(Hboxes[i]);
        }
        Hboxes[this.totatlRow] = new HBox();
        viewButton = new Button("View");
        viewButton.setPrefSize(100,40);
        viewButton.setOnAction(this::viewClicked);
        Hboxes[totatlRow].getChildren().add(viewButton);
        timeIncrementButton = new Button("Time++");
        timeIncrementButton.setPrefSize(100,40);
        timeIncrementButton.setOnAction(this::timeIncClicked);

        Hboxes[totatlRow].getChildren().add(timeIncrementButton);
        this.getChildren().add(Hboxes[totatlRow]);


        double width = 60*this.totatlCol;
        double height = 40*this.totatlRow;
        Stage stage=new Stage();
        Scene scene=new Scene(this,width,height+40);
        stage.setScene(scene);
        stage.show();

    }

    private void timeIncClicked(ActionEvent actionEvent) {
        System.out.println("Hello");
        if(isviewClicked){
            for (int i=0; i<totatlRow; i++){
                for(int j=0; j<totatlCol; j++) {
                    buttons[i][j].setText("");
                }
            }
            isviewClicked = false;
            //viewButton.setText("View");
        }
    }

    public void viewClicked(ActionEvent event) {
        if(isviewClicked==false){
            isviewClicked = true;
            viewButton.setText("Hide");
        }else{
            isviewClicked = false;
            viewButton.setText("View");
        }
    }
}
