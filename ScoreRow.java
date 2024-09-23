/**
 * Class that stores the information about a specific row on a score sheet.
 * Leo Dresang
 * 9/23/2024
 */

public class ScoreRow {

    // The name of the row.
    String rowName;
    // The score in the row.
    int rowScore;
    
    // Creates a new score row, setting the name and score of the row.
    public ScoreRow(String rowName, int rowScore){
        this.rowName = rowName;
        this.rowScore = rowScore;
    }

    // Returns the row name.
    public String getRowName() {
        return rowName;
    }   

    // Returns the row score.
    public int getRowScore() {
        return rowScore;
    }

    // Sets the row score.
    public void setRowScore(int score){
        rowScore = score;
    }
}
