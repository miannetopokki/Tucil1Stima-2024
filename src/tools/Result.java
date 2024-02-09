package tools;

import java.util.ArrayList;
import java.util.List;

public class Result {
    public List<List<Integer>> listCoords;
    public List<String> sequence;
    public int i;
    public int totalPoints;

    

    public Result(int i, int totalPoints) {
        listCoords = new ArrayList<>(i);
        sequence = new ArrayList<>(i);
        this.totalPoints = totalPoints;
    }
    public void printResult() {
        System.out.println("Coords '[COL, ROW]' :  " + listCoords);
        System.out.println("Sequence: " + sequence);
        System.out.println("Total Points: " + totalPoints);
    }
}
