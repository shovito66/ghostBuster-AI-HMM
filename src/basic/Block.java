package basic;

import java.util.LinkedList;

public class Block {
    private int dimension;
    private int totalRow;
    private int totalCol;
    private int rowNo;
    private int colNo;
    private BlockType blockType;
    private LinkedList<Block> NeighbourList;
    private LinkedList<MoveType> NeighbourMove;
    private double probabilityLeft_Right;
    private double probabilityTop_Bottom;
    private double probabilityDiagonal;
    private double probabilityNoMove;
    double currentProbability;
    public int colour;

    public Block(int dimension, int rowNo, int colNo,int currentProbability) {
        this.NeighbourList = new LinkedList<>();
        this.NeighbourMove = new LinkedList<>();
        this.currentProbability = currentProbability;

        this.dimension = dimension;
        this.rowNo = rowNo;
        this.colNo = colNo;
        if (colNo==0 || colNo==this.totalCol-1 || rowNo==0 || rowNo==this.totalRow-1){
            if (colNo==0 && rowNo==0 || colNo==0 && rowNo==this.totalRow-1
                    ||colNo==this.totalCol-1 && rowNo==0
                    || colNo==this.totalCol-1 && rowNo==this.totalRow-1) this.blockType = BlockType.CORNER;
            else this.blockType = BlockType.SIDE;
        }else this.blockType = BlockType.MIDDLE;

        if (this.blockType==BlockType.MIDDLE){
            this.probabilityLeft_Right = CONSTANTS.MIDDLE_probabilityLeft_Right;
            this.probabilityTop_Bottom = CONSTANTS.MIDDLE_probabilityTop_Bottom;
            this.probabilityDiagonal = CONSTANTS.MIDDLE_probabilityDiagonal;
            this.probabilityNoMove= CONSTANTS.MIDDLE_probabilityNoMove;

        } else if(this.blockType==BlockType.SIDE){
            this.probabilityLeft_Right = CONSTANTS.SIDE_probabilityLeft_Right; //double .5
            this.probabilityTop_Bottom = CONSTANTS.SIDE_probabilityTop_Bottom; //
            this.probabilityDiagonal = CONSTANTS.SIDE_probabilityDiagonal;
            this.probabilityNoMove= CONSTANTS.SIDE_probabilityNoMove;
        } else {
            this.probabilityLeft_Right = CONSTANTS.CORNER_probabilityLeft_Right; //double .5
            this.probabilityTop_Bottom = CONSTANTS.CORNER_probabilityTop_Bottom; //
            this.probabilityDiagonal = CONSTANTS.CORNER_probabilityDiagonal;
            this.probabilityNoMove= CONSTANTS.CORNER_probabilityNoMove;
        }

    }

    public Block(int rowNo, int colNo,int currentProbability) {
        this.currentProbability = currentProbability;
        this.NeighbourList = new LinkedList<>();
        this.NeighbourMove = new LinkedList<>();
        this.rowNo = rowNo;
        this.colNo = colNo;
        if (colNo==0 || colNo==this.totalCol-1 || rowNo==0 || rowNo==this.totalRow-1){
            if (colNo==0 && rowNo==0 || colNo==0 && rowNo==this.totalRow-1
                    ||colNo==this.totalCol-1 && rowNo==0
                    || colNo==this.totalCol-1 && rowNo==this.totalRow-1) this.blockType = BlockType.CORNER;
            else this.blockType = BlockType.SIDE;
        }else this.blockType = BlockType.MIDDLE;

        if (this.blockType==BlockType.MIDDLE){
            this.probabilityLeft_Right = CONSTANTS.MIDDLE_probabilityLeft_Right;
            this.probabilityTop_Bottom = CONSTANTS.MIDDLE_probabilityTop_Bottom;
            this.probabilityDiagonal = CONSTANTS.MIDDLE_probabilityDiagonal;
            this.probabilityNoMove= CONSTANTS.MIDDLE_probabilityNoMove;

        } else if(this.blockType==BlockType.SIDE){
            this.probabilityLeft_Right = CONSTANTS.SIDE_probabilityLeft_Right; //double .5
            this.probabilityTop_Bottom = CONSTANTS.SIDE_probabilityTop_Bottom; //
            this.probabilityDiagonal = CONSTANTS.SIDE_probabilityDiagonal;
            this.probabilityNoMove= CONSTANTS.SIDE_probabilityNoMove;
        } else {
            this.probabilityLeft_Right = CONSTANTS.CORNER_probabilityLeft_Right; //double .5
            this.probabilityTop_Bottom = CONSTANTS.CORNER_probabilityTop_Bottom; //
            this.probabilityDiagonal = CONSTANTS.CORNER_probabilityDiagonal;
            this.probabilityNoMove= CONSTANTS.CORNER_probabilityNoMove;
        }
    }

