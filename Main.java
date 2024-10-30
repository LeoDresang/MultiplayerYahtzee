import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Main method for running the game of Yahtzee.
 * Leo Dresang
 * 9/23/2024
 */

public class Main {
    
    // Scanner to read user input.
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.println("Lets Play Yahtzee!\nHow many players will be playing? [1-6]");

        // The number of players in this game.
       int numOfPlayers = -1;

       // Receieve the number of players.
        while(numOfPlayers < Game.minPlayers || numOfPlayers > Game.maxPlayers){
            String temp = input.nextLine();
            temp = temp.replaceAll("\\s+","");
            temp = temp.replaceAll("[^\\d.]", "");
            if(!(temp.equals(""))){
                numOfPlayers = Integer.parseInt(temp);
            }

            if(numOfPlayers < Game.minPlayers || numOfPlayers > Game.maxPlayers){
                System.out.println("Invalid input. Try again!");
            }
        }

        // Receive the names of each player.
        ArrayList<String> names = new ArrayList<String>();
        System.out.println("-------------------------------------");
        System.out.println("Input the name of each player.");
        for (int i = 0; i < numOfPlayers; i++){
            System.out.print("Player " + (i+1) + ": ");
            String temp = input.nextLine();
            names.add(temp);
        }

        System.out.println("Would you like to play with 5 or 6 dice Yahtzee? [5 or 6]");

        // The number dice to be played with in this game.
       int numOfDice = -1;

       // Receieve the number of players.
        while(!(numOfDice == 5 || numOfDice == 6)){
            String temp = input.nextLine();
            temp = temp.replaceAll("\\s+","");
            temp = temp.replaceAll("[^\\d.]", "");
            if(!(temp.equals(""))){
                numOfDice = Integer.parseInt(temp);
            }

            if(!(numOfDice == 5 || numOfDice == 6)){
                System.out.println("Invalid input. Try again!");
            }
        }

        // Create a new game.
        new Game(numOfPlayers, names, numOfDice);

        
        
    }

}