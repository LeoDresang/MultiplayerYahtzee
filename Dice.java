import java.util.Random;

/**
 * Class which handles the rolling of dice.
 * Leo Dresang
 * 9/23/2024
 */

public class Dice {

    // Random Object so random dice rolls can be made.
    Random rand = new Random();

    int[] roll = new int[]{0,0,0,0,0,0};


    // Rolls all dice that have not been chosen to keep.
    public void rollDice(int numOfDice){
        for(int i = 0; i < numOfDice; i++){
            if(roll[i] == 0){
                roll[i] = rand. nextInt(6) + 1;
            }
        }
    }

    // Returns the current roll of the dice.
    public int[] getRoll(){
        return roll;
    }

    // Resets dice to be all zeros.
    public void resetDice(){
        roll = new int[]{0,0,0,0,0,0};
    }

}
