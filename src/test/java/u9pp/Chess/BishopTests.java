package u9pp.Chess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

public class BishopTests {
    private ChessPiece[][] board;

    @BeforeEach
    public void beforeEach() {
        board = new ChessPiece[8][8];
        board[0][0] = new Bishop(board, 0,0, false);
        board[7][0] = new Bishop(board, 7, 0, true);
    }

    @Test
    public void Bishop_canMoveOOB_returnsFalse() {
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(-1, 0),  "Bishop should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 8, 0),  "Bishop should not be able to move out of bounds."),
            () -> assertFalse(board[0][0].canMoveTo( 0, -1), "Bishop should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 7, -1), "Bishop should not be able to move out of bounds."),
            () -> assertFalse(board[0][0].canMoveTo( 0, 8), "Bishop should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 7, 8), "Bishop should not be able to move out of bounds.")
        );
    }

    @Test
    public void Bishop_canMoveSameSpace_returnsFalse() {
        assertFalse(board[0][0].canMoveTo(0, 0), "Bishops should not be able to move onto same square");
    }
    
    @Test
    public void Bishop_canMoveOntoPieceSameTeam_returnsFalse(){
        board[1][1] = new Pawn(board, 1, 1, false);
        board[6][1] = new Pawn(board, 6, 1, true);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(1, 1), "Bishops should not move onto other pieces on the same team"), 
            () -> assertFalse(board[7][0].canMoveTo(6, 1), "Bishops should not move onto other pieces on the same team") 
        );
    }

    @Test
    public void Bishop_canMoveThroughPiece_returnsFalse() {
        board[1][1] = new Bishop(board, 1, 1, false);
        board[6][1] = new Bishop(board, 6, 1, false);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(2, 2), "Bishops should not move through other pieces"), 
            () -> assertFalse(board[7][0].canMoveTo(5, 2), "Bishops should not move through other pieces") 
        );
    }

    @Test
    public void Bishop_canMoveManySpaces_returnsTrue(){
        board = new ChessPiece[8][8];
        board[5][5] = new Bishop(board, 5,5, true);
        List<Executable> tests = new ArrayList<Executable>();
        for(int i = -7; i < 8; i++ ) {
            int r = 5 - i; 
            int c = 5 - i; 
            if(r == 5 && c == 5) {
                continue;
            }
            if(r < 0 || r >= 8 || c < 0 || c >= 8) {
                continue;
            }
            tests.add(()-> assertTrue(board[5][5].canMoveTo(r, c), "Bishops must be able to move anywhere on the board diagonally"));
        }

        for(int i = -7; i < 8; i++ ) {
            int r = 5 - i; 
            int c = 5 + i;
            if(r == 5 && c == 5) {
                continue;
            }
            if(r < 0 || r >= 8 || c < 0 || c >= 8) {
                continue;
            }
            tests.add(()-> assertTrue(board[5][5].canMoveTo(r, c), "Bishops must be able to move anywhere on the board diagonally"));
        }

        assertAll( tests );
    }


    @Test
    public void Bishop_moveWhenTaking_returnsTrue() {

        board[1][1] = new Pawn(board, 1, 1, true);
        board[6][1] = new Pawn(board, 6, 1, false);
        assertAll(
            () -> assertTrue(board[0][0].canMoveTo(1, 1), "Bishops should be able to move when taking"),
            () -> assertTrue(board[7][0].canMoveTo(6, 1), "Bishops should be able to move when taking")
        );
    }

    @Test
    public void Bishop_doMove_changesBoard() {
        ChessPiece botBishop = board[0][0];
        ChessPiece topBishop = board[7][0];
        board[0][0].doMove(1, 1);
        board[7][0].doMove(6, 1);
        assertAll(
            () -> assertNull(board[0][0], "doMove should set previous location to null"),
            () -> assertNull(board[7][0], "doMove should set previous location to null"),
            () -> assertEquals(botBishop, board[1][1], "doMove should set new location to the piece that moved"),
            () -> assertEquals(topBishop, board[6][1], "doMove should set new location to the piece that moved")
        );
    }

    @Test
    public void Bishop_toString_returnsB() {
        assertAll(
            () -> assertEquals("b", board[0][0].toString(), "toString should return B for white Bishop"),
            () -> assertEquals("B", board[7][0].toString(), "toString should return b for black Bishop")
        );
    }


}
