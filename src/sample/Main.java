package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane borderPane=new BorderPane();
        borderPane.setMinHeight(400);
        borderPane.setMinWidth(700);

        VBox vbox=new VBox();
        vbox.setMinWidth(USE_COMPUTED_SIZE);
        vbox.setMinHeight(100);
        vbox.setStyle("-fx-background-color:whitesmoke; -fx-border-color:red; -fx-border-width:0 0 1 0");
        Label gameName=new Label("GHOST BUSTER by Shovito Barua Soumma(1605066)");
        gameName.setAlignment(Pos.CENTER);
        vbox.getChildren().add(gameName);

        VBox vbox2=new VBox();
        vbox2.setMinHeight(USE_COMPUTED_SIZE);
        vbox2.setMinWidth(USE_COMPUTED_SIZE);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(20);

        TextField rowField=new TextField();
        rowField.setPromptText("Enter Row");
        rowField.setMaxWidth(200);


        TextField columnField=new TextField();
        columnField.setPromptText("Enter Column");
        columnField.setMaxWidth(200);

        Button button=new Button("Submit");
        Label status=new Label();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String rowString = rowField.getText();
                String columnString = columnField.getText();
                int totalRow = Integer.parseInt(rowString);
                int totalCol = Integer.parseInt(columnString);
//                if(rowString.equals("java") && columnString.equals("12345")){
//                    status.setText("success");
//                }
//                else{
//                    status.setText("wrong");
//                }
//                MainView_XXX mainViewXXX = new MainView_XXX(totalRow,totalCol);
                MainView mainView = new MainView(totalRow);
                primaryStage.close();
            }
        });



        vbox2.getChildren().addAll(rowField,columnField,button,status);



        borderPane.setTop(vbox);
        borderPane.setCenter(vbox2);

        Scene scene=new Scene(borderPane);
        primaryStage.setTitle("Ghost Buster");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * int totalRow,totalCol;
 *     int size =15;
 *     //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
 *     MainView mainViewOld = new MainView(size);
 *         primaryStage.setTitle("Ghost Buster");
 *                 double shape = 40 * (size);
 *                 primaryStage.setScene(new Scene(mainViewOld,60*size, shape+40));
 *                 primaryStage.show();
 * */
