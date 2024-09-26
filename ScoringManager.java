import java.util.Arrays;

public class ScoringManager {

    int[] roll = new int[]{0,0,0,0,0};


    public ScoringManager(){

    }

    /**
     * Score in the correct row.
     * @param row the row the user input.
     */

    public void readScoreInput(int rowToScore, ScoreSheet scoreSheet, int[] diceRolled){
        roll = diceRolled;
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

    // Calculates if a Bonus YAHTZEE is to be awarded.
    public int getYBScore(int[] diceRolled){
        for(int i = 0; i < 5; i++){
            if(diceRolled[i] != diceRolled[0]){
                return 0;
            }
        }
        return 50;
    }


}
