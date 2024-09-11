import java.util.ArrayList;

// Class that stores the scoring information for a player; in other words, a score sheet.
public class ScoreSheet {

    // Stores each row of the score sheet (includes the row's name and score).
    ArrayList<ScoreRow> rows = new ArrayList<ScoreRow>();

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

        row = new ScoreRow("BONUS YAHTZEE", 0);
        rows.add(row);
    }

    // Formats and prints out a this score sheet.
    public void printScoreSheet(){
        final Object[][] table = new String[rows.size() + 5][3];

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

        for (final Object[] row : table) {
            System.out.format("%25s%3s%-15s%n", row);
        }
    }


}
