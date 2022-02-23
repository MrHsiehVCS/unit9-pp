package u9pp.Chess;

public abstract class ChessPiece {
    protected int row;
    protected int col;
    protected ChessPiece[][] board;
    protected boolean isWhite;
    
    public ChessPiece(ChessPiece[][] board, int row, int col, boolean isWhite) {
        this.row = row;
        this.col = col;
        this.board = board;
        this.isWhite = isWhite;
    }
    
    // returns true if the move is valid
    // returns false if move is invalid
    public boolean canMoveTo(int row, int col) {
        if(row < 0 || row >= 8 || col < 0 || col >= 8) {
            // out of bounds
            return false;
        }
        if(row == this.row && col == this.col) {
            // not actually moving
            return false;
        }
        if(board[row][col] != null && board[row][col].isWhite == this.isWhite) {
            // landing on piece of our color
            return false;
        }
        return true;
    }
    
    public void doMove(int row, int col) {
        board[this.row][this.col] = null;
        board[row][col] = this;
        this.row = row; 
        this.col = col;
    }
    
    public static boolean pathClear(ChessPiece[][] board, int row1, int col1, int row2, int col2) {
        if(row1 == row2) {
            for(int i = Math.min(col1, col2) + 1; i < Math.max(col1, col2); i++) {
                if(board[row1][i] != null) {
                    return false;
                }
            }
            return true;
        }
        if(col1 == col2) {
            for(int i = Math.min(row1, row2) + 1; i < Math.max(row1, row2); i++) {
                if(board[i][col1] != null) {
                    return false;
                }
            }
            return true;
        }
        if(Math.abs(row1-row2) == Math.abs(col1-col2)) {
            for(int i = 1; i < Math.abs(row1-row2); i++) {
                int r = row1 + i;
                if(row2 < row1) {
                    r = row1 - i;
                }
                int c = col1 + i;
                if(col2 < col1) {
                    c = col1 - i;
                }
                if(board[r][c] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public abstract String toString();

    public boolean isWhite() { return isWhite;}
}
