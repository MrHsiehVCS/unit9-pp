package u9pp.Chess;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class KingTests {
    
    private ChessPiece[][] board;

    @BeforeEach
    public void beforeEach() {
        board = new ChessPiece[8][8];
        board[0][0] = new King(board, 0,0, false);
        board[7][0] = new King(board, 7, 0, true);
    }

    @Test
    public void King_canMoveOOB_returnsFalse() {
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(-1, 0),  "King should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 8, 0),  "King should not be able to move out of bounds."),
            () -> assertFalse(board[0][0].canMoveTo( 0, -1), "King should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 7, -1), "King should not be able to move out of bounds.")
        );
    }

    @Test
    public void King_canMoveSameSpace_returnsFalse() {
        assertFalse(board[0][0].canMoveTo(0, 0), "kings should not be able to move onto same square");
    }

    @Test
    public void King_canMoveOneSpaceAnyDirection_returnsTrue(){
        board[5][5] = new King(board, 5,5, true);
        assertAll(
            () -> assertTrue(board[5][5].canMoveTo(4, 4), "Kings must be able to move 1 sqaure in all directions"), 
            () -> assertTrue(board[5][5].canMoveTo(4, 5), "Kings must be able to move 1 sqaure in all directions"), 
            () -> assertTrue(board[5][5].canMoveTo(4, 6), "Kings must be able to move 1 sqaure in all directions"), 
            () -> assertTrue(board[5][5].canMoveTo(5, 4), "Kings must be able to move 1 sqaure in all directions"), 
            () -> assertTrue(board[5][5].canMoveTo(5, 6), "Kings must be able to move 1 sqaure in all directions"), 
            () -> assertTrue(board[5][5].canMoveTo(6, 4), "Kings must be able to move 1 sqaure in all directions"), 
            () -> assertTrue(board[5][5].canMoveTo(6, 5), "Kings must be able to move 1 sqaure in all directions"), 
            () -> assertTrue(board[5][5].canMoveTo(6, 6), "Kings must be able to move 1 sqaure in all directions")
        );
    }

    
    @Test
    public void King_canMoveWhenBlocked_returnsFalse(){
        board[1][0] = new Pawn(board, 1, 0, false);
        board[6][0] = new Pawn(board, 6, 0, true);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(1, 0), "Kings should not move forward onto other pieces"), 
            () -> assertFalse(board[7][0].canMoveTo(6, 0), "Kings should not move forward onto other pieces") 
        );
    }



    @Test
    public void King_canMoveTwoSpaces_returnsFalse(){
        board[5][5] = new King(board, 5,5, true);
        assertAll(
            () -> assertFalse(board[5][5].canMoveTo(3, 3), "Kings must not be able to move more than 1 sqaure in all directions"), 
            () -> assertFalse(board[5][5].canMoveTo(3, 5), "Kings must not be able to move more than 1 sqaure in all directions"), 
            () -> assertFalse(board[5][5].canMoveTo(3, 7), "Kings must not be able to move more than 1 sqaure in all directions"), 
            () -> assertFalse(board[5][5].canMoveTo(5, 3), "Kings must not be able to move more than 1 sqaure in all directions"), 
            () -> assertFalse(board[5][5].canMoveTo(5, 7), "Kings must not be able to move more than 1 sqaure in all directions"), 
            () -> assertFalse(board[5][5].canMoveTo(7, 3), "Kings must not be able to move more than 1 sqaure in all directions"), 
            () -> assertFalse(board[5][5].canMoveTo(7, 5), "Kings must not be able to move more than 1 sqaure in all directions"), 
            () -> assertFalse(board[5][5].canMoveTo(7, 7), "Kings must not be able to move more than 1 sqaure in all directions")
        );
    }


    @Test
    public void King_moveWhenTaking_returnsTrue() {
        board[1][1] = new Pawn(board, 1, 1, true);
        board[6][1] = new Pawn(board, 6, 1, false);
        assertAll(
            () -> assertTrue(board[0][0].canMoveTo(1, 1), "Kings should be able to move when taking"),
            () -> assertTrue(board[7][0].canMoveTo(6, 1), "Kings should be able to move when taking")
        );
    }

    @Test
    public void King_doMove_changesBoard() {
        ChessPiece botKing = board[0][0];
        ChessPiece topKing = board[7][0];
        board[0][0].doMove(1, 0);
        board[7][0].doMove(6, 0);
        assertAll(
            () -> assertNull(board[0][0], "doMove should set previous location to null"),
            () -> assertNull(board[7][0], "doMove should set previous location to null"),
            () -> assertEquals(botKing, board[1][0], "doMove should set new location to the piece that moved"),
            () -> assertEquals(topKing, board[6][0], "doMove should set new location to the piece that moved")
        );
    }

    @Test
    public void King_toString_returnsK() {
        assertAll(
            () -> assertEquals("k", board[0][0].toString(), "toString should return K for white King"),
            () -> assertEquals("K", board[7][0].toString(), "toString should return k for black King")
        );
    }

    @Test
    public void King_moveNextToOtherKing_returnsFalse() {
        board[1][2] = new King(board, 1, 2, false);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(0, 1), "Kings should not be able to move next to other kings"),
            () -> assertFalse(board[0][0].canMoveTo(1, 1), "Kings should not be able to move next to other kings"),
            () -> assertFalse(board[1][2].canMoveTo(0, 1), "Kings should not be able to move next to other kings"),
            () -> assertFalse(board[1][2].canMoveTo(1, 1), "Kings should not be able to move next to other kings")
        );
    }
  
}
    
    