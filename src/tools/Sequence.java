package tools;

public class Sequence {
    public int points,lengthEff,i;
    public String[] Seq;

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


    public void setSeqToken(int i,String x){
        this.Seq[i] = x; 

    }


    

    
}
