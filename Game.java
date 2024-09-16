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
                if(players.get(j).playerNum < players.get(i).playerNum){
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
            System.out.println(players.get(i).playerNum + "  -  " + players.get(i).name);
        }
        



    }


}
