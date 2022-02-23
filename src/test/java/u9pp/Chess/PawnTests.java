package u9pp.Chess;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class PawnTests {

    private ChessPiece[][] board;

    @BeforeEach
    public void beforeEach() {
        board = new ChessPiece[8][8];
        board[0][0] = new Pawn(board, 0, 0, false);
        board[7][0] = new Pawn(board, 7, 0, true);
    }

    @Test
    public void Pawn_canMoveOOB_returnsFalse() {
        board[0][7] = new Pawn(board, 0, 7,false);
        board[7][7] = new Pawn(board, 7, 7, true);
        assertAll(
            () -> assertFalse(board[0][7].canMoveTo(-1, 7), "Pawn should not be able to move out of bounds."),
            () -> assertFalse(board[7][7].canMoveTo(8, 7), "Pawn should not be able to move out of bounds."),
            () -> assertFalse(board[0][7].canMoveTo(0, 8), "Pawn should not be able to move out of bounds."),
            () -> assertFalse(board[7][7].canMoveTo(7, 8), "Pawn should not be able to move out of bounds.")
        );
    }

    @Test
    public void Pawn_canMoveSameSquare_returnsFalse() {
        board[0][7] = new Pawn(board, 0, 7, false);
        board[7][7] = new Pawn(board, 7, 7, true);
        assertAll(
            () -> assertFalse(board[0][7].canMoveTo(0, 7), "Pawn should not be able to move onto current square."),
            () -> assertFalse(board[7][7].canMoveTo(7, 7), "Pawn should not be able to move onto current square.")
        );
    }

    @Test
    public void Pawn_canMoveOneSpaceForward_returnsTrue(){
        assertAll(
            () -> assertTrue(board[0][0].canMoveTo(1, 0), "white pawns must be able to move forward 1 square"), 
            () -> assertTrue(board[7][0].canMoveTo(6, 0), "black pawns must be able to move forward 1 square") 
        );
    }

    @Test
    public void Pawn_canMoveOneSpaceBackward_returnsFalse() {
        board[5][1] = new Pawn(board, 0,0, false);
        board[6][1] = new Pawn(board, 7, 0, true);
        assertFalse(board[6][1].canMoveTo(7, 1));
        assertAll(
            () -> assertFalse(board[5][1].canMoveTo(4, 1), "white pawns must not be able to move backward"), 
            () -> assertFalse(board[6][1].canMoveTo(7, 1), "black pawns must not be able to move backward") 
        );
    }

    @Test
    public void Pawn_canMoveForwardWhenBlocked_returnsFalse(){
        board[1][0] = new Pawn(board, 1, 0, true);
        board[6][0] = new Pawn(board, 6, 0, true);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(1, 0), "pawns should not move forward onto other pieces"), 
            () -> assertFalse(board[7][0].canMoveTo(6, 0), "pawns should not move forward onto other pieces") 
        );
    }

    @Test
    public void Pawn_canMoveTwoSpacesFirstTurn_returnsTrue(){
        assertAll(
            () -> assertTrue(board[0][0].canMoveTo(2, 0), "white pawns must be able to move two spaces on their first turn"), 
            () -> assertTrue(board[7][0].canMoveTo(5, 0), "black pawns must be able to move two spaces on their first turn") 
        );
    }

    @Test
    public void Pawn_canMoveTwoSpacesSecondTurn_returnsFalse(){
        board[0][0].doMove(1, 0);
        board[7][0].doMove(6, 0);
      
        assertAll(
            () -> assertFalse(board[1][0].canMoveTo(3, 0), "white pawns must not be able to move two spaces on their second turn"), 
            () -> assertFalse(board[6][0].canMoveTo(4, 0), "black pawns must not be able to move two spaces on their second turn") 
        );
    }

    @Test
    public void Pawn_moveDiagonalWhenNotTaking_returnsFalse() {
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(1, 1), "pawns should not be able to move diagonally when not taking"),
            () -> assertFalse(board[7][0].canMoveTo(6, 1), "pawns should not be able to move diagonally when not taking")
        );
    }

    @Test
    public void Pawn_moveDiagonalWhenTaking_returnsTrue() {
        board[1][1] = new Pawn(board, 1, 1, true);
        board[6][1] = new Pawn(board, 6, 1, false);
        assertAll(
            () -> assertTrue(board[0][0].canMoveTo(1, 1), "pawns should be able to move diagonal when taking"),
            () -> assertTrue(board[7][0].canMoveTo(6, 1), "pawns should be able to move diagonal when taking")
        );
    }

    @Test
    public void Pawn_moveDiagonalOntoTeammate_returnsFalse() {
        board[1][1] = new Pawn(board, 1, 1, false);
        board[6][1] = new Pawn(board, 6, 1, true);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(1, 1), "pawns should not be able to move diagonal onto a teammate"),
            () -> assertFalse(board[7][0].canMoveTo(6, 1), "pawns should not be able to move diagonal onto a teammate")
        );
    }

    @Test
    public void Pawn_doMove_changesBoard() {
        ChessPiece botPawn = board[0][0];
        ChessPiece topPawn = board[7][0];
        board[0][0].doMove(1, 0);
        board[7][0].doMove(6, 0);
        assertAll(
            () -> assertNull(board[0][0], "doMove should set previous location to null"),
            () -> assertNull(board[7][0], "doMove should set previous location to null"),
            () -> assertEquals(botPawn, board[1][0], "doMove should set new location to the piece that moved"),
            () -> assertEquals(topPawn, board[6][0], "doMove should set new location to the piece that moved")
        );
    }

    @Test
    public void Pawn_toString_returnsP() {
        assertAll(
            () -> assertEquals("p", board[0][0].toString(), "toString should return P for white pawn"),
            () -> assertEquals("P", board[7][0].toString(), "toString should return p for black pawn")
        );
    }
}