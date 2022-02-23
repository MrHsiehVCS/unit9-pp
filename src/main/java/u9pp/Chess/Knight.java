package u9pp.Chess;

public class Knight extends ChessPiece {
    public Knight(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }

    public boolean canMoveTo(int row, int col) {
        if(super.canMoveTo(row, col) == false) {
            return false;
        }

        if(Math.abs(row-this.row) + Math.abs(col-this.col) == 3
        && Math.abs(Math.abs(row-this.row) - Math.abs(col-this.col)) == 1) {
            return true;
        }
        return false;
    }

    public String toString() {
        if(!isWhite) {
            return "n";
        }
        return "N";
    }
}
