package u9pp.Chess;

import java.util.*;

import u9pp.InputHelper;
import u9pp.ListUtilities;

public class Chess {
    
    private ChessPiece[][] board;
    
    public Chess() {
        initializeBoard();
    }
    
    public void play(Scanner scanner) {
        /* Your code here */
    }

    public static boolean hasWinner(ChessPiece[][] board) {
        /* Your code here */
    }
    
    public void initializeBoard() {
        board = new ChessPiece[8][8];
        
        board[0][4] = new King(board, 0, 4, false);
        board[7][4] = new King(board, 7, 4, true);
        
        board[0][3] = new Queen(board, 0, 3, false);
        board[7][3] = new Queen(board, 7, 3, true);
        
        for(int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(board, 1, i, false);
            board[6][i] = new Pawn(board, 6, i, true);
        }
        
        board[0][2] = new Bishop(board, 0, 2, false);
        board[7][2] = new Bishop(board, 7, 2, true);
        board[0][5] = new Bishop(board, 0, 5, false);
        board[7][5] = new Bishop(board, 7, 5, true);
        
        board[0][0] = new Rook(board, 0, 0, false);
        board[7][0] = new Rook(board, 7, 0, true);
        board[0][7] = new Rook(board, 0, 7, false);
        board[7][7] = new Rook(board, 7, 7, true);
        
        board[0][1] = new Knight(board, 0, 1, false);
        board[7][1] = new Knight(board, 7, 1, true);
        board[0][6] = new Knight(board, 0, 6, false);
        board[7][6] = new Knight(board, 7, 6, true);
    }
    
    private String doComputerTurn(boolean isWhite) {
        // very simple AI. randomly moves a random piece. 
        
        // get all pieces
        List<ChessPiece> allMyPieces = getAllPiecesOfColor(isWhite);
        ListUtilities.shuffleList(allMyPieces);
        
        // get all possible move location pairs
        List<Integer> rows = getNumsRandomized(0, 7);
        List<Integer> cols = getNumsRandomized(0, 7);
        
        // try all moves for all pieces until one works
        for(ChessPiece piece : allMyPieces) {
            for(Integer r : rows) {
                for(Integer c : cols) {
                    if(piece.canMoveTo(r, c)) {
                        int prevRow = piece.row;
                        int prevCol = piece.col;
                        piece.doMove(r, c);
                        //displayBoard(true, true);
                        return (String.format("Computer moved the %s at row %s, col %s to row %s, %s.",
                            piece, prevRow, prevCol, r, c));
                    }
                }
            }
        }
        return "";
    }
    
    private List<ChessPiece> getAllPiecesOfColor(boolean isWhite) {
        List<ChessPiece> pieces = new ArrayList<>();
        for(ChessPiece[] row : board) {
            for(ChessPiece piece : row) {
                if(piece != null && piece.isWhite() == isWhite) {
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }
    
    public void displayBoard(boolean displayRowLabel, boolean displayColLabel) {
        if(displayColLabel) {
            System.out.print(" ".repeat(3));
            for(int i = 0; i < 8; i++) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        System.out.println("  " + "+-".repeat(8) + "+");
        for(int r = 0; r < board.length; r++) {
            String output = "";
            if(displayRowLabel) {
                output += (r) + " |";
            } else {
                output += "  |";
            }
            for(int c = 0; c < board[0].length; c++) {
                if(board[r][c] == null) {
                    output += " ";
                } else {
                    output += board[r][c];
                }
                output += "|";
            }
            if(displayRowLabel) {
                output += (" " + r);
            }
            System.out.println(output);
            
            System.out.println("  " + "+-".repeat(8) + "+");
        }
        
        if(displayColLabel) {
            System.out.print(" ".repeat(3));
            for(int i = 0; i < 8; i++) {
                System.out.print(i + " ");
            }
        } 
        System.out.println();
        
    } 
    
    private List<Integer> getNumsRandomized(int lowerBound, int higherBound) {
        if(lowerBound > higherBound) {
            return Collections.emptyList();
        }
        List<Integer> output = new ArrayList<>();
        for(int i = lowerBound; i <= higherBound; i++) {
            output.add(i);
        }
        ListUtilities.shuffleList(output);
        return output;
    }
    

    
}
