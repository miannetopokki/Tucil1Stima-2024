package tools;
import java.io.BufferedReader;
import java.io.IOException;

public class parse {
    String[] buffer;

    public static String[] readBufferSize(BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        int bufferSize = Integer.parseInt(line.trim());
        String[] buffer = new String[bufferSize];
        return buffer;
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
}
