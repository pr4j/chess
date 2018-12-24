package pieces;

public class King extends Piece {

    public King(int row, int column, int start){
        super(row,column,start);
    }

    public int validateMove(){
        if(super.start==0){
        }
        return -1;
    }
}
