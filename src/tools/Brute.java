package tools;
import java.util.ArrayList;
import java.util.List;


public class Brute {
    public static int maxx = 0;
    public static ArrayList<Result>  resultList = new ArrayList<>();

    public static void generateSequences(Matrix matrix,int maxSteps,List<List<String>> sequences,Sequence[] prizeSeq) {
        sequences.clear();
        long startTime = System.currentTimeMillis();

        for (int startRow = 0; startRow < matrix.getRowEff(); startRow++) {
            List<int[]> currentPath = new ArrayList<>();
            int[] startPosition = {startRow, 0};
            currentPath.add(startPosition);
            explorePath(currentPath, startPosition, maxSteps - 1, true,maxSteps, matrix,sequences,prizeSeq);
        }
        long endTime = System.currentTimeMillis();
        long exeTime = endTime - startTime;

        System.out.println("=================FINAL RESULT========================");
        if (resultList.size() > 0)
        {
            
            Result maxResult = maxPointFromResult(resultList);
            maxResult.printResult();
        }
        else
        {
            System.out.println("No Solution");
        }
        System.out.println("Execution Time: " + exeTime + " ms");
        

        
    }

    private static void explorePath(List<int[]> currentPath, int[] currentPosition, int remainingSteps, boolean isVertical,int maxSteps,Matrix matrix,List<List<String>> sequences,Sequence[] prizeSeq) {
        if (remainingSteps == 0) {
            List<String> sequence = new ArrayList<>();
            List<List<Integer>> listCoord = new ArrayList<>();
            for (int[] position : currentPath) {
                List<Integer> coord = new ArrayList<>();
                int row = position[0];
                int col = position[1];
                sequence.add(matrix.getElmt(row, col));
                coord.add(col+1);
                coord.add(row+1);
                listCoord.add(coord);
                // System.out.println("debug1" + listCoord);
            }
          

            // hasil tiap sequence disini
            Sequence[] tempPrizeSeq = prizeSeq;
            
            int max = 0;
            for (int i = 0; i < prizeSeq.length; i++) {
                if(stringMatch(sequence, tempPrizeSeq[i]) == true){
                    max = max + tempPrizeSeq[i].getPoints();
                }
            }
            if (max > maxx){
                maxx = max;
                Result result = new Result(sequence.size(), maxx); 
                result.listCoords = listCoord;
                result.sequence = sequence;
                result.printResult();
                resultList.add(result);
               
            }
            sequences.add(sequence);
            return;
        }

        int row = currentPosition[0];
        int col = currentPosition[1];
        int[][] directions;

        if (isVertical) {
            directions = new int[][]{{1, 0}, {-1, 0}}; // vertikal
        } else {
            directions = new int[][]{{0, 1}, {0, -1}}; // horizontal
        }

        for (int[] direction : directions) {
            for (int step = 1; step <= maxSteps; step++) {
                int newRow = row + direction[0] * step;
                int newCol = col + direction[1] * step;
                int[] newPosition = {newRow, newCol};

                if (isValidPosition(newPosition,matrix) && !containsPosition(currentPath, newPosition)) {
                    List<int[]> newPath = new ArrayList<>(currentPath);
                    newPath.add(newPosition);

                    explorePath(newPath, newPosition, remainingSteps - 1, !isVertical,maxSteps,matrix,sequences,prizeSeq);
                }
            }
        }
    }

    private static boolean isValidPosition(int[] position,Matrix matrix) {
        int row = position[0];
        int col = position[1];
        return row >= 0 && row < matrix.getRowEff() && col >= 0 && col < matrix.getColEff();
    }

    private static boolean containsPosition(List<int[]> path, int[] position) {
        for (int[] p : path) {
            if (p[0] == position[0] && p[1] == position[1]) {
                return true;
            }
        }
        return false;
    }

    public static void printSequences(List<List<String>> sequences) {
        for (int i = 0; i < sequences.size(); i++) {
            System.out.printf("Sequence %d: %s%n", i + 1, sequences.get(i));
        }
    }
    public static boolean stringMatch(List<String> textArray, Sequence patternArray) {
        

        for (int i = 0; i <= textArray.size() - patternArray.Seq.length; i++) {
            int j = 0;

            while (j < patternArray.Seq.length && textArray.get(i + j).equals(patternArray.Seq[j])) {
                j++;
            }

            if (j == patternArray.Seq.length) {
                return true;
            }
        }
        return false;

        
    }
    public static Result maxPointFromResult(ArrayList<Result> resultList){
        int max = resultList.get(0).totalPoints;
        int idMax = 0;
        for (int i = 1; i < resultList.size(); i++) {
            if (resultList.get(i).totalPoints > max)
            {
                idMax = i;
            }
        }

        return resultList.get(idMax);
    }
    
}
