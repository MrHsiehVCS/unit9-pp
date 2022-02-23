package u9pp.Chess;

public class Pawn extends ChessPiece{

    private boolean hasMoved = false;

    public Pawn(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }

    public void doMove(int row, int col) {
        this.hasMoved = true;
        super.doMove(row, col);
    }
    
    public boolean canMoveTo(int row, int col) {
        if(super.canMoveTo(row, col) == false) {
            return false;
        }

        int direction = 1;
        if(isWhite) {
            direction = -1;
        }

        if(row == this.row + direction * 2 && col == this.col && board[row][this.col] == null && !hasMoved) {
            // move forward 2 spaces on first turn
            return true; 
        } else if(row == this.row + direction && col == this.col && board[row][this.col] == null) {
            // move forward, no collision
            return true;
        } else if (row == this.row + direction && Math.abs(col-this.col) == 1 && board[row][col] != null && board[row][col].isWhite != this.isWhite) {
            // we feast on the blood of the enemy
            return true;
        }

        return false;
    }
    
    public String toString() {
        if(!isWhite) {
            return "p";
        }
        return "P";
    }
}
