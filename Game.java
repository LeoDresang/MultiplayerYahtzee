import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles the flow/order of operations in a game of Yahtzee.
 * Leo Dresang
 * 9/23/2024
 */

public class Game {

    // The maximum and minimum players allowed in a game.
    public static final int maxPlayers = 6;
    public static final int minPlayers = 1;

    // The number of players in a particular game.
    int numOfPlayers;

    // List of players in a particular game.
    ArrayList<Player> players = new ArrayList<Player>();

    // The dice in a particular game.
    Dice dice = new Dice();

    // Scanner to read user input.
    Scanner input = new Scanner(System.in);

    // List of rows a player my select to score in.
    ArrayList<String> rowInput = new ArrayList<String>();

    // Manages calculating how to score.
    ScoringManager scoringManager = new ScoringManager();

    // Creates a >new game and the player objects.
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

        // Adding row choices to rowInput list.
        rowInput.add("aces");
        rowInput.add("twos");
        rowInput.add("threes");
        rowInput.add("fours");
        rowInput.add("fives");
        rowInput.add("sixes");
        rowInput.add("3ofakind");
        rowInput.add("4ofakind");
        rowInput.add("fullhouse");
        rowInput.add("smallstraight");
        rowInput.add("largestraight");
        rowInput.add("yahtzee");
        rowInput.add("chance");

        runGame();
    }

    // Manages the turn logic of the gameplay. Also determines when the game is over and which player has won
    void runGame(){
        int turnsRemaining = 13;
        while(turnsRemaining > 0){
            for(int i = 0; i < players.size(); i++){

                System.out.println("-------------------------------------");
                System.out.println(players.get(i).getName() + "'s turn!");

                // Roll dice.
                rollDice(1);

                System.out.println("-------------------------------------");

                players.get(i).getScoreSheet().printScoreSheet();

                Score(i);
            }
            turnsRemaining--;
        }

        // Sorting players by total score.
        for(int i = 0; i < players.size(); i ++){
            players.get(i).getScoreSheet().calculateTotal();
        }
        for(int i = 0; i < players.size(); i ++){
            for(int j = i+1; j < players.size(); j ++){
                Player temp;
                if(players.get(j).getScoreSheet().getTotalScore() > players.get(i).getScoreSheet().getTotalScore()){
                    temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }

        System.out.println("-------------------------------------");


       // Print out the standings and announce with winner.
       for(int i = 1; i < players.size() + 1; i++){
            System.out.println(i + ". " + players.get(i-1).getName() + " [Score: " + players.get(i-1).getScoreSheet().getTotalScore() + "]");
       }
       System.out.println("-------------------------------------");
       System.out.println("Above is the player standings! Good game!");


    }

    /**
     * Handles the logic for a player choosing how to score.
     * @param playerNumber the number of the player to score.
     */
    private void Score(int playerNumber){
        Player player = players.get(playerNumber);
        ScoreSheet scoreSheet = player.getScoreSheet();

        System.out.println("-------------------------------------");

        System.out.println("Your current Score Sheet is printed above.");
        if((scoreSheet.getRows().get(11).getRowScore() > -1) && (scoringManager.getYBScore(dice.getRoll()) == 50)){
            scoreSheet.addYB();
            System.out.println("You earned a BONUS YAHTZEE this turn (it will appear on your score sheet next turn)!");
        }
        System.out.println("In which row would you like to score? [Input the row name. Do not include any text in parentheses.]");

        int rowToScore = -1;
        boolean isValidInput = false;

        while(!isValidInput){

            String temp = input.nextLine();
            temp = temp.replaceAll("\\s+","");
            temp = temp.toLowerCase();
            if(!(temp.equals(""))){
                for(int i = 0; i < rowInput.size(); i++){
                    if(temp.equals(rowInput.get(i))){
                        if (scoreSheet.getRows().get(i).getRowScore() < 0) {
                            rowToScore = i;
                            isValidInput = true;
                        }
                    }
                }
            }
            if(rowToScore == -1){
                System.out.println("That row either doesn't exist or has already been scored in. Try again!");
            }
        }

        scoringManager.readScoreInput(rowToScore, scoreSheet, dice.getRoll());

        System.out.println("Scored " + scoreSheet.getRows().get(rowToScore).getRowScore() + " in " + scoreSheet.getRows().get(rowToScore).getRowName() + "!");
        dice.resetDice();
    }

    /**
     * Handles the logic for the rolls on a player's turn.
     * @param rollNum the roll number of a given player's turn (either 1, 2, or 3).
     */
    private void rollDice(int rollNum){

        System.out.println("-------------------------------------");

        dice.rollDice();

        System.out.print("Roll " + rollNum + ":       ");

         for(int j = 0; j < 5; j++){
            System.out.print(dice.getRoll()[j] + "   ");
        }
        System.out.println("");

        // Decide what to do based on if the player wants to reroll.
        if(rollNum < 3){
            System.out.println("Would you like to roll again? [Y/N]");
            String response = "";
            while(!response.equals("y") && !response.equals("n")){
                String temp = input.nextLine();
                temp = temp.toLowerCase();
                temp = temp.replaceAll("\\s+","");
                response = temp;
                
                if(!response.equals("y") && !response.equals("n")){
                    System.out.println("Invalid input. Try again!");
                }
            }

            if(response.equals("y")){

                // Select which dice to keep.
                System.out.println("Select which dice to reroll. [Input an _ to keep a die or an X to reroll a die. Ex: XX___ would reroll the first two die.]");
                boolean isValidReroll = false;
                String playerRerollList = "";


                while(!isValidReroll){

                    String temp = input.nextLine();
                    temp = temp.replaceAll("\\s+","");
                    temp = temp.toLowerCase();
                    if(!(temp.equals(""))){

                        playerRerollList = temp;

                        if(playerRerollList.length() == 5){
                            isValidReroll = true;
                            for(int j = 0; j < 5; j++){
                                if((!playerRerollList.substring(j, j+1).equals("x") && !playerRerollList.substring(j, j+1).equals("_"))){
                                    isValidReroll = false;
                                }
                            }
                            if(!isValidReroll){
                                System.out.println("Invalid input. Try again!");
                                isValidReroll = false;
                            }
                        }
                        else
                        {
                            System.out.println("Invalid input. Try again!");
                            isValidReroll = false;
                        }
                    }
                }

                // Only save dice that arent being rerolled.
                for(int j = 0; j < 5; j++){
                    if(playerRerollList.substring(j, j+1).equals("x")){
                        dice.getRoll()[j] = 0;
                    }
                }

            System.out.println("-------------------------------------");
            System.out.print("Saved dice:   ");

            for(int j = 0; j < 5; j++){
                if (dice.getRoll()[j] != 0) {
                    System.out.print(dice.getRoll()[j] + "   ");
                }
                else{
                    System.out.print("_   ");
                }
            }
                System.out.println("");
                rollDice(rollNum + 1);
            }
            else{
                return;
            }
        }
                
    }

}
