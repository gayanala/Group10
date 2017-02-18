


/**
 *
 * @author colinrichards
 */
public class VideoEvent {
    private String Identifier;
    private double ViewerRank;
    private int NumVotes;

    //private String AirDate;
    private double WatchedIndicator;
    
    public VideoEvent(String name, double score, int votes){
        this.Identifier = name;
        this.ViewerRank = score;
        this.NumVotes = votes;
        this.WatchedIndicator = 0.0;
    }
    
    public String getIdentifier(){
        return new String(this.Identifier);
    }
    
    public double getViewerRank(){
        return this.ViewerRank;
    }
    
    public int getNumVotes(){
        return this.NumVotes;
    }
    /*
    public String getAirDate(){
        return new String(this.AirDate);
    }
    */
    
   public double getWatchedIndicator(){
        //possible to change based on Stastical evidence of correlation 
        //(for now our only indicator is Viewer Ranking and Number of Votes)
        this.WatchedIndicator = (1.0*this.NumVotes)*this.ViewerRank;
        return this.WatchedIndicator;
    }
   
     public String toString(){
        String print = "";
        
        print+=this.getIdentifier()+" "+this.getViewerRank()+" "+this.getWatchedIndicator();
        
        return print;
    }
}
