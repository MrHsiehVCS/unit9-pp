package u9pp.Chess;

public class Queen extends ChessPiece {
    public Queen(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }

    public boolean canMoveTo(int row, int col) {
        if(super.canMoveTo(row, col) == false) {
            return false;
        }

        if((row == this.row && col != this.col) 
        || (col == this.col && row != this.row)
        || (Math.abs(col-this.col) == Math.abs(row-this.row))) {
            if(ChessPiece.pathClear(board, this.row, this.col, row, col)) {
                return true;    
            }
        }
        return false;
    }

    public String toString() {
        if(!isWhite) {
            return "q";
        }
        return "Q";
    }
}
