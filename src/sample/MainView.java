package sample;

import basic.Block;
import basic.Board;
import basic.Ghost;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class MainView extends VBox {
    Button timeincrementButton;
    Button bustButton;
    HBox[] Hboxes;
    Button[][] buttons;
    Board board;
    Ghost ghost;
    int size;
    int time = 1;
    boolean isbustButtonClicked = false;
    boolean timeButtonClicked = false;
    boolean sense = false;
    boolean bust = false;
    double [] [] boardArray;
    Label testStatus;
     int selectedRowNO;
     int selectedColNO;

    public MainView(int size){
        this.size = size;
        Hboxes = new HBox[size+1];
        buttons = new Button[size][size];

        ///---------Back End Code------------
        board=new Board(size,size);

        //creating blocks
        board.makeBoard();
        for (Block block:board.getBlockList()) {
            //neighbor ber kre er number diyei type define kre dibo.
            block.determineNeighbors(board);
            block.determineBlockType();
        }
        System.out.println("Initial Board");
        board.printBoard();
        //creating ghost.
        ghost = board.createGhost();
        System.out.println("ghost position : "+ghost.getRowNo()+"--"+ghost.getColNo());
        ///starting game now.
        boardArray = board.getGraphArray();
        System.out.println("Game has started ");
        ///---------Back End finish------------

        for (int i=0; i<size; i++){
            Hboxes[i] = new HBox();
            for(int j=0; j<size; j++){
                String currentProbabily = new DecimalFormat("##.###").format(boardArray[i][j]);
                buttons[i][j] = new Button(currentProbabily);
                buttons[i][j].setPrefSize(60,40);
                buttons[i][j].setMnemonicParsing(false);
                final int selectedRowNO = i;
                final int selectedColNO = j;

                final Board passingBoard = board;
                buttons[i][j].setOnAction(event -> blockButtonClicked(event, selectedRowNO,selectedColNO));
                //buttons[i][j].setStyle("-fx-background-color: #F83535; -fx-border-color: black");
                //buttons[i][j].setStyle("-fx-background-color: #1482EA; -fx-border-color: black");
                //buttons[i][j].setLayoutX(0+j*60);
                //buttons[i][j].setLayoutY(40+i*40);
                Hboxes[i].getChildren().add(buttons[i][j]);
            }
            this.getChildren().add(Hboxes[i]);
        }

        Hboxes[size] = new HBox();
        bustButton = new Button("BUST");
        bustButton.setPrefSize(100,40);
        //bustButton.setOnAction(this::bustButtonClicked);
        bustButton.setOnAction(event->bustButtonClicked(event));
        Hboxes[size].getChildren().add(bustButton);

        timeincrementButton = new Button("Time++");
        timeincrementButton.setPrefSize(100,40);
        timeincrementButton.setStyle("-fx-background-color: #F83535; -fx-border-color: black");
        timeincrementButton.setOnAction(this::timeIncClicked);
//        timeincrementButton.setOnAction(event->timeIncClicked(event,time));
        Hboxes[size].getChildren().add(timeincrementButton);
        //this.getChildren().add(Hboxes[size]);

//        Button senseButton = new Button("Sense");
//        senseButton.setPrefSize(100,40);
//        senseButton.setStyle("-fx-background-color: #1482EA; -fx-border-color: black");
//        senseButton.setOnAction(event->senseClicked(event));
//        Hboxes[size].getChildren().add(senseButton);

        testStatus=new Label();
        testStatus.setAlignment(Pos.CENTER);
        testStatus.setText("Current Time:"+time);

        this.getChildren().addAll(Hboxes[size],testStatus);


        Stage stage=new Stage();
        double shape = 40 * (size);
        Scene scene=new Scene(this,60*size, shape+80);
        //stage.setScene(new Scene(this,60*size, shape+40));
        stage.setTitle("Ghost Buster by Shovito Barua Soumma(1605066)");
        stage.setScene(scene);
        stage.show();
    }

    public void timeIncClicked(ActionEvent actionEvent) {
        time++;
        board.re_initialize_block_colors();// to return all colors to white as ghost has moved.
        for (int i=0; i<size; i++){
            Hboxes[i] = new HBox();
            for(int j=0; j<size; j++){
                //String currentProbabily = new DecimalFormat("##.###").format(boardArray[i][j]);
                buttons[i][j].setStyle("-fx-background-color: #f2f2f2;-fx-border-color: black");
            }
        }
        System.out.println("Current Time:"+time);
        ghost.moveGhost();
        for (Block block:board.getBlockList()) {
            block.updateProbabilityForTransition(board);
        }
        for (int i = 0; i <board.getBlockList().size() ; i++) {
            board.getBlockList().get(i).setCurrentProbability(board.updatedTransitionalProb.get(i));
        }
        board.updatedTransitionalProb.clear();
        board.printBoard();
        updateGuiBlockProbability(Hboxes);
        testStatus.setText("Current Time:"+time);
    }

    public void updateGuiBlockProbability(HBox[] Hboxes){
        for (int i=0; i<size; i++){
            Hboxes[i] = new HBox();
            for(int j=0; j<size; j++){
                String currentProbabily = new DecimalFormat("##.###").format(boardArray[i][j]);
                buttons[i][j].setText(currentProbabily);
            }
        }
    }

    public void updateGuiBlockProbabilityForSensing(HBox[] Hboxes,int selectedRowNO,int selectedColNO){
        for (int i=0; i<size; i++){
            Hboxes[i] = new HBox();
            for(int j=0; j<size; j++){
                String currentProbabily = new DecimalFormat("##.###").format(boardArray[i][j]);
                buttons[i][j].setText(currentProbabily);
                if (i==selectedColNO  && j==selectedColNO){
                    if (board.getBlock(selectedRowNO,selectedColNO).colour==0){
                        buttons[selectedRowNO][selectedColNO].setStyle("-fx-background-color: RED; -fx-border-color: black");
                    }else if(board.getBlock(selectedRowNO,selectedColNO).colour==1){
                        buttons[selectedRowNO][selectedColNO].setStyle("-fx-background-color: ORANGE; -fx-border-color: black");
                    }else {
                        buttons[selectedRowNO][selectedColNO].setStyle("-fx-background-color: GREEN; -fx-border-color: black");
                    }
                }
            }
        }
    }



    public final Board getBoard(){
        return this.board;
    }


    public void bustButtonClicked(ActionEvent event) {

        board.bustghost(selectedRowNO,selectedColNO);
        if (board.getGhost().isBusted()){
            testStatus.setText("Current Time:"+time+"\nyou guessed right");
        }else {
            testStatus.setText("Current Time:"+time+"\nghost escaped,keep trying");
        }
    }

//    public void senseClicked(ActionEvent event) {
//
//    }

    public void blockButtonClicked(ActionEvent event,
                                   int selectedRowNO,int selectedColNO) {

    // for sensing part
        this.selectedColNO = selectedColNO;
        this.selectedRowNO = selectedRowNO;
        System.out.println("Selected Block(row-col)"+selectedRowNO+"-"+selectedColNO+
                "\tProbability:"+board.getGraphArray()[selectedRowNO][selectedColNO]);
        board.assign_color_to_sensed_block(selectedRowNO,selectedColNO);
        board.update_probability_for_emission(selectedRowNO,selectedColNO);
        board.updated_e_prob.clear();
        board.printBoard();
//        Block sensedBlock = board.getBlock(selectedRowNO,selectedColNO);
//        if (sensedBlock.colour==0){
//            buttons[selectedRowNO][selectedColNO].setStyle("-fx-background-color: RED; -fx-border-color: black");
//        }else if(sensedBlock.colour==1){
//            buttons[selectedRowNO][selectedColNO].setStyle("-fx-background-color: ORANGE; -fx-border-color: black");
//        }else {
//            buttons[selectedRowNO][selectedColNO].setStyle("-fx-background-color: GREEN; -fx-border-color: black");
//        }
//        #f2f2f2
        updateGuiBlockProbabilityForSensing(Hboxes,selectedRowNO,selectedColNO);

        testStatus.setText("Current Time:"+time);

    }

    public void testFunction(){
        int myrow=0;
        int mycol=0;
        System.out.println("I am in test");
        Block x = this.board.getBlock(myrow,mycol);
        double [] [] a =this.board.getGraphArray();
        a[0][0]=0.5;
        x.setCurrentProbability(0.5);
        System.out.println(a[0][0]);

        this.board.setGraphArray(a);
        this.board.printBoard();
    }
}

