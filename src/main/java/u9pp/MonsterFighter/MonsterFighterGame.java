package u9pp.MonsterFighter;

import java.util.*;

import u9pp.InputHelper;

public class MonsterFighterGame {
    
    int monsterCounter = 0;
    List<Monster> allMonsters;
    
    public MonsterFighterGame() {
        initializeMonsters();
    }


    
    public void play(Scanner scanner) {
        clearScreen();
        printLogo();
        System.out.print("Welcome to Monster Fighter!\nWhat is your hero's name? >");
        String name = scanner.nextLine().trim();
        printDivider();
        
        Hero hero = new Hero(name, 10, 2);
        System.out.println(String.format("A newbie hero, %s, decided to brave the local dungeon, to make a name for themselves.", hero.getName()));
        System.out.println();
        
        boolean gameOver = false;
        Monster currMonster = getNextMonster();
        while(!gameOver) {
            // get next monster
            
            // it's battle time
            System.out.println(String.format("A %s appeared! Get ready to fight for your life!", currMonster.getName()));
            
            boolean battleOver = false;
            while(!battleOver) {
                
                // display statuses
                System.out.println();
                System.out.println("Status:");
                System.out.println(" "  + hero.getStatus());
                System.out.println(" " + currMonster.getStatus());
                System.out.println();
                
                // display options
                String temp = "It's your turn.\n";
                temp += "1: Attack\n";
                temp += "2: Defend\n";
                temp += "3: Focus\n";
                System.out.print(temp);
                
                // player picks attack option
                int moveIndex = InputHelper.getValidNumberInput(scanner, "What would you like to do? (1), (2), or (3)? >", 1, 3);
                printDivider();
                
                // do and print player move
                temp = null;
                if(moveIndex == 1) {
                    temp = hero.attack(currMonster);
                } else if (moveIndex == 2) {
                    temp = hero.defend();
                } else if (moveIndex == 3) {
                    temp = hero.focus();
                }
                if (temp != null) {
                    System.out.println(temp);
                }
                
                // do and print monster move
                System.out.println(currMonster.takeTurn(hero));
                
                // if the hero or monster can't fight anymore
                if(!hero.canFight() || !currMonster.canFight())
                {
                    // end fight
                    battleOver = true;
                    
                    if(!hero.canFight()) {
                        // you died
                        System.out.println(String.format("\nThe hero %s has died.\n\nRIP", name));
                        printDivider();
                        gameOver = true;
                    } 
                    else { // monster died
                        // good job! 
                        System.out.println();
                        System.out.println(String.format("The %s has been slain! You gained %s exp.", currMonster.getName(), currMonster.getExpGiven()));
                        boolean didLevelUp = hero.gainExp(currMonster.getExpGiven());
                        if(didLevelUp) {
                            System.out.println(String.format("%s leveled up to level %s!\nThey now have %s max health, and %s attack.\nThey have also been fully healed.", hero.getName(), hero.getLevel(), hero.getMaxHealth(), hero.getAttackPower()));
                        }
                        
                        // get nextMonster 
                        currMonster = getNextMonster();
                        // if we are out of monsters, you've won the game! :)
                        if(currMonster == null) {
                            System.out.println(String.format("Congrats %s! You cleared all the monsters!\nYou lived happily ever after.\n\n-THE END-", hero.getName()));
                            printDivider();
                            gameOver = true;
                            break;
                        }
                    }
                    
                    break;
                }
            } // end turn
            
            // if we ended battle with a gameover, we leave the game loop
            if(gameOver) {
                break;
            }
            
            // if game is not over, we ask them if they want to go onto next battle
            System.out.println();
            System.out.println(String.format("Status:\n %s", hero.getStatus()));
            if(InputHelper.getValidInput(scanner, "Would you like to continue? >", InputHelper.getValidYesNoInputs()).equals("N")) {
                printDivider();
                System.out.println("Thanks for playing!");
                break;
            }
            printDivider();
        } // end battle 
        
        
    }
    
    public void initializeMonsters() {
        allMonsters = new ArrayList<>(); 
        allMonsters.add(new Monster("Lizard", 5, 1, 1));
        // level 2 here // 10 health, 3 atk
        allMonsters.add(new Monster("Lizard", 5, 1, 1));
        allMonsters.add(new SlowMonster("Sloth", 15, 2, 1));
        // level 3 here // 20 health, 4 atk
        allMonsters.add(new SlowMonster("Big Sloth", 30, 6, 2));
        allMonsters.add(new Monster("Lizard", 5, 1, 1));
        // level 4 here // 30 health, 5 atk
        allMonsters.add(new SlowMonster("Sleepy Goblin Guard", 15, 8, 1));
        allMonsters.add(new SlowMonster("Surprised Goblin", 15, 8, 1));
        allMonsters.add(new Monster("Goblin", 15, 8, 2));
        // level 5 here // 50 health, 6 atk
        allMonsters.add(new Monster("Goblin Gang", 45, 8, 4));
        allMonsters.add(new Monster("Elderly Goblin", 8, 1, 1));
        // level 6 here // 60 health, 8 atk
        allMonsters.add(new Monster("Crying Goblin", 15, 0, 1));
        allMonsters.add(new HealingMonster("Blue Slime", 10, 5, 2, 1));
        allMonsters.add(new HealingMonster("Green Slime", 10, 5, 3, 5)); 
        // level 7 here // 70 health, 9 atk
        allMonsters.add(new HealingMonster("Red Slime", 10, 15, 4, 10));
        allMonsters.add(new HealingMonster("Grey Slime", 20, 5, 4, 20));
        // level 8 here // 80 health, 10 atk
        allMonsters.add(new SlowMonster("Golem", 50, 20, 100));
    }

    public Monster getNextMonster() {
        Monster currMonster = allMonsters.get(0);
        allMonsters.remove(0);
        return currMonster;
    }
    
    private void printDivider() {
        System.out.println();
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println();
        System.out.println();
    }
    
    public void clearScreen() {  
        System.out.print("\033[H\033[2J"); // special print character
        System.out.flush();  
    }  
    
    public void printLogo() {
        printDivider();
        System.out.println( 
        "_______  _______  _        _______ _________ _______  _______    _______ _________ _______          _________ _______  _______ \n" +
        "(       )(  ___  )( (    |(  ____ \\\\__   __/(  ____ \\(  ____ )  (  ____ \\\\__   __/(  ____ \\|\\     /|\\__   __/(  ____ \\(  ____ )\n" +
        "| () () || (   ) ||  \\  ( || (    \\/   ) (   | (    \\/| (    )|  | (    \\/   ) (   | (    \\/| )   ( |   ) (   | (    \\/| (    )|\n" +
        "| || || || |   | ||   \\ | || (_____    | |   | (__    | (____)|  | (__       | |   | |      | (___) |   | |   | (__    | (____)|\n" +
        "| |(_)| || |   | || (\\ \\) |(_____  )   | |   |  __)   |     __)  |  __)      | |   | | ____ |  ___  |   | |   |  __)   |     __)\n" +
        "| |   | || |   | || | \\   |      ) |   | |   | (      | (\\ (     | (         | |   | | \\_  )| (   ) |   | |   | (      | (\\ (   \n" +
        "| )   ( || (___) || )  \\  |/\\____) |   | |   | (____/\\| ) \\ \\__  | )      ___) (___| (___) || )   ( |   | |   | (____/\\| ) \\ \\__\n" +
        "|/     \\|(_______)|/    )_)\\_______)   )_(   (_______/|/   \\__/  |/       \\_______/(_______)|/     \\|   )_(   (_______/|/   \\__/");
        printDivider();
    }
}
