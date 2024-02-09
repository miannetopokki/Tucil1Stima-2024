package tools;

public class Sequence {
    public int points,lengthEff,i;
    public String[] Seq;
    public boolean valid;

    public Sequence(int lengthEff,int points){
        this.points = points;
        this.lengthEff = lengthEff;
        this.Seq = new String[lengthEff];

    }

    public int getPoints(){
        return points;
    }
    public String getSeqToken(int i){
        return Seq[i];
    }
    public int getSeqlength()
    {
        return lengthEff;
    }


    public void setSeqToken(int i,String x){
        this.Seq[i] = x; 

    }
    public void displaySequence(){
        System.out.print(points + " , ");
        for (int i = 0; i < lengthEff; i++) {
            System.out.print(getSeqToken(i) + " ");
        }
    }



    

    
}
