package u9pp.Chess;

public class King extends ChessPiece{
    public King(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }
    
    public boolean canMoveTo(int row, int col) {
        if(super.canMoveTo(row,col) == false) {
            return false;
        }
        if(Math.abs(row-this.row) > 1 || Math.abs(col-this.col) > 1) {
            return false;
        }
        if(isSquareNextToKing(row, col)) {
            return false;
        }
        return true;
    }
    
    private boolean isSquareNextToKing(int row, int col) {
        for(int r = Math.max(row-1, 0); r < Math.min(row+2, 8); r++) {
            for(int c = Math.max(col-1, 0); c < Math.min(col+2, 8); c++) {
                if(r == row && c == col) {
                    continue;
                }
                if(board[r][c] == this) {
                    continue;
                }
                if(board[r][c] instanceof King) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public String toString() {
        if(!isWhite) {
            return "k";
        }
        return "K";
    }
}
