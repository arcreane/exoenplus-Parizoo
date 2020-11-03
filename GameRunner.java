import java.util.Scanner;


public class GameRunner{
  
  //Displays rules at beginning of game.
  public static void init(){

  }
  
  public static void test(){
    Scanner scan = new Scanner(System.in);

    Game game = new Game();
    System.out.println("Enter a number of Mines");
    int userSelectedMines = scan.nextInt();
    game.generateMines(userSelectedMines);
    game.update();

    
    int x, y;
    System.out.print("Enter an x coordinate.");
    x = scan.nextInt();
    System.out.print("Enter a y coordinate.");
    y = scan.nextInt();
    
    /* 
     * To ensure that the player does not lose on their first move,
     * the game will move a mine to another tile if the player
     * happens to select a tile with a mine present.
     */ 
    if(game.getTile(x,y).equals(" * ") == true){
      game.generateMines(1);
      game.field[x][y] = " ? ";
    }
    
    game.clear(x,y);
    game.detect();
    game.update();
    
    //After first move, loops until the game ends.
    while(true){
      if(game.getDone() == true && game.getWin() == true){    //If the player wins.
        System.out.println("You win!");
        game.onEnd();
        break;
      }else if(game.getDone() == true){                       //If the player loses.
        game.onEnd(); 
        break;
      }else if(game.getDone() == false){                      //While the player hasn't lost or won.
        x = -1;
        y = -1;     //Resets values.
        System.out.print("Enter an x coordinate.");
        y = scan.nextInt();
        System.out.print("Enter a y coordinate.");
        x = scan.nextInt();
        game.turn(x,y);
        game.isVictory();
        game.detect();
        game.update();
      }
      
    }   
  }
}
