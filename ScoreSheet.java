import java.util.ArrayList;

/**
 * Class that stores the scoring information for a player; in other words, a score sheet.
 * Leo Dresang
 * 9/23/2024
 */

public class ScoreSheet {

    // Stores each row of the score sheet (includes the row's name and score).
    ArrayList<ScoreRow> rows = new ArrayList<ScoreRow>();

    // The total score of a given score sheet.
    int totalScore = 0;

    // The number of YAHTZEE BONUSES achieved on a given score sheet.
    int numOfYB = 0;

    // Creates a players score sheet and initializes all the rows of a score sheet.
    // Row scores are set to -1, which represents a row that a player has not yet assigned a score to.
    public ScoreSheet(){
        ScoreRow row = new ScoreRow("Aces (Ones)", -1);
        rows.add(row);

        row = new ScoreRow("Twos", -1);
        rows.add(row);

        row = new ScoreRow("Threes", -1);
        rows.add(row);

        row = new ScoreRow("Fours", -1);
        rows.add(row);

        row = new ScoreRow("Fives", -1);
        rows.add(row);

        row = new ScoreRow("Sixes", -1);
        rows.add(row);

        row = new ScoreRow("3 of a Kind", -1);
        rows.add(row);

        row = new ScoreRow("4 of a Kind", -1);
        rows.add(row);

        row = new ScoreRow("Full House", -1);
        rows.add(row);

        row = new ScoreRow("Small Straight", -1);
        rows.add(row);

        row = new ScoreRow("Large Straight", -1);
        rows.add(row);

        row = new ScoreRow("YAHTZEE (5 of a Kind)", -1);
        rows.add(row);

        row = new ScoreRow("Chance", -1);
        rows.add(row);

    }

    // Formats and prints out a this score sheet.
    public void printScoreSheet(){
        final Object[][] table = new String[rows.size() + 9][3];

        // Format the rows.
        for (int i = 0; i < rows.size()+5; i++){
            if(i == 0){
                table[i] = new String[] {"UPPER SECTION", "","SCORE"};
            }
            else if(i == 8){
                table[i] = new String[] {"", "", ""};
            }
            else if(i == 9){
                table[i] = new String[] {"LOWER SECTION", "", "SCORE"};
            }
            else if((i == 1) || (i == 10)){
                table[i] = new String[] {"---------------", "", "---------------"};
            }
            else{
                if(i < 10){
                    if(rows.get(i-2).getRowScore() == -1){
                        table[i] = new String[] {rows.get(i-2).getRowName(), "","Available"};
                    }
                    else{
                        table[i] = new String[] {rows.get(i-2).getRowName(), "","" + rows.get(i-2).getRowScore()};
                    }
                }
                else{
                    if(rows.get(i-5).getRowScore() == -1){
                        table[i] = new String[] {rows.get(i-5).getRowName(), "","Available"};
                    }
                    else{
                        table[i] = new String[] {rows.get(i-5).getRowName(), "", "" + rows.get(i-5).getRowScore()};
                    }
                }
            }
        }

        // Still formatting rows.
        table[rows.size() + 5] = new String[] {"", "", ""};
        table[rows.size() + 6] = new String[] {"---------------", "", "---------------"};
        ScoreRow row = new ScoreRow("YAHTZEE BONUS", 0);
        table[rows.size() + 7] = new String[] {row.getRowName(), "","" + row.getRowScore()};
        calculateTotal();
        row = new ScoreRow("[TOTAL SCORE]", totalScore);
        table[rows.size() + 8] = new String[] {row.getRowName(), "","" + row.getRowScore()};

        // Print out scoresheet.
        for (final Object[] roww : table) {
            System.out.format("%25s%3s%-15s%n", roww);
        }
    }

    /**
     * Returns if the sheet is completed.
     * @return boolean representing whether or not the scoresheet has been scored in all 13 rows.
     */
    public boolean sheetComplete(){
        for(int i = 0; i < rows.size(); i++){
            if(rows.get(i).getRowScore() == -1){
                return false;
            }
        }
        return true;
    }

    // Calculates and sets the total score.
    public void calculateTotal(){
        int total = 0;
        for(int i = 0; i < rows.size(); i++){
            if(rows.get(i).getRowScore() > 0){
                total += rows.get(i).getRowScore();
            }
        }
        int upperTotal = 0;
        for(int i = 0; i < 6; i++){
            upperTotal += rows.get(i).getRowScore();
        }
        if(upperTotal > 62){
            total += 35;
        }
        total += (numOfYB * 100);
        totalScore = total;
    }

    // Returns the total score.
    public int getTotalScore(){
        return totalScore;
    }

    // Returns the rows
    public ArrayList<ScoreRow> getRows(){
        return rows;
    }

    // Adds a YAHTZEE BONUS to a given score sheet.
    public void addYB(){
        numOfYB++;
    }


}