    public Block(int rowNo, int colNo, int totalRow, int totalCol,double currentProbability) {
        this.colour = CONSTANTS.WHITE;
        this.currentProbability = currentProbability;
        this.totalRow=totalRow;
        this.totalCol=totalCol;
        this.NeighbourList = new LinkedList<>();
        this.NeighbourMove = new LinkedList<>();
        this.rowNo = rowNo;
        this.colNo = colNo;
        if (colNo==0 || colNo==this.totalCol-1 || rowNo==0 || rowNo==this.totalRow-1){
            if (colNo==0 && rowNo==0 || colNo==0 && rowNo==this.totalRow-1
                    ||colNo==this.totalCol-1 && rowNo==0
                    || colNo==this.totalCol-1 && rowNo==this.totalRow-1) this.blockType = BlockType.CORNER;
            else this.blockType = BlockType.SIDE;
        }else this.blockType = BlockType.MIDDLE;

        if (this.blockType==BlockType.MIDDLE){
            this.probabilityLeft_Right = CONSTANTS.MIDDLE_probabilityLeft_Right;
            this.probabilityTop_Bottom = CONSTANTS.MIDDLE_probabilityTop_Bottom;
            this.probabilityDiagonal = CONSTANTS.MIDDLE_probabilityDiagonal;
            this.probabilityNoMove= CONSTANTS.MIDDLE_probabilityNoMove;

        } else if(this.blockType==BlockType.SIDE){
            this.probabilityLeft_Right = CONSTANTS.SIDE_probabilityLeft_Right; //double .5
            this.probabilityTop_Bottom = CONSTANTS.SIDE_probabilityTop_Bottom; //
            this.probabilityDiagonal = CONSTANTS.SIDE_probabilityDiagonal;
            this.probabilityNoMove= CONSTANTS.SIDE_probabilityNoMove;
        } else {
            this.probabilityLeft_Right = CONSTANTS.CORNER_probabilityLeft_Right; //double .5
            this.probabilityTop_Bottom = CONSTANTS.CORNER_probabilityTop_Bottom; //
            this.probabilityDiagonal = CONSTANTS.CORNER_probabilityDiagonal;
            this.probabilityNoMove= CONSTANTS.CORNER_probabilityNoMove;
        }
    }

    public void determineBlockType(){
        if (NeighbourList.size()==4) this.blockType=BlockType.CORNER;
        else if (NeighbourList.size()==6) this.blockType=BlockType.SIDE;
        else if (NeighbourList.size()==9) this.blockType=BlockType.MIDDLE;
    }

    public void determineNeighbors(Board b){
        NeighbourList.add(this);
        NeighbourMove.add(MoveType.NO_MOVE);
        //--------------------------------------------------------------------
        int rightX,rightY,leftX,leftY,topX,topY,bottomX,bottomY,
                topLeftX,topLeftY,topRightX,topRightY,
                bottomLeftX,bottomLeftY,bottomRightX,bottomRightY;
        rightX = this.rowNo;        rightY = this.colNo+1;
        leftX = this.rowNo;         leftY = this.colNo-1;
        topX = this.rowNo-1;        topY = this.colNo;
        bottomX = this.rowNo+1;     bottomY=this.colNo;
        topLeftX=this.rowNo-1;      topLeftY=this.colNo-1;
        topRightX=this.rowNo-1;     topRightY=this.colNo+1;
        bottomLeftX=this.rowNo+1;   bottomLeftY=this.colNo-1;
        bottomRightX=this.rowNo+1;  bottomRightY=this.colNo+1;
        //--------------------------------------------------------------------
        for (Block x: b.getBlockList()) {
            if (x.getRowNo()==rightX && x.getColNo()==rightY){
                getNeighbourList().add(x);
                getNeighbourMove().add(MoveType.RIGHT);
            }else if(x.getRowNo()==leftX && x.getColNo()==leftY){
                getNeighbourList().add(x);
                getNeighbourMove().add(MoveType.LEFT);
            }else if(x.getRowNo()==topX && x.getColNo()==topY){
                getNeighbourList().add(x);
                getNeighbourMove().add(MoveType.TOP);
            }else if(x.getRowNo()==bottomX && x.getColNo()==bottomY){
                getNeighbourList().add(x);
                getNeighbourMove().add(MoveType.BOTTOM);
            }else if(x.getRowNo()==topLeftX && x.getColNo()==topLeftY){
                getNeighbourList().add(x);
                getNeighbourMove().add(MoveType.TOP_LEFT);
            }else if(x.getRowNo()==topRightX && x.getColNo()==topRightY){
                getNeighbourList().add(x);
                getNeighbourMove().add(MoveType.TOP_RIGHT);
            }else if(x.getRowNo()==bottomLeftX && x.getColNo()==bottomLeftY){
                getNeighbourList().add(x);
                getNeighbourMove().add(MoveType.BOTTOM_LEFT);
            }else if(x.getRowNo()==bottomRightX && x.getColNo()==bottomRightX){
                getNeighbourList().add(x);
                getNeighbourMove().add(MoveType.BOTTOM_RIGHT);
            }
        }
    }

