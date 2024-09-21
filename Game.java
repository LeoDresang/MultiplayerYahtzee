import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Handles the flow/order of operations in a game of Yahtzee.
public class Game {

    // The maximum and minimum players allowed in a game.
    public static final int maxPlayers = 6;
    public static final int minPlayers = 1;

    // The number of players in a particular game.
    int numOfPlayers;

    // List of players in a particular game.
    ArrayList<Player> players = new ArrayList<Player>();

    // The roll of a particular player on a particular turn.
    int[] roll = new int[]{0,0,0,0,0};

    // Scanner to read user input.
    Scanner input = new Scanner(System.in);

    // List of rows a player my select to score in.
    ArrayList<String> rowInput = new ArrayList<String>();

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
            for(int j = i+1; j < players.size(); j ++){
                Player temp;
                if(players.get(j).getScoreSheet().getTotalScore() < players.get(i).getScoreSheet().getTotalScore()){
                    temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }

        System.out.println("-------------------------------------");


       // Print out the standings and announce with winner.
       for(int i = 1; i < players.size() + 1; i++){
            System.out.println("1. " + players.get(i-1).getName() + " [Score: " + players.get(i-1).getScoreSheet().getTotalScore() + "]");
       }
       System.out.println("-------------------------------------");
       System.out.println(players.get(0).getName() + " is the winner! Good game!");


    }

    // Handles the logic for a player choosing how to score.
    private void Score(int playerNumber){
        Player player = players.get(playerNumber);
        ScoreSheet scoreSheet = player.getScoreSheet();

        System.out.println("-------------------------------------");

        System.out.println("Your current Score Sheet is printed above.");
        if((scoreSheet.getRows().get(11).getRowScore() > -1) && (getYahtzeeScore() == 50)){
            scoreSheet.addYB();
            System.out.println("You earned a BONUS YAHTZEE this turn (it will appear on your score sheet next turn)!");
        }
        System.out.println("In which row would you like to score? [Input the row name. Do not include any text in parentheses.]");

        // Add scoring system.
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

        if(rowToScore > -1){
            if(rowToScore == 0){
                scoreSheet.getRows().get(rowToScore).setRowScore(getAcesScore());
            }
            else if(rowToScore == 1){
                scoreSheet.getRows().get(rowToScore).setRowScore(getTwosScore());
            }
            else if(rowToScore == 2){
                scoreSheet.getRows().get(rowToScore).setRowScore(getThreesScore());
            }
            else if(rowToScore == 3){
                scoreSheet.getRows().get(rowToScore).setRowScore(getFoursScore());
            }
            else if(rowToScore == 4){
                scoreSheet.getRows().get(rowToScore).setRowScore(getFivesScore());
            }
            else if(rowToScore == 5){
                scoreSheet.getRows().get(rowToScore).setRowScore(getSixesScore());
            }
            else if(rowToScore == 6){
                scoreSheet.getRows().get(rowToScore).setRowScore(get3OfAKindScore());
            }
            else if(rowToScore == 7){
                scoreSheet.getRows().get(rowToScore).setRowScore(get4OfAKindScore());
            }
            else if(rowToScore == 8){
                scoreSheet.getRows().get(rowToScore).setRowScore(getFullHouseScore());
            }
            else if(rowToScore == 9){
                scoreSheet.getRows().get(rowToScore).setRowScore(getSmallStraightScore());
            }
            else if(rowToScore == 10){
                scoreSheet.getRows().get(rowToScore).setRowScore(getLargeStraightScore());
            }
            else if(rowToScore == 11){
                scoreSheet.getRows().get(rowToScore).setRowScore(getYahtzeeScore());
            }
            else if(rowToScore == 12){
                scoreSheet.getRows().get(rowToScore).setRowScore(getChanceScore());
            }

            System.out.println("Scored " + scoreSheet.getRows().get(rowToScore).getRowScore() + " in " + scoreSheet.getRows().get(rowToScore).getRowName() + "!");
            roll = new int[]{0,0,0,0,0};
        }
    }

    // Calculates and returns the Aces score of the rolled dice.
    private int getAcesScore(){
        int score = 0;
        for(int i = 0; i < 5; i++){
            if(roll[i] == 1){
                score += 1;
            }
        }
        return score;
    }

    // Calculates and returns the Twos score of the rolled dice.
    private int getTwosScore(){
        int score = 0;
        for(int i = 0; i < 5; i++){
            if(roll[i] == 2){
                score += 2;
            }
        }
        return score;
    }

    // Calculates and returns the Threes score of the rolled dice.
    private int getThreesScore(){
        int score = 0;
        for(int i = 0; i < 5; i++){
            if(roll[i] == 3){
                score += 3;
            }
        }
        return score;
    }

    // Calculates and returns the Fours score of the rolled dice.
    private int getFoursScore(){
        int score = 0;
        for(int i = 0; i < 5; i++){
            if(roll[i] == 4){
                score += 4;
            }
        }
        return score;
    }

    // Calculates and returns the Fives score of the rolled dice.
    private int getFivesScore(){
        int score = 0;
        for(int i = 0; i < 5; i++){
            if(roll[i] == 5){
                score += 5;
            }
        }
        return score;
    }

    // Calculates and returns the Sixes score of the rolled dice.
    private int getSixesScore(){
        int score = 0;
        for(int i = 0; i < 5; i++){
            if(roll[i] == 6){
                score += 6;
            }
        }
        return score;
    }

    // Calculates and returns the 3 Of a Kind score of the rolled dice.
    private int get3OfAKindScore(){
        int score = 0;
        int duplicatesNum;
        for(int i = 0; i < 5; i++){
            duplicatesNum = 0;
            for(int j = i; j < 5; j++){
                if(roll[i] == roll[j]){
                    duplicatesNum++;
                }
                if(duplicatesNum == 3){
                    for(int k = 0; k < 5; k++){
                        score += roll[k];
                    }
                    return score;
                }
            }
        }
        return score;
    }

    // Calculates and returns the 4 Of a Kind score of the rolled dice.
    private int get4OfAKindScore(){
        int score = 0;
        int duplicatesNum;
        for(int i = 0; i < 5; i++){
            duplicatesNum = 0;
            for(int j = i; j < 5; j++){
                if(roll[i] == roll[j]){
                    duplicatesNum++;
                }
                if(duplicatesNum == 4){
                    for(int k = 0; k < 5; k++){
                        score += roll[k];
                    }
                    return score;
                }
            }
        }
        return score;
    }

    // Calculates and returns the Full House score of the rolled dice.
    private int getFullHouseScore(){
        int[] rollSorted = roll;
        Arrays.sort(rollSorted);
        if(
            ((rollSorted[0] == rollSorted[1]) &&
            (rollSorted[0] == rollSorted[2]) &&
            (rollSorted[3] == rollSorted[4])) ||
            ((rollSorted[0] == rollSorted[1]) &&
            (rollSorted[2] == rollSorted[3]) &&
            (rollSorted[2] == rollSorted[4]))
            ){
                return 25;
            }
        return 0;
    }

    // Calculates and returns the Small Straight score of the rolled dice.
    private int getSmallStraightScore(){
        int ascendingLength = 0;
        for(int i = 1; i < 5; i++){
            if(ascendingLength < 3){
                if(roll[i] == (roll[i-1]+1)){
                    ascendingLength++;
                }
                else{
                    ascendingLength = 0;
                }
            }
        }

        if(ascendingLength >= 3){
            return 30;
        }
        else{
            return 0;
        }
    }

    // Calculates and returns the Large Straight score of the rolled dice.
    private int getLargeStraightScore(){
        int ascendingLength = 0;
        for(int i = 1; i < 5; i++){
            if(ascendingLength < 4){
                if(roll[i] == (roll[i-1]+1)){
                    ascendingLength++;
                }
                else{
                    ascendingLength = 0;
                }
            }
        }

        if(ascendingLength >= 4){
            return 40;
        }
        else{
            return 0;
        }
    }

    // Calculates and returns the YAHTZEE score of the rolled dice.
    private int getYahtzeeScore(){
        for(int i = 0; i < 5; i++){
            if(roll[i] != roll[0]){
                return 0;
            }
        }
        return 50;
    }

    // Calculates and returns the Chance score of the rolled dice.
    private int getChanceScore(){
        int score = 0;
        for(int i = 0; i < 5; i++){
            score += roll[i];
        }
        return score;
    }



    // Handles the logic for the rolls on a player's turn.
    private void rollDice(int rollNum){

        System.out.println("-------------------------------------");

        for(int i = 0; i < 5; i++){
            if(roll[i] == 0){
                roll[i] = Dice.RollDice(1)[0];
         }
        }

        System.out.print("Roll " + rollNum + ":       ");

         for(int j = 0; j < 5; j++){
            System.out.print(roll[j] + "   ");
        }
        System.out.println("");

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
                        roll[j] = 0;
                    }
                }

            System.out.println("-------------------------------------");
            System.out.print("Saved dice:   ");

            for(int j = 0; j < 5; j++){
                if (roll[j] != 0) {
                    System.out.print(roll[j] + "   ");
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
