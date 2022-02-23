package u9pp.Chess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

public class QueenTests {
    private ChessPiece[][] board;

    @BeforeEach
    public void beforeEach() {
        board = new ChessPiece[8][8];
        board[0][0] = new Queen(board, 0,0, false);
        board[7][0] = new Queen(board, 7, 0, true);
    }

    @Test
    public void Queen_canMoveOOB_returnsFalse() {
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(-1, 0),  "Queen should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 8, 0),  "Queen should not be able to move out of bounds."),
            () -> assertFalse(board[0][0].canMoveTo( 0, -1), "Queen should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 7, -1), "Queen should not be able to move out of bounds."),
            () -> assertFalse(board[0][0].canMoveTo( 0, 8), "Queen should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 7, 8), "Queen should not be able to move out of bounds.")
        );
    }

    @Test
    public void Queen_canMoveSameSpace_returnsFalse() {
        assertFalse(board[0][0].canMoveTo(0, 0), "Queens should not be able to move onto same square");
    }
    
    @Test
    public void Queen_canMoveOntoPieceSameTeam_returnsFalse(){
        board[1][0] = new Pawn(board, 1, 0, false);
        board[6][0] = new Pawn(board, 6, 0, true);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(1, 0), "Queens should not move onto other pieces on the same team"), 
            () -> assertFalse(board[7][0].canMoveTo(6, 0), "Queens should not move onto other pieces on the same team") 
        );
    }

    @Test
    public void Queen_canMoveThroughPiece_returnsFalse() {
        board[4][0] = new Queen(board, 4, 0, false);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(5, 0), "Queens should not move through other pieces"), 
            () -> assertFalse(board[7][0].canMoveTo(2, 0), "Queens should not move through other pieces") 
        );
    }

    @Test
    public void Queen_canMoveManySpaces_returnsTrue(){
        board = new ChessPiece[8][8];
        board[5][5] = new Queen(board, 5,5, true);
        List<Executable> tests = new ArrayList<Executable>();
        for(int r = 0; r < 8; r++) {
            if(r == 5) continue;
            final int rFinal = r; 
            tests.add(() -> assertTrue(board[5][5].canMoveTo(rFinal, 5), "Queen must be able to move any distance in the same row"));
        }
        for(int c = 0; c < 8; c++) {
            if(c == 5) continue;
            final int cFinal = c; 
            tests.add(() -> assertTrue(board[5][5].canMoveTo(5, cFinal), "Queen must be able to move any distance in the same col"));
        }

        for(int i = -7; i < 8; i++ ) {
            int r = 5 - i; 
            int c = 5 - i; 
            if(r == 5 && c == 5) {
                continue;
            }
            if(r < 0 || r >= 8 || c < 0 || c >= 8) {
                continue;
            }
            tests.add(()-> assertTrue(board[5][5].canMoveTo(r, c), "Queens must be able to move anywhere on the board diagonally"));
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
            tests.add(()-> assertTrue(board[5][5].canMoveTo(r, c), "Queens must be able to move anywhere on the board diagonally"));
        }

        assertAll( tests );
    }


    @Test
    public void Queen_moveWhenTaking_returnsTrue() {

        board[1][1] = new Pawn(board, 1, 1, true);
        board[6][1] = new Pawn(board, 6, 1, false);
        assertAll(
            () -> assertTrue(board[0][0].canMoveTo(7, 0), "Queens should be able to move when taking"),
            () -> assertTrue(board[7][0].canMoveTo(0, 0), "Queens should be able to move when taking"),
            () -> assertTrue(board[0][0].canMoveTo(1, 1), "Queens should be able to move when taking"),
            () -> assertTrue(board[7][0].canMoveTo(6, 1), "Queens should be able to move when taking")
        );
    }

    @Test
    public void Queen_doMove_changesBoard() {
        ChessPiece botQueen = board[0][0];
        ChessPiece topQueen = board[7][0];
        board[0][0].doMove(1, 0);
        board[7][0].doMove(6, 0);
        assertAll(
            () -> assertNull(board[0][0], "doMove should set previous location to null"),
            () -> assertNull(board[7][0], "doMove should set previous location to null"),
            () -> assertEquals(botQueen, board[1][0], "doMove should set new location to the piece that moved"),
            () -> assertEquals(topQueen, board[6][0], "doMove should set new location to the piece that moved")
        );
    }

    @Test
    public void Queen_toString_returnsQ() {
        assertAll(
            () -> assertEquals("q", board[0][0].toString(), "toString should return Q for white Queen"),
            () -> assertEquals("Q", board[7][0].toString(), "toString should return q for black Queen")
        );
    }


}
