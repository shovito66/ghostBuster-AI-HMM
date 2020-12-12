package basic;

public class Ghost {
    private int rowNo;
    private int colNo;
    boolean isBusted;
    Block current_block;

    public Ghost(int rowNo, int colNo, Block current_block) {
        this.rowNo = rowNo;
        this.colNo = colNo;
        this.current_block = current_block;
        this.isBusted = false;
    }

    public void moveGhost(){

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

    public boolean isBusted() {
        return isBusted;
    }

    public void setBusted(boolean busted) {
        isBusted = busted;
    }

    public Block getCurrent_block() {
        return current_block;
    }

    public void setCurrent_block(Block current_block) {
        this.current_block = current_block;
    }
}
