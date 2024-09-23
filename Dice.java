import java.util.Random;

/**
 * Class which handles the rolling of dice.
 * Leo Dresang
 * 9/23/2024
 */

public class Dice {

    // Random Object so random dice rolls can be made.
    static Random rand = new Random();

    /**
     * Returns the result (in the form of an integer array) of rolling a specified number of dice.
     * @param numToRoll the number of dice to roll
     * @return integer array of the resulting rolled dice.
    */
    public static int[] RollDice(int numToRoll){
        int[] roll = new int[numToRoll];
        for(int i = numToRoll-1; i > -1; i--){
            roll[i] = rand. nextInt(6) + 1;
        }
        return roll;
    }

}
