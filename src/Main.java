
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tools.Matrix;
import tools.parse;
import tools.Sequence;
public class Main {
    
     public static void main(String[] args) {
        String filePath = "setting.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] buffer = parse.readBufferSize(br);
            Matrix matrix = parse.readMatrixSize(br);
            parse.inputMatrixFromFile(br,matrix);
            Sequence[] arrSeq = parse.inputSequencesFromFile(br);
            parse.displayMatrix(matrix);
            parse.displaySequences(arrSeq);
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
