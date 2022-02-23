# Unit 9 Programming Project
You will be creating a simplified version of the game of Chess, and some monsters for a hero to fight in a simple Role-playing game (RPG). 
You can make any extra methods/classes you want.
Your code must be DRY (don't repeat yourself!).
Given code will be poorly documented, to simulate it being written by a student who doesn't like javaDoc.  

# Monster Fighter
In the RPG Monster Fighter, a hero traverses a dungeon and fights a series of monsters. Most of the game has already been completed, but we still need monsters for the hero to fight!   
You will be implementing a `Monster` class, and a few child classes of `Monster` that have slightly more complex behavior.   
Start by browsing through the existing classes in the `MonsterFighter` folder. 


## Implement Monster.java
First, familiarize yourself with the `Combatant` class.  Make sure you can answer the following questions about it: 
* What are the instance variables?
* How do access and change these instance variables?
* Which of the methods in the class would be useful to me when I am creating a Monster?
* There is one method that is `private`. Why is this specific method private?

`Monster` is an abstract class, and a child class of `Combatant`.  
Monsters give a certain amount of experience when they die. The experience should be initialized from a parameter in the constructor.   
The `Monster` constructor should use the constructor of its parent class.   
`Monster`s should have a `getExpGiven()` method, which returns the amount of exp the monster gives when defeated.  
`Monster`s should have a `String takeTurn(Combatant target)` method. In this method, the monster should attack to the target, based on the monster's attackPower. This method should return a `String` that says what happened. (who attacked whom, with how much attackPower);

## Implement Other Monster Classes
`HealingMonster.java` and `SlowMonster.java` are child classes of `Monster`.  

`SlowMonster` works the same way as `Monster` except it only attacks on every other turn (it does not attack on the first turn).  
`HealingMonster` works as a nomal `Monster`, except that it heals a certain amount of health each turn. The health it heals each turn should be taken in as a parameter in its constuctor.  
`HealingMonster` should also have a `int getHealingPerTurn()` method.   

Both new classes should override `takeTurn()`. If there any any code that is shared by all 3 types of monster, you should put it in the `Monster` class. From the child classes, use `super.newMethod()` to access it.  

## Extra Credit
Implement new features, or additional monster types for extra credit.

### Additional Monster types
Implement additional enemies for extra credit.  
Here are some ideas, but feel free to make anything you can think of!  
Don't forget to change the `List<Monster>` in `MonsterFighterGame.java` to include your new monsters!  
* ShieldMonster - has a shield that negates the first attack it takes
* SleepingMonster - does nothing until it gets hit for the first time, then acts as normal monster. Might also take double damage on first hit.
* AngryMonster - gains atk everytime it takes a hit
* DesperateMonster - the lower its health, the more damage it deals
* MomentumMonster - When attacking, has a chance to miss. Every time it hits the target, doubles its damage. Missing resets damage. 
* MonsterGroup - A group of monsters.
  * has an ArrayList of `Monster`s. 
  * Each turn, all of them take a turn.
  * only take damage in order, from first to last
  * at most, only 1 monster takes damage each turn
  * when a monster cannot fight, it no longer takes turns
* StanceMonster - Switches stances periodically - works well as a boss. 
  * Retaliation stance - takes no damage, and reflects damage back to the player
  * Heal stance - if left uninterrupted, heals back to full in a couple turns
  * Attack Stance - attacks every turn
  * Heavy Attack stance - 'charges up' for a couple turns, then does a very high dmg attack
  * Resting stance - does nothing

## Other features
Implement any of these (or anything else you come up with) for extra credit
* Hero
  * new ability - heal (can also be buffed by focus)
  * new ability - dodge (negates all damage for one turn, can only be used once every 3 turns)
  * any other new ability
* Random events between monsters
* Potions (can hold at most 3, gain 1 for every defeated monster, using it heals half the hero's health)
* anything else you can think of




# Chess
You will be implementing a simplified version of chess. The rules are given in the sections below.  
If you are already familiar with chess, tldr: our simplified version of chess does not include check/checkmate, en passant, castling, and pawn promotion. Games end when one side has no kings, and we use 0-7 for row and column notation.    
The `Chess` class has multiple helper functions already made for you. Use these to implement the main game loop in `Chess.play()`.  
*Italicized* rules are different from normal chess.   

## General Rules
  * Piece cannot move out of bounds
  * piece cannot move onto a piece of the same color
  * piece moving onto a piece of the other color is a 'take', and removes that other piece from the game
  * *game is decided when one color does not have a king on board. Winner is the one with king(s) left*
  * pieces cannot move through other pieces. 
  * white pieces are on the bottom of the board, black pieces start at the top
  * White pieces are represented with capital letters, black pieces with lower case
  * *rows and columns are numbered 0 to 7*
  * White pieces start in rows 0 and 1. Black pieces start in rows 6 and 7. 

## Specific Piece Rules
* King
  * represented with a `K` or a `k`
  * can only move 1 square in any of the 8 directions
  * cannot move to a square next to another king
* Pawn: 
  * represented with a `P` or `p`
  * forward is defined as away from the side of the board where it started
  * can only move forward, and cannot take when doing so
  * can take by moving forward diagonally. Cannot do this unless taking
  * if the pawn has never taken a move, it can move 2 squares forward (cannot take with this move)
* Queen
  * represented with a `Q` or a `q`
  * can move in any of the 8 directions any number of squares
* Rook
 * represented with a `R` or a `r`
 * can move in any of the 4 orthogonal directions (right, left, up, down) any number of squares
* Bishop
  * represented with a `B` or a `b`
  * can move in any diagonal direction any number of squares
* Knight
  * represented with a `N` or an `n`
  * can jump over pieces 
  * can move 2 squares in any orthogonal direction, then one square at a 90 degree angle from the first move. See below for example.

```
+-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
| | | | | |   | | | | | |   |x| |x| |x|   | | |x| | |   |x| | | |x|
+-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
| |x|x|x| |   | |*|o|*| |   | |x|x|x| |   | | |x| | |   | |x| |x| |
+-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
| |x|K|x| |   | | |P| | |   |x|x|Q|x|x|   |x|x|R|x|x|   | | |B| | |
+-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
| |x|x|x| |   | | | | | |   | |x|x|x| |   | | |x| | |   | |x| |x| |
+-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
| | | | | |   | | | | | |   |x| |x| |x|   | | |x| | |   |x| | | |x|
+-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+

+-+-+-+-+-+
| |x| |x| |
+-+-+-+-+-+    x is a valid move
|x| | | |x|       
+-+-+-+-+-+    * is a valid move only when taking
| | |N| | |       
+-+-+-+-+-+    o is a valid move only when not taking
|x| | | |x|       
+-+-+-+-+-+    movement unaffected by pieces in unmarked squares
| |x| |x| |    
+-+-+-+-+-+  
```

## Implementing Chess
In the `Chess` class, you will implement the following methods: 
* `void play(Scanner scanner)` - the main game loop. Example output in `ChessExample.txt`
* `boolean hasWinner(ChessPiece[][] board)` - a `static` method that returns true if the board only has kings of 1 color. Returns false when the board has both colors of king present.

Implement a `ChessPiece.java`, all the other pieces will inherit from this abstract class.  
Any functionality that is shared between most of the types of pieces should be placed in this class.  
The class must include the following methods:   
* `public ChessPiece(ChessPiece[][] board, int row, int col, boolean isWhite)` self-explanatory
* `public boolean canMoveTo(int row, int col)` which returns true if the indicated space is a valid move for this piece, false otherwise. Does not alter the board. This may or may not be abstract.
* `public void doMove(int row, int col)` which actually moves the piece to the indicated space. This may or may not be abstract. 
* `public abstract String toString()` to be implemented by each child class, based on how they are to be represented
* `public boolean isWhite()` self-explanatory

Then, implement all the pieces as child classes of `ChessPiece`.  
If appropriate, each child class should override `canMoveTo()` and `doMove()`.  
Don't forget that you can simplify your code with good use of `super.canMoveTo()` and `super.doMove()`.  

### Recommended implementation order: 
1. Barebones class of each piece type, empty `Chess.play()`, just so that everything compiles
1. `ChessPiece.java` (until mvn package is success for all related tests)
1. `King`, so that you can begin testing your `play()` method from front to end
1. `Chess.play()` method.
1. play a couple times, moving your kings around
1. All the other pieces, extending `ChessPiece.java` as you see fit
  
## Starting board arrangement: 
```
   0 1 2 3 4 5 6 7 
  +-+-+-+-+-+-+-+-+
0 |r|n|b|q|k|b|n|r| 0
  +-+-+-+-+-+-+-+-+
1 |p|p|p|p|p|p|p|p| 1
  +-+-+-+-+-+-+-+-+
2 | | | | | | | | | 2
  +-+-+-+-+-+-+-+-+
3 | | | | | | | | | 3
  +-+-+-+-+-+-+-+-+
4 | | | | | | | | | 4
  +-+-+-+-+-+-+-+-+
5 | | | | | | | | | 5
  +-+-+-+-+-+-+-+-+
6 |P|P|P|P|P|P|P|P| 6
  +-+-+-+-+-+-+-+-+
7 |R|N|B|Q|K|B|N|R| 7
  +-+-+-+-+-+-+-+-+
   0 1 2 3 4 5 6 7 
```

## Skipped chess rules
Extra credit for each additional rule you implement, based on the complexity of the task.  
Sorted roughly from easiest to hardest.  
* castling
* en passant 
* pawn promotion
* check, checkmate & related rules
  * (Instead, our win condition is taking their king, which is much simpler to check)
  * Calculating check requires precomputing all possible moves for the opponent
  * Calculating checkmate requires precomputing all possible moves for all pieces, 2 turns into the future
  * king moving into check
  * Player must move out of check if possible


# Grading

- Attempted All Code: 15 points
- All Code is DRY: 15 points
- All Code Properly JavaDoc'ed: 20 points
- Passes All Test Cases: 40 points
- Manual testing of chess game loop: 10 points

