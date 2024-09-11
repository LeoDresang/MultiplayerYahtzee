// Class which represents a single player in the game of Yahtzee.
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



}
