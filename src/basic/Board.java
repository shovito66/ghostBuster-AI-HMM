package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    int totalRowNo;
    int totalColNo;
    int totalBlocks;
    int size;
    Ghost ghost;
    ArrayList<Block> blockList=new ArrayList<>();
    public ArrayList<Double> updatedTransitionalProb=new ArrayList<>();
    private double[][] graphArray;
    public int max_manhattan_distance;
    public ArrayList<Double> updated_e_prob=new ArrayList<>();

    public Board(int totalRowNo, int totalColNo) {
        this.totalRowNo = totalRowNo;
        this.totalColNo = totalColNo;
        this.totalBlocks = totalRowNo*totalColNo;
        this.blockList = new ArrayList<>();
        graphArray = new double[totalRowNo][totalColNo];
        this.max_manhattan_distance = totalRowNo + totalColNo - 2;
        for (double[] row: graphArray)
            Arrays.fill(row, 0);
    }

    public void makeBoard(){
        for( int i = 0 ; i < this.totalRowNo ; i++ ){
            //ei loop e  j er jagay no of clm dilei m*n howar kotha.
            for( int j = 0 ; j < this.totalColNo ; j++ ){
                double prob=100.0/totalBlocks;
                Block b=new Block(i,j,totalRowNo,totalColNo,prob);
                graphArray[i][j] = prob;
                this.blockList.add(b);
            }
        }
    }

    public Ghost createGhost(){
        Random rand = new Random();
        int r=rand.nextInt(totalRowNo);
        int c=rand.nextInt(totalRowNo);
        System.out.println("Ghost is in Row:"+r+" -- Col:"+c);
        Block b = getBlock(r,c);
        this.ghost = new Ghost(r,c,b);
        return this.ghost;
    }

    public void printBoard(){
        for (Block x: blockList) {
            graphArray[x.getRowNo()][x.getColNo()] = x.getCurrentProbability();
        }

        //System.out.println("Length:"+nodeList.size());
        for (int i = 0; i <totalRowNo ; i++) {
            for (int j = 0; j <totalColNo ; j++) {
                System.out.printf("%.3f\t",graphArray[i][j]);
            }
            System.out.println();
        }

//        for (int i = 0; i <this.totalBlocks; i++) {
//            System.out.printf("%.3f\t",getBlockList().get(i).getCurrentProbability());
//            if (i%getRowNo()==0) System.out.println();
//        }


    }

    public Block getBlock(int row,int col){
        for (Block blk: this.blockList) {
            if (blk.getRowNo()==row & blk.getColNo()==col)
                return blk;
        }
        return null;
    }

    public void re_initialize_block_colors(){
        for (Block b:this.blockList) {
            b.colour = CONSTANTS.WHITE;
        }
    }

    //------------------------------codes for sensing part-----------------------------------
    public int measure_color_to_show(int row1,int col1,int row2,int col2){
        int dist=0;
        dist=Math.abs(row1-row2)+Math.abs(col1-col2);
        Double d1=Double.valueOf(dist);
        Double d2= Double.valueOf(max_manhattan_distance);
        Double ratio=d1/d2;
        if (ratio<CONSTANTS.RATIO_FOR_RED)return CONSTANTS.RED;
        else if (ratio<CONSTANTS.RATIO_FOR_ORANGE)return CONSTANTS.ORANGE;
        else return CONSTANTS.GREEN;
    }

    public void assign_color_to_sensed_block(int sensed_row,int sensed_col){
        Block sensed_block=getBlock(sensed_row,sensed_col);
        sensed_block.colour=measure_color_to_show(sensed_row,sensed_col,ghost.current_block.getRowNo(),ghost.current_block.getColNo());
        String Color;
        if (sensed_block.colour==CONSTANTS.RED) Color="RED";
        else if(sensed_block.colour==CONSTANTS.ORANGE) Color="ORANGE";
        else Color="GREEN";
        System.out.println("Sensor Colour for Block row:"+sensed_row+" --col"
                +sensed_col+"\t--> COLOR:"+Color);
    }

    public void update_probability_for_emission(int sensed_row,int sensed_col){
        Block sensed_block = getBlock(sensed_row,sensed_col);
        double normalization_sum=0;
        for (int i = 0; i <this.totalBlocks ; i++) {
            double new_prob=0;

            Block cur_block = getBlockList().get(i);
            int right_color_at_sensed_block = measure_color_to_show(cur_block.getRowNo(),cur_block.getColNo(),sensed_row,sensed_col);
            if (right_color_at_sensed_block==sensed_block.colour){
                new_prob=cur_block.getCurrentProbability()*(1-CONSTANTS.ERROR);
            }
            else{
                new_prob=cur_block.getCurrentProbability()*CONSTANTS.ERROR;
            }
            updated_e_prob.add(new_prob);
            normalization_sum+=new_prob;
        }
        for (int i = 0; i <this.totalBlocks ; i++) {
            getBlockList().get(i).currentProbability=(updated_e_prob.get(i)/normalization_sum)*100;
        }
    }

    //------------------------------codes for busting part-----------------------------------

    public void bustghost(int bust_row,int bust_col){
        if (ghost.current_block.getRowNo()==bust_row && ghost.current_block.getColNo()==bust_col){
            ghost.isBusted=true;
            System.out.println("you guessed right");
        }
        else{
            System.out.println("ghost escaped,keep trying");
        }
    }

    public int getRowNo() {
        return totalRowNo;
    }

    public void setRowNo(int totalRowNo) {
        this.totalRowNo = totalRowNo;
    }

    public int getColNo() {
        return totalColNo;
    }

    public void setColNo(int totalColNo) {
        this.totalColNo = totalColNo;
    }

    public int getTotalBlocks() {
        return totalBlocks;
    }

    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(ArrayList<Block> blockList) {
        this.blockList = blockList;
    }

    public double[][] getGraphArray() {
        return graphArray;
    }

    public void setGraphArray(double[][] graphArray) {
        this.graphArray = graphArray;
    }

    public int getTotalRowNo() {
        return totalRowNo;
    }

    public void setTotalRowNo(int totalRowNo) {
        this.totalRowNo = totalRowNo;
    }

    public int getTotalColNo() {
        return totalColNo;
    }

    public void setTotalColNo(int totalColNo) {
        this.totalColNo = totalColNo;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    public ArrayList<Double> getUpdatedTransitionalProb() {
        return updatedTransitionalProb;
    }

    public void setUpdatedTransitionalProb(ArrayList<Double> updatedTransitionalProb) {
        this.updatedTransitionalProb = updatedTransitionalProb;
    }

    public int getMax_manhattan_distance() {
        return max_manhattan_distance;
    }

    public void setMax_manhattan_distance(int max_manhattan_distance) {
        this.max_manhattan_distance = max_manhattan_distance;
    }

    public ArrayList<Double> getUpdated_e_prob() {
        return updated_e_prob;
    }

    public void setUpdated_e_prob(ArrayList<Double> updated_e_prob) {
        this.updated_e_prob = updated_e_prob;
    }

}
