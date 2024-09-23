/**
 * Class that stores the scoring information for a player; in other words, a score sheet.
 * Leo Dresang
 * 9/23/2024
 */

public class Player {

    // The player's name.
    String name;

    // The player's number in turn order.
    int playerNum;

    // The player's score sheet.
    ScoreSheet scoreSheet;

    // Constructs a new player.
    public Player(String name, int playerNum){
        this.name = name;
        this.playerNum = playerNum;
        scoreSheet = new ScoreSheet();
    }

    // Returns the player's score sheet.
    public ScoreSheet getScoreSheet(){
        return scoreSheet;
    }

    // Returns the player's name.
    public String getName(){
        return name;
    }

    // Returns the player's order number.
    public int getPlayerNum(){
        return playerNum;
    }


}
