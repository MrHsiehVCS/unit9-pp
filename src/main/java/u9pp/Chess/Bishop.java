package u9pp.Chess;

public class Bishop extends ChessPiece {
    public Bishop(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }

    public boolean canMoveTo(int row, int col) {
        if(super.canMoveTo(row, col) == false) {
            return false;
        }

        if(Math.abs(col-this.col) == Math.abs(row-this.row)) {
            if(ChessPiece.pathClear(board, this.row, this.col, row, col)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        if(!isWhite) {
            return "b";
        }
        return "B";
    }
}
