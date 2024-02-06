package tools;
public class Matrix {
    public int row, col;
    public String[][] Matrix;

    // Constructor Class Matriks
    public Matrix(int row, int col){
        this.row = row;
        this.col = col;
        this.Matrix = new String[row][col];
    }


    
    //Getter untuk Class Matrix
    public String getElmt(int i, int j){
        return this.Matrix[i][j];
    }
    public int getRowEff(){
        return this.row;
    }
    public int getColEff(){
        return this.col;
    }


    //Setter untuk class Matrix
    public void setElmt(int i, int j, String x){
        this.Matrix[i][j] = x;
    }
    
}