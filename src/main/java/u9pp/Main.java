package u9pp;

import java.util.*;

import u9pp.MonsterFighter.MonsterFighterGame;
import u9pp.Chess.Chess;


public class Main 
{
    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);

        String prompt = "Welcome to u9pp :)\nAvailable games:\n"
            + "1: Monster Fighter\n"
            + "2: Chess\n"
            + "Select a game: ";
        int gameSelection = InputHelper.getValidNumberInput(scanner, prompt, 1, 2);

        if(gameSelection == 1) 
        {
            MonsterFighterGame game = new MonsterFighterGame();
            game.play(scanner);
        } 
        else if (gameSelection == 2) 
        {
            Chess chess = new Chess();
            chess.play(scanner);
        }
        
        scanner.close();
    }
}
