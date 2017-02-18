
/**
 *
 * @author Group10
 */
public class VideoEvent implements Comparable {
    private int NumAppeared;
    private String Identifier;
    private String Name;
    private String Rank;
    private String NumVotes;
    private String Year;
    
    public VideoEvent(int ApperanceOrder, String SeasonNumber, String EpisodeNumber, String Name, String Rank, String Votes, String Year){
        this.NumAppeared = ApperanceOrder;
        this.Identifier = "S"+SeasonNumber+":E"+EpisodeNumber;
        this.Name = Name;
        this.Rank = Rank;
        this.NumVotes = Votes;
        this.Year = Year;
        
    }
    
    public String getIdentifier(){
        return new String(this.Identifier);
    }
    
    public double getRank(){
        return Double.parseDouble(this.Rank);
    }
    
    public double getNumVotes(){
        return Integer.valueOf(this.NumVotes)/1000.0;
    }
    
    public String getWatchedIndicator(){
        //possible to change based on Stastical evidence of correlation 
        //(for now our only indicator is Viewer Ranking and Number of Votes)
        if(this.Rank.equals("")||this.NumVotes.equals("")){
            return "No given data";
        }
        return ""+(this.getRank()*this.getNumVotes())/10000.0;
    }
   
    @Override
     public String toString(){
        String print = this.NumAppeared+", ";
        if(this.Name.equals("")){
            print += this.Identifier+"No Name Given, ";
        }
        else{
            print += "'"+this.Identifier+" "+this.Name+"'"+", ";
        }
        print += this.Rank+", ";
        
        return print;
    }

    @Override
    public int compareTo(Object o) {
        VideoEvent other = (VideoEvent)o;
        return (this.Identifier.compareTo(other.getIdentifier()));
    }
}
