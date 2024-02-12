package tools;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

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
    
    public static void displaySequences(Sequence[] ses){
        System.out.println("============Sequences========");
        for (int i = 0; i < ses.length; i++) {
            ses[i].displaySequence();
            System.out.println();
            
        }
        System.out.println("=============================");
    }
    
    public static void inputCli(Scanner scanner){
        Random random = new Random();
        System.out.print("Masukkan jumlah_token_unik: ");
        int sumUniqueToken = scanner.nextInt();
        while (sumUniqueToken <= 0) {
            System.out.print("Tidak valid! tolong jumlahnya >= 0 : ");
            sumUniqueToken = scanner.nextInt();
        }
        scanner.nextLine(); 
        
        System.out.print("Masukkan token: ");
        String tokens = scanner.nextLine();
        String[] arrayOfTokens = tokens.split("\\s+");
        while (arrayOfTokens.length != sumUniqueToken) {
            System.out.print("Jumlah Tidak valid! Masukkan token kembali : ");
            tokens = scanner.nextLine();
            arrayOfTokens = tokens.split("\\s+");
            
        }

        // for (String token : arrayOfTokens) {
        //     System.out.println(token);
        // }
    
        System.out.print("ukuran_buffer: ");
        int bufferSize = scanner.nextInt();
        scanner.nextLine(); 
    
        System.out.print("ukuran_matriks: ");
        String matrixSize = scanner.nextLine();
        String[] words = matrixSize.split("\\s+");
        while (words.length != 2) {
            System.out.print("Input tidak valid! Ketik kembali ukuran matriks (M N): ");
            matrixSize = scanner.nextLine();
            words = matrixSize.split("\\s+");
        }
        Matrix matrix = new Matrix(Integer.parseInt(words[0]), Integer.parseInt(words[1]));
    
        System.out.print("jumlah_sekuens: ");
        int sequens = scanner.nextInt();
        scanner.nextLine(); 
    
        System.out.print("ukuran_maksimal_sekuens: ");
        int maxSizeSequens = scanner.nextInt();
        scanner.nextLine(); 

        //Pembuatan matriks random
        for (int i = 0; i < matrix.getRowEff(); i++) {
            for (int j = 0; j < matrix.getColEff(); j++) {
                int randomIndex = random.nextInt(sumUniqueToken);
                matrix.setElmt(i, j, arrayOfTokens[randomIndex]); 
            }
        }
        displayMatrix(matrix);


        //Pembuatan sequens random
        Sequence[] arrSeq = new Sequence[sequens];
        for (int i = 0; i < sequens; i++) {
            Sequence sequen = new Sequence(random.nextInt(1,maxSizeSequens), random.nextInt(30));// random points
            for (int j = 0; j < sequen.getSeqlength(); j++) {
                sequen.setSeqToken(j, arrayOfTokens[random.nextInt(sumUniqueToken)]);    
            }
            arrSeq[i] = sequen;
            
        }
        displaySequences(arrSeq);
        Brute.generateSequences(matrix,bufferSize,raw_seq_list,arrSeq);
    }
    
    public static void inputTxt(Scanner scanner) {
        
        boolean isValidInput = false;
        
    
        do {
            String fileParent = "../test/input/";
            System.out.print("Masukkan nama file (tanpa .txt): ");
            String userInput = scanner.nextLine();
            String filePath = fileParent + userInput + ".txt";
    
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {


                int bufferSize = readBufferSize(br);
                Matrix matrix = parse.readMatrixSize(br); 
                inputMatrixFromFile(br, matrix);
                Sequence[] arrSeq = parse.inputSequencesFromFile(br);
                displayMatrix(matrix);
                displaySequences(arrSeq);
                Brute.generateSequences(matrix,bufferSize,raw_seq_list,arrSeq);
                
                
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
