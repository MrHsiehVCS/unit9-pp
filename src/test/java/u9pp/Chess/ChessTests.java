package u9pp.Chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessTests {
    
    private ChessPiece[][] board;

    @BeforeEach
    public void BeforeEach() {
        board = new ChessPiece[8][8];
    }

    @Test
    public void HasWinner_oneWhiteKing_returnsTrue() {
        board[4][4] =  new King(board, 4, 4, true);
        assertTrue(Chess.hasWinner(board), "Chess.hasWinner should return true when the board only has 1 white king");
    }

    @Test
    public void HasWinner_oneBlackKing_returnsTrue() {
        board[5][5] =  new King(board, 5, 5, false);
        assertTrue(Chess.hasWinner(board), "Chess.hasWinner should return true when the board only has 1 black king");
    }

    @Test
    public void HasWinner_twoKings_returnsFalse() {
        board[4][4] =  new King(board, 4, 4, true);
        board[5][5] =  new King(board, 5, 5, false);
        assertFalse(Chess.hasWinner(board), "Chess.hasWinner should return false when the board only has 1 king of each color");
 
    }
}
