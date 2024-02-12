
package tools;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public static void writeToTextFile(Sequence[] arrSeq,long exeTime, Matrix matrix, Result result, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String resultString = resultToString(result);
            String matrixString = matrixToString(matrix);
            String arrSeqString = sequencesToString(arrSeq);
            String timeString = "\nExecute Time : " + String.valueOf(exeTime) + " ms"; 
            writer.write(matrixString);
            writer.write("========================================\n");
            writer.write(arrSeqString);
            writer.write("========================================\n");
            writer.write(resultString);
            writer.write(timeString);

            System.out.println("Output berhasil disimpan di." + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String resultToString(Result result) {
        StringBuilder sb = new StringBuilder();
        sb.append("Coords '[COL, ROW]' :  ").append(result.listCoords).append("\n");
        sb.append("Sequence: ").append(result.sequence).append("\n");
        sb.append("Total Points: ").append(result.totalPoints);
        return sb.toString();
    }

    private static String matrixToString(Matrix matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.getRowEff(); i++) {
            for (int j = 0; j < matrix.getColEff(); j++) {
                sb.append(matrix.getElmt(i, j)).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    private static String sequencesToString(Sequence[] arrSeq)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrSeq.length; i++) {
            sb.append(arrSeq[i].points).append(" Points | ");
            for (int j = 0; j < arrSeq[i].lengthEff; j++) {
                sb.append(arrSeq[i].getSeqToken(j)).append(" ");
            }
            sb.append("|\n");

        }
        return sb.toString();
    }
    
}
