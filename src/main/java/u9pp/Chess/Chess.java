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
        System.out.println("Welcome to chess!");
        boolean computerActive = InputHelper.getValidNumberInput(scanner, "Please select a mode. (1) player or (2) player:", 1, 2) == 1;
        boolean isWhiteTurn = true; 
        
        boolean shouldPlay = true;
        while(shouldPlay) {
            while(!hasWinner()) {
                
                // computer turn
                if(computerActive && !isWhiteTurn) {
                    System.out.println(doComputerTurn(isWhiteTurn)); 
                    isWhiteTurn = !isWhiteTurn;
                    continue;
                }
                else
                {
                    displayBoard(true, true);
                }
                
                // player select piece
                while(true) 
                {
                    ChessPiece selectedPiece = null;
                    int selectedRow = InputHelper.getValidNumberInput(scanner, "Please indicate a piece to move. Row: ", 0, 7);
                    int selectedCol = InputHelper.getValidNumberInput(scanner, "You have selected row " + selectedRow + ". Col: ", 0, 7);
                    selectedPiece = board[selectedRow][selectedCol];
                    
                    
                    if(selectedPiece == null) {
                        System.out.println("You selected an empty square. Please try again.");
                        continue;
                    } 
                    
                    if(selectedPiece.isWhite() != isWhiteTurn) {
                        String currColor = "white"; 
                        String selectedColor = "black";
                        if(!isWhiteTurn) {
                            currColor = "black";
                            selectedColor = "white";
                        }
                        System.out.println(String.format("You selected a %s piece, but it is currently %s's turn", selectedColor, currColor));
                        continue;
                    }
                    
                    System.out.println("You have selected the " + selectedPiece 
                    + " at [" + selectedRow + "],[" + selectedCol + "]");


                    selectedRow = InputHelper.getValidNumberInput(scanner, "Please indicate a square to move to. Row: ", 0, 7);
                    System.out.println("The selected piece is the " + selectedPiece 
                    + " at [" + selectedRow + "],[" + selectedCol + "]");
                    selectedCol = InputHelper.getValidNumberInput(scanner, "You have selected to move to row " + selectedRow + ".\nPlease select a column to move to: ", 0, 7);
                    if(selectedPiece.canMoveTo(selectedRow, selectedCol)) {
                        selectedPiece.doMove(selectedRow, selectedCol);
                        System.out.println("You have moved the " + selectedPiece + " to " +
                        "[" + selectedRow + "],[" + selectedCol + "]");
                        break;
                    } else {
                        System.out.println("That is an invalid move. Please try again.");
                        continue;
                    }
                }
                
                // swap to player 2 (black) 
                isWhiteTurn = !isWhiteTurn;
                
            } // end game loop

            displayBoard(true, true);
            System.out.println("Game Over!");
            String winner = "Black";
            String loser = "White";
            if(getNumKings(false) == 0) {
                winner = "White";
                loser = "Black";
            }
            System.out.println(String.format("%s has no more kings left. %s wins.", loser, winner));
            if(InputHelper.getValidInput(scanner, "Play Again? (Y)es or (N)o:", InputHelper.getValidYesNoInputs()).equals("N")) {
                shouldPlay = false;
            } else {
                initializeBoard();
                isWhiteTurn = true;
            }
        }
        System.out.println("Thanks for playing!");
        System.out.println();
    }

    public boolean hasWinner() {
        return getNumKings(true) == 0 || getNumKings(false) == 0;
    }

    private int getNumKings(boolean isWhite) {
        int count = 0;
        for(ChessPiece[] row : board) {
            for(ChessPiece piece : row) {
                if(piece != null && piece instanceof King) {
                    if(piece.isWhite == isWhite) {
                        count++;
                    }
                }
            }
        }
        return count;
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
