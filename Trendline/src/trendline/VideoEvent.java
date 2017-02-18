/**
 * Class which takes given data for a Video and produces a way of generating useful measurements
 * @author Group10
 */
public class VideoEvent implements Comparable {
    private int NumAppeared;
    private String Identifier;
    private String Name;
    private String Rank;
    private String NumVotes;
    /**
     * Constructs on object of the VideoEvent Class
     * @param ApperanceOrder the location within a series
     * @param SeasonNumber the season number
     * @param EpisodeNumber the event's location within a season
     * @param Name the event's name
     * @param Rank viewer given rank (0.0-10.0)
     * @param Votes total number of viewer votes
     */
    public VideoEvent(int ApperanceOrder, String SeasonNumber, String EpisodeNumber, String Name, String Rank, String Votes){
        this.NumAppeared = ApperanceOrder;
        this.Identifier = "S"+SeasonNumber+":E"+EpisodeNumber;
        this.Name = Name;
        this.Rank = Rank;
        this.NumVotes = Votes;
        
    }
    /**
     * Provides access to the event's identifying locations
     * @return the identifying locations (i.e. S2:E5)
     */
    public String getIdentifier(){
        return new String(this.Identifier);
    }
    /**
     * Provides access to the event's viewer given rank
     * @return the viewer ranking as a double (within 0.0-10.0)
     */
    public double getRank(){
        return Double.parseDouble(this.Rank);
    }
    /**
     * Provides access to the event's total votes (the current representation of total views)
     * @return the total number of votes as an integer (scaled by a factor of 10^3)
     */
    public double getNumVotes(){
        return Integer.valueOf(this.NumVotes)/1000.0;
    }
    
    public String getName(){
        return new String(this.Name);
    }
    /**
     * Produces a measurement of total views using both viewer rank and total votes
     * @return a String representation of our Rank/Vote view measurement (scaled by a factor of 10^4)
     */
    public String getWatchedIndicator(){
        if(this.Rank.equals("")||this.NumVotes.equals("")){
            return "No given data";
        }
        return ""+(this.getRank()*this.getNumVotes())/10000.0;
    }
   
    @Override
    public String toString(){
        String print = this.NumAppeared+"\t";
        if(this.Name.equals("")){
            print += this.Identifier+" No Name Given\t";
        }
        else{
            print += "\""+this.Identifier+" "+this.Name+"\""+"\t";
        }
        print += this.Rank+"\t";
        
        return print;
    }

    @Override
    public int compareTo(Object o) {
        VideoEvent other = (VideoEvent)o;
        return (this.Identifier.compareTo(other.getIdentifier()));
    }
}
