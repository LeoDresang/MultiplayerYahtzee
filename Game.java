import java.util.ArrayList;

// Handles the flow/order of operations in a game of Yahtzee.
public class Game {

    // The maximum and minimum players allowed in a game.
    public static final int maxPlayers = 6;
    public static final int minPlayers = 1;

    // The number of players in a particular game.
    int numOfPlayers;

    // List of players in a particular game.
    ArrayList<Player> players;

    // Creates a new game and the player objects.
    public Game(int numOfPlayers, ArrayList<String> names){
        this.numOfPlayers = numOfPlayers;


    }


}
