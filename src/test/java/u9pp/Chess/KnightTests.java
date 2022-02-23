package u9pp.Chess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

public class KnightTests {
    private ChessPiece[][] board;

    @BeforeEach
    public void beforeEach() {
        board = new ChessPiece[8][8];
        board[0][0] = new Knight(board, 0,0, false);
        board[7][0] = new Knight(board, 7, 0, true);
    }

    @Test
    public void Knight_canMoveOOB_returnsFalse() {
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(-1, 0),  "Knight should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 8, 0),  "Knight should not be able to move out of bounds."),
            () -> assertFalse(board[0][0].canMoveTo( 0, -1), "Knight should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 7, -1), "Knight should not be able to move out of bounds."),
            () -> assertFalse(board[0][0].canMoveTo( 0, 8), "Knight should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 7, 8), "Knight should not be able to move out of bounds.")
        );
    }

    @Test
    public void Knight_canMoveSameSpace_returnsFalse() {
        assertFalse(board[0][0].canMoveTo(0, 0), "Knights should not be able to move onto it's current square");
    }
    
    @Test
    public void Knight_canMoveOntoPieceSameTeam_returnsFalse(){
        board[2][1] = new Pawn(board, 2, 1, false);
        board[1][2] = new Pawn(board, 1, 2, false);
        board[6][2] = new Pawn(board, 6, 1, true);
        board[5][1] = new Pawn(board, 5, 2, true);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(1, 2), "Knights should not be able to move onto piece of same color"),
            () -> assertFalse(board[0][0].canMoveTo(2, 1), "Knights should not be able to move onto piece of same color"),
            () -> assertFalse(board[7][0].canMoveTo(5, 1), "Knights should not be able to move onto piece of same color"),
            () -> assertFalse(board[7][0].canMoveTo(6, 2), "Knights should not be able to move onto piece of same color")
        );
    }

    @Test
    public void Knight_canMoveThroughPiece_returnsTrue() {
        board[1][0] = new Knight(board, 1, 0, false);
        board[1][1] = new Knight(board, 1, 1, false);
        board[0][1] = new Knight(board, 0, 1, false);
        assertAll(
            () -> assertTrue(board[0][0].canMoveTo(2, 1), "Knights should jump over other pieces"), 
            () -> assertTrue(board[0][0].canMoveTo(1, 2), "Knights should jump over other pieces") 
        );
    }

    @Test
    public void Knight_canMoveToValidEmptySpaces_returnsTrue(){
        board = new ChessPiece[8][8];
        board[5][5] = new Knight(board, 5,5, true);
        List<Executable> tests = new ArrayList<Executable>();

        for(int r = 0; r < 8; r ++) {
            for(int c = 0; c < 8; c++) {
                if(r == 5 && c == 5) continue;
                int rDiff = Math.abs(r-5);
                int cDiff = Math.abs(c-5);
                if(rDiff + cDiff == 3 && Math.abs(cDiff - rDiff) == 1) {
                    final int rFinal = r; 
                    final int cFinal = c; 
                    tests.add(
                        () -> assertTrue(board[5][5].canMoveTo(rFinal, cFinal), "Knight must be able to move to all squares that are a 3 square 'L' away.")
                    );
                }
            }
        }
        assertAll( tests );
    }

    @Test
    public void Knight_canMoveToInvalidEmptySpaces_returnsFalse(){
        board = new ChessPiece[8][8];
        board[5][5] = new Knight(board, 5,5, true);
        List<Executable> tests = new ArrayList<Executable>();

        for(int r = 0; r < 8; r ++) {
            for(int c = 0; c < 8; c++) {
                if(r == 5 && c == 5) continue;
                int rDiff = Math.abs(r-5);
                int cDiff = Math.abs(c-5);
                if(rDiff + cDiff != 3 || Math.abs(rDiff - cDiff) != 1) {
                    final int rFinal = r; 
                    final int cFinal = c; 
                    tests.add(
                        () -> assertFalse(board[5][5].canMoveTo(rFinal, cFinal), "Knight cannot move to squares that are not a 3 square 'L' away.")
                    );
                }
            }
        }
        assertAll( tests );
    }

    @Test
    public void Knight_moveWhenTaking_returnsTrue() {

        board[1][2] = new Pawn(board, 1, 2, true);
        board[2][1] = new Pawn(board, 2, 1, true);
        board[5][1] = new Pawn(board, 5, 1, false);
        board[6][2] = new Pawn(board, 6, 2, false);
        assertAll(
            () -> assertTrue(board[0][0].canMoveTo(1, 2), "Knights should be able to move when taking"),
            () -> assertTrue(board[0][0].canMoveTo(2, 1), "Knights should be able to move when taking"),
            () -> assertTrue(board[7][0].canMoveTo(5, 1), "Knights should be able to move when taking"),
            () -> assertTrue(board[7][0].canMoveTo(6, 2), "Knights should be able to move when taking")
        );
    }

    @Test
    public void Knight_doMove_changesBoard() {
        ChessPiece botKnight = board[0][0];
        ChessPiece topKnight = board[7][0];
        board[0][0].doMove(2, 1);
        board[7][0].doMove(6, 2);
        assertAll(
            () -> assertNull(board[0][0], "doMove should set previous location to null"),
            () -> assertNull(board[7][0], "doMove should set previous location to null"),
            () -> assertEquals(botKnight, board[2][1], "doMove should set new location to the piece that moved"),
            () -> assertEquals(topKnight, board[6][2], "doMove should set new location to the piece that moved")
        );
    }

    @Test
    public void Knight_toString_returnsK() {
        assertAll(
            () -> assertEquals("n", board[0][0].toString(), "toString should return N for white Knight"),
            () -> assertEquals("N", board[7][0].toString(), "toString should return n for black Knight")
        );
    }
}
