package tools;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class parse {
    public static List<List<String>> raw_seq_list = new ArrayList<>();
    

    public static int readBufferSize(BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        int bufferSize = Integer.parseInt(line.trim());
        return bufferSize;
    }
    public static Matrix readMatrixSize(BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        String[] words = line.split("\\s+");
        Matrix matrix = new Matrix(Integer.parseInt(words[0]), Integer.parseInt(words[1]));
        return matrix;
    }
 
    public static void inputMatrixFromFile(BufferedReader reader,Matrix m) throws IOException {
        int row = m.getRowEff();
        for (int i = 0; i < row; i++) {
            String line = reader.readLine();
            String[] words = line.split("\\s+");
            
            int j = 0;
            for(String token : words){
                m.setElmt(i, j, token);
                j ++;
            }
        }
    }
    public static Sequence[] inputSequencesFromFile(BufferedReader reader) throws IOException {
        String line = reader.readLine(); // baca size sequence
        int sequenceSize = Integer.parseInt(line.trim()); 
        Sequence[] arrSeq = new Sequence[sequenceSize];
        for (int i = 0; i < sequenceSize; i++) {
            line = reader.readLine(); // baca isi sequence
            String[] words = line.split("\\s+");
            line = reader.readLine(); // baca point
            int points = Integer.parseInt(line.trim());
            Sequence sequen = new Sequence(words.length, points);

            // Set token Sequence
            for (int j = 0; j < words.length; j++) {
                sequen.setSeqToken(j, words[j]);
            }
            arrSeq[i] = sequen;        
        }

        return arrSeq;

    }
    public static void displayMatrix(Matrix m) {
        for (int i = 0; i < m.getRowEff(); i++) {
            for (int j = 0; j < m.getColEff(); j++) {
                System.out.print(m.getElmt(i, j) + " ");
            }
            System.out.println();
        }
    }
    public static void displaySequence(Sequence seq){
        System.out.print(seq.points + " , ");
        for (int i = 0; i < seq.lengthEff; i++) {
            System.out.print(seq.getSeqToken(i) + " ");
        }
    }
    public static void displaySequences(Sequence[] ses){
        for (int i = 0; i < ses.length; i++) {
            displaySequence(ses[i]);
            System.out.println();
            
        }
    }
    public class Result {
        public Matrix matrix;
        public Sequence[] arrSeq;
    
        public Result(Matrix matrix, Sequence[] arrSeq) {
            this.matrix = matrix;
            this.arrSeq = arrSeq;
        }
    }
    
    public static void inputTxt(Scanner scanner) {
        
        boolean isValidInput = false;
        
    
        do {
            String fileParent = "../test/";
            System.out.print("Masukkan sebuah string: ");
            String userInput = scanner.nextLine();
            String filePath = fileParent + userInput + ".txt";
    
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {


                int bufferSize = readBufferSize(br);
                Matrix matrix = parse.readMatrixSize(br); 
                inputMatrixFromFile(br, matrix);
                Sequence[] arrSeq = parse.inputSequencesFromFile(br);
                displayMatrix(matrix);
                test.generateSequences(matrix,bufferSize,raw_seq_list,arrSeq);
               
                
                displaySequences(arrSeq);
                
                // test.printSequences(raw_seq_list);
    
                
                isValidInput = true;
            } catch (FileNotFoundException e) {
                
                System.err.println("File tidak ditemukan: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!isValidInput);
    }
  
    
    
}
