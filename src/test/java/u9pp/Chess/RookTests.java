package u9pp.Chess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

public class RookTests {
    private ChessPiece[][] board;

    @BeforeEach
    public void beforeEach() {
        board = new ChessPiece[8][8];
        board[0][0] = new Rook(board, 0,0, false);
        board[7][0] = new Rook(board, 7, 0, true);
    }

    @Test
    public void Rook_canMoveOOB_returnsFalse() {
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(-1, 0),  "Rook should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 8, 0),  "Rook should not be able to move out of bounds."),
            () -> assertFalse(board[0][0].canMoveTo( 0, -1), "Rook should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 7, -1), "Rook should not be able to move out of bounds."),
            () -> assertFalse(board[0][0].canMoveTo( 0, 8), "Rook should not be able to move out of bounds."),
            () -> assertFalse(board[7][0].canMoveTo( 7, 8), "Rook should not be able to move out of bounds.")
        );
    }

    @Test
    public void Rook_canMoveSameSpace_returnsFalse() {
        assertFalse(board[0][0].canMoveTo(0, 0), "Rooks should not be able to move onto same square");
    }
    
    @Test
    public void Rook_canMoveOntoPieceSameTeam_returnsFalse(){
        board[1][0] = new Pawn(board, 1, 0, false);
        board[6][0] = new Pawn(board, 6, 0, true);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(1, 0), "Rooks should not move onto other pieces on the same team"), 
            () -> assertFalse(board[7][0].canMoveTo(6, 0), "Rooks should not move onto other pieces on the same team") 
        );
    }

    @Test
    public void Rook_canMoveThroughPiece_returnsFalse() {
        board[4][0] = new Rook(board, 4, 0, false);
        assertAll(
            () -> assertFalse(board[0][0].canMoveTo(5, 0), "Rooks should not move through other pieces"), 
            () -> assertFalse(board[7][0].canMoveTo(2, 0), "Rooks should not move through other pieces") 
        );
    }

    @Test
    public void Rook_canMoveManySpaces_returnsTrue(){
        board = new ChessPiece[8][8];
        board[5][5] = new Rook(board, 5,5, true);
        List<Executable> tests = new ArrayList<Executable>();
        for(int r = 0; r < 8; r++) {
            if(r == 5) continue;
            final int rFinal = r; 
            tests.add(() -> assertTrue(board[5][5].canMoveTo(rFinal, 5), "Rook must be able to move any distance in the same row"));
        }
        for(int c = 0; c < 8; c++) {
            if(c == 5) continue;
            final int cFinal = c; 
            tests.add(() -> assertTrue(board[5][5].canMoveTo(5, cFinal), "Rook must be able to move any distance in the same col"));
        }
        assertAll( tests );
    }


    @Test
    public void Rook_moveWhenTaking_returnsTrue() {
        board[0][1] = new Pawn(board, 0, 1, true);
        board[7][1] = new Pawn(board, 7, 1, false);
        assertAll(
            () -> assertTrue(board[0][0].canMoveTo(7, 0), "Rooks should be able to move when taking"),
            () -> assertTrue(board[7][0].canMoveTo(0, 0), "Rooks should be able to move when taking"),
            () -> assertTrue(board[0][0].canMoveTo(0, 1), "Rooks should be able to move when taking"),
            () -> assertTrue(board[7][0].canMoveTo(7, 1), "Rooks should be able to move when taking")
        );
    }

    @Test
    public void Rook_doMove_changesBoard() {
        ChessPiece botRook = board[0][0];
        ChessPiece topRook = board[7][0];
        board[0][0].doMove(1, 0);
        board[7][0].doMove(6, 0);
        assertAll(
            () -> assertNull(board[0][0], "doMove should set previous location to null"),
            () -> assertNull(board[7][0], "doMove should set previous location to null"),
            () -> assertEquals(botRook, board[1][0], "doMove should set new location to the piece that moved"),
            () -> assertEquals(topRook, board[6][0], "doMove should set new location to the piece that moved")
        );
    }

    @Test
    public void Rook_toString_returnsR() {
        assertAll(
            () -> assertEquals("r", board[0][0].toString(), "toString should return R for white Rook"),
            () -> assertEquals("R", board[7][0].toString(), "toString should return r for black Rook")
        );
    }


}
