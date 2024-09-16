import java.util.Scanner;
import java.util.ArrayList;

// Leo Dresang

// The main method for running the game of Yahtzee.
public class Main {
    
    // Scanner to read user input.
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.println("Lets Play Yahtzee!\nHow many players will be playing (1-6)?");

        // The number of players in this game.
       int numOfPlayers = -1;

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

        ArrayList<String> names = new ArrayList<String>();

        System.out.println("-------------------------------------");
        System.out.println("Input the name of each player.");
        for (int i = 0; i < numOfPlayers; i++){
            System.out.print("Player " + (i+1) + ": ");
            String temp = input.nextLine();
            names.add(temp);
        }

        // Create a new game.
        Game game = new Game(numOfPlayers, names);

        
        
    }

}
