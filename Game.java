import java.util.ArrayList;

// Handles the flow/order of operations in a game of Yahtzee.
public class Game {

    // The maximum and minimum players allowed in a game.
    public static final int maxPlayers = 6;
    public static final int minPlayers = 1;

    // The number of players in a particular game.
    int numOfPlayers;

    // List of players in a particular game.
    ArrayList<Player> players = new ArrayList<Player>();

    // Tracks how many players have filled out their score sheets.

    // Creates a new game and the player objects.
    public Game(int numOfPlayers, ArrayList<String> names){
        this.numOfPlayers = numOfPlayers;


        // Randomly assigning player numbers here.
        ArrayList<Integer> playerOrder = new ArrayList<Integer>();
        for(int i = 1; i <= numOfPlayers; i++){
            playerOrder.add(i);
        }
        for(int i = 0; i < names.size(); i++){
            Player temp = new Player(names.get(i), playerOrder.remove((int)(Math.random() * playerOrder.size())));
            players.add(temp);
        }

        // Sorting players by turn order.
        for(int i = 0; i < players.size(); i ++){
            for(int j = i+1; j < players.size(); j ++){
                Player temp;
                if(players.get(j).getPlayerNum() < players.get(i).getPlayerNum()){
                    temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }

        // Printing out player orders.
        System.out.println("-------------------------------------");
        System.out.println("Randomly assigned player turn order:");
        for(int i = 0; i < players.size(); i++){
            System.out.println(players.get(i).getPlayerNum() + "  -  " + players.get(i).getName());
        }
        runGame();
    }

    // Manages the turn logic of the gameplay. Also determines when the game is over and which player has won
    void runGame(){
        int turnsRemaining = 13;
        while(turnsRemaining > 0){
            for(int i = 0; i < players.size(); i++){
                System.out.println("-------------------------------------");
                System.out.println(players.get(i).getName() + "'s turn!");
                players.get(i).getScoreSheet().printScoreSheet();
                //roll dice

                //decide how to score
            }
            turnsRemaining--;
        }

        // Sorting players by total score.
        for(int i = 0; i < players.size(); i ++){
            for(int j = i+1; j < players.size(); j ++){
                Player temp;
                if(players.get(j).getScoreSheet().getTotalScore() < players.get(i).getScoreSheet().getTotalScore()){
                    temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }

       // Print out the standings and announce with winner.
       for(int i = 1; i < players.size() + 1; i++){
            System.out.println("1. " + players.get(i-1).getName() + " [Score: " + players.get(i-1).getScoreSheet().getTotalScore() + "]");
       }
       System.out.println("-------------------------------------");
       System.out.println(players.get(0).getName() + " is the winner! Good game!");


    }


}
