package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
        ///apatoto n*n er jonno krtesi, pore m*n er jonno krbo.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input row no");
        int r = Integer.parseInt(br.readLine());
        System.out.println("Input column no");
        int c = Integer.parseInt(br.readLine());
        //creating board
        Board board=new Board(r,c);

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
        Ghost ghost = board.createGhost();
        System.out.println("ghost position : "+ghost.getRowNo()+"--"+ghost.getColNo());


        ///starting game now.
        int time=1;
        boolean sense=false;
        boolean bust=false;
        System.out.println("Game has started ");
        while (ghost.isBusted()==false){

            System.out.println(" Enter:\n" +
                    "1 for time+1\n"+
                    "2 for sensing\n"+
                    "3 for busting\n"
            );

            String str=br.readLine();
            //code for simulating time+1
            if (str.equalsIgnoreCase("1")){
                time++;
                board.re_initialize_block_colors();// to return all colors to white as ghost has moved.
                ghost.moveGhost();

                for (Block block:board.getBlockList()) {
                    block.updateProbabilityForTransition(board);
                }
                for (int i = 0; i <board.getBlockList().size() ; i++) {
                    board.getBlockList().get(i).currentProbability=board.updatedTransitionalProb.get(i);
                }
                board.updatedTransitionalProb.clear();
                board.printBoard();
            }
            //code for simulating sensing
            else if (str.equalsIgnoreCase("2")){
                System.out.println("Input row no to sense");
                int r2 = Integer.parseInt(br.readLine());
                System.out.println("Input column no to sense");
                int c2 = Integer.parseInt(br.readLine());

                board.assign_color_to_sensed_block(r2,c2);
                board.update_probability_for_emission(r2,c2);
                board.updated_e_prob.clear();
                board.printBoard();
            }
            //code for simulating bust
            else if (str.equalsIgnoreCase("3")){
                System.out.println("Input row no to sense");
                int r3 = Integer.parseInt(br.readLine());
                System.out.println("Input column no to sense");
                int c3 = Integer.parseInt(br.readLine());
                board.bustghost(r3,c3);
            }
        }
    }
}
