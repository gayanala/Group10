
/**
 *
 * @author Group10
 */
public class VideoEvent implements Comparable {
    private String NumSeason;
    private String NumEpisode;
    private String Name;
    private String Rank;
    private String numVotes;
    private String Year;
    
    public VideoEvent(String SeasonNumber, String EpisodeNumber, String Name, String Rank, String Votes, String Year){
        this.NumSeason = SeasonNumber;
        this.NumEpisode = EpisodeNumber;
        this.Name = Name;
        this.Rank = Rank;
        this.numVotes = Votes;
        this.Year = Year;
    }
    
    public String getNumSeason(){
        return new String(this.NumSeason);
    }
    
    public String getNumEpisode(){
        return new String(this.NumEpisode);
    }
    
    public String getWatchedIndicator(){
        //possible to change based on Stastical evidence of correlation 
        //(for now our only indicator is Viewer Ranking and Number of Votes)
        if(this.Rank.equals("")||this.numVotes.equals("")){
            return "No given data";
        }
        return String.valueOf(Math.round(Double.parseDouble(this.Rank)*(1.0*Integer.valueOf(this.numVotes))));      
    }
   
    @Override
     public String toString(){
        String print = this.NumSeason+", "+this.NumEpisode+", ";
        if(this.Name.equals("")){
            print += "Episode, ";
        }
        else{
            print += this.Name+", ";
        }
        print += this.Rank+", "+this.getWatchedIndicator();
        
        return print;
    }

    @Override
    public int compareTo(Object o) {
        VideoEvent other = (VideoEvent)o;
        if(this.NumSeason.equals(other.getNumSeason())){
            return Integer.valueOf(this.NumEpisode)-Integer.valueOf(other.getNumEpisode());
        }
        return Integer.valueOf(this.NumSeason)-Integer.valueOf(other.getNumSeason());
    }
}