    public void updateProbabilityForTransition(Board board){
        double new_prob=0;
        for (int i = 0; i <getNeighbourList().size() ; i++) {
            Block neighbor=getNeighbourList().get(i);
            MoveType moveType = getNeighbourMove().get(i);
            if (neighbor.blockType==BlockType.MIDDLE){

                if (moveType== MoveType.TOP || moveType== MoveType.BOTTOM || moveType== MoveType.LEFT || moveType== MoveType.RIGHT){
                    new_prob = new_prob + neighbor.currentProbability*CONSTANTS.MIDDLE_probabilityLeft_Right; //topBottom use krleo same
                }
                else if (moveType== MoveType.TOP_RIGHT || moveType== MoveType.TOP_LEFT
                        || moveType== MoveType.BOTTOM_RIGHT || moveType== MoveType.BOTTOM_LEFT){
                    new_prob = new_prob + neighbor.currentProbability*CONSTANTS.MIDDLE_probabilityDiagonal;
                }
                else if (moveType==MoveType.NO_MOVE)
                    new_prob = new_prob + neighbor.currentProbability*CONSTANTS.MIDDLE_probabilityNoMove;

            }
            else if (neighbor.blockType==BlockType.SIDE){

                if (moveType== MoveType.TOP || moveType== MoveType.BOTTOM
                        || moveType== MoveType.LEFT || moveType== MoveType.RIGHT){
                    new_prob=new_prob+neighbor.currentProbability*CONSTANTS.SIDE_probabilityLeft_Right; //TOP_Bottom dileo hobe
                }
                else if (moveType== MoveType.TOP_RIGHT || moveType== MoveType.TOP_LEFT
                        || moveType== MoveType.BOTTOM_RIGHT || moveType== MoveType.BOTTOM_LEFT){
                    new_prob=new_prob+neighbor.currentProbability*CONSTANTS.SIDE_probabilityDiagonal;
                }
                else if (moveType==MoveType.NO_MOVE)new_prob=new_prob+neighbor.currentProbability*CONSTANTS.SIDE_probabilityNoMove;

            }
            else if (neighbor.blockType==BlockType.CORNER){

                if (moveType== MoveType.TOP || moveType== MoveType.BOTTOM
                        || moveType== MoveType.LEFT || moveType== MoveType.RIGHT){
                    new_prob=new_prob+neighbor.currentProbability*CONSTANTS.CORNER_probabilityLeft_Right; //TOP_BOTTOM dileo hobe
                }
                else if (moveType== MoveType.TOP_RIGHT || moveType== MoveType.TOP_LEFT
                        || moveType== MoveType.BOTTOM_RIGHT || moveType== MoveType.BOTTOM_LEFT){
                    new_prob=new_prob+neighbor.currentProbability*CONSTANTS.CORNER_probabilityDiagonal;
                }
                else if (moveType==MoveType.NO_MOVE)new_prob=new_prob+neighbor.currentProbability*CONSTANTS.CORNER_probabilityNoMove;
            }
        }
        //currentProbability = new_prob;
        board.updatedTransitionalProb.add(new_prob);
    }

    public void printNeighbors(){
        System.out.println("current block : "+this.getRowNo()+"--"+this.getColNo());
        for (int i = 0; i <this.NeighbourList.size() ; i++) {
            System.out.println("pos(row,col): "+NeighbourList.get(i).getRowNo()+"--"+NeighbourList.get(i).getRowNo()
                    +"\t-->Relative pos: "+NeighbourMove.get(i));
        }
        System.out.println("-------------------------------------");
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getColNo() {
        return colNo;
    }

    public void setColNo(int colNo) {
        this.colNo = colNo;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public LinkedList<Block> getNeighbourList() {
        return NeighbourList;
    }

    public void setNeighbourList(LinkedList<Block> neighbourList) {
        NeighbourList = neighbourList;
    }

    public double getProbabilityLeft_Right() {
        return probabilityLeft_Right;
    }

    public void setProbabilityLeft_Right(double probabilityLeft_Right) {
        this.probabilityLeft_Right = probabilityLeft_Right;
    }

    public double getProbabilityTop_Bottom() {
        return probabilityTop_Bottom;
    }

    public void setProbabilityTop_Bottom(double probabilityTop_Bottom) {
        this.probabilityTop_Bottom = probabilityTop_Bottom;
    }

    public double getProbabilityDiagonal() {
        return probabilityDiagonal;
    }

    public void setProbabilityDiagonal(double probabilityDiagonal) {
        this.probabilityDiagonal = probabilityDiagonal;
    }

    public double getProbabilityNoMove() {
        return probabilityNoMove;
    }

    public void setProbabilityNoMove(double probabilityNoMove) {
        this.probabilityNoMove = probabilityNoMove;
    }

    public double getCurrentProbability() {
        return currentProbability;
    }

    public void setCurrentProbability(double currentProbability) {
        this.currentProbability = currentProbability;
    }
    public LinkedList<MoveType> getNeighbourMove() {
        return NeighbourMove;
    }

    public void setNeighbourMove(LinkedList<MoveType> neighbourMove) {
        NeighbourMove = neighbourMove;
    }


    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalCol() {
        return totalCol;
    }

    public void setTotalCol(int totalCol) {
        this.totalCol = totalCol;
    }

}
