import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Class which takes a file as input and produces useful measurements of 
 * popularity, expectations, and overall satisfaction as well as provide
 * warnings for unpopular or unsatisfying sections of VideoEvents
 * @author Group10
 */
public class Trendline {
    private static ArrayList<VideoEvent> streamingVideo;
    private static ArrayList<Double> expectationsChange;
    private static final int yieldAfterNumNegative = 5;
    private static final int avoidAfterNumNegative = 10;
    private static String Name;
    private static String FileName = "The Walking Dead";
 
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(Trendline.FileName+".txt");
        PrintWriter writer = new PrintWriter(Trendline.FileName+"WithBonus.txt", "UTF-8");
        Trendline ShowTrend = new Trendline(file);
        ArrayList<String> Locations = Trendline.getYield();
        //writer.write(Locations+"\n");
        
        ArrayList<Integer> badValues = Trendline.getBadValue(Locations);
        for(int i = 0; i<Trendline.streamingVideo.size(); i++){
            VideoEvent show = Trendline.streamingVideo.get(i);
            String print = show.toString() + Trendline.expectationsChange.get(i)+"\t" + show.getNumVotes()+"\t";
            if(badValues.contains(i+1)){
                print+=1+"\t";
            }
            else{
                print+=0+"\t";
            }
            Trendline.getRunningAverageVoting(i);
            writer.write(print+"\n");
        }
        writer.close();  
    }
    /**
     * Constructs an Object of the Trendline Class (old name with new ideas)
     * @param FileName the file to be read from
     * @throws FileNotFoundException 
     */
    public Trendline(File FileName) throws FileNotFoundException{
        Trendline.streamingVideo = new ArrayList();
        Trendline.expectationsChange = new ArrayList();
        Scanner input = new Scanner(FileName);
        Trendline.Name = "";
        int order = 0;
        while (input.hasNextLine()){
            order ++;
            
            String[] line = input.nextLine().split("\t");
            String NumSeason = line[0];
            String NumEpisode = line[1];
            Trendline.Name = line[2];
            String episodeName = line[3];
            String Rank = line[4];
            String NumVotes = line[5];
 
            VideoEvent show = new VideoEvent(order, NumSeason, NumEpisode, episodeName, Rank, NumVotes);
            Trendline.streamingVideo.add(show);
        }
        for(int i = 0; i<Trendline.streamingVideo.size(); i++){
            Trendline.expectationsChange.add(Trendline.getExpectations(i));
        }
        input.close();
    }
    /**
     * Produces a running average of the viewer ranking until a specified VideoEvent's index
     * @param ShowIndex the specified VideoEvent's index, the end of the run
     * @return the average calculated as a double
     */
    public static double getRunningAverageRating(int ShowIndex){
        double TotalRank = 0.0;
        for(int i = 0; i<ShowIndex; i++){
            VideoEvent show = Trendline.streamingVideo.get(i); 
            TotalRank += show.getRank(); 
        }
        TotalRank = (double)Math.round(TotalRank*10)/10.0;
        return TotalRank/(1.00*(ShowIndex));
    }
    /**
     * Produces a measure of viewers' expectations for a single VideoEvent based on the previous events
     * @param ShowIndex the index of the VideoEvent
     * @return the measure viewers' expectations
     */
    public static double getExpectations(int ShowIndex){
        double expectedRank = 0.0;
        expectedRank = Trendline.streamingVideo.get(ShowIndex).getRank()
                    - Trendline.getRunningAverageRating(ShowIndex);
        return expectedRank;
    }
    /**
     * Produces a running average of the total viewer votes until a specified VideoEvent's index
     * @param ShowIndex the specified VideoEvent's index
     * @return the calculated average
     */
    public static double getRunningAverageVoting(int ShowIndex){
       double TotalVotes = 0.0;
       for(int i = 0; i<ShowIndex; i++){
           VideoEvent show = Trendline.streamingVideo.get(i);  
           TotalVotes += show.getNumVotes();
       }
       return TotalVotes/(1.00*ShowIndex);  
    }
    /**
     * Produces a warnings if expectations are negative for a set threshold 
     * @return warnings in the form of a String
     */
    public static ArrayList<String> getYield(){
        int NumNegativeExpectations = 0;
        int index = 0;
        boolean endpoint = false;
        ArrayList<String> yieldInfo = new ArrayList();
        String StartNStop = "";
        while(index<Trendline.expectationsChange.size()){
            //System.out.println("EPISODE NUM IS:"+(index+1)+" and NumNegative is "+NumNegativeExpectations+" and my expecatation is: "+Trendline.expectationsChange.get(index));
            if(Trendline.expectationsChange.get(index)<0){
                NumNegativeExpectations++;
                if(NumNegativeExpectations == Trendline.yieldAfterNumNegative){
                    endpoint = true;
                    StartNStop += index-Trendline.yieldAfterNumNegative+2+"\t";
                }
                else if(NumNegativeExpectations == Trendline.avoidAfterNumNegative){
                    StartNStop += Trendline.expectationsChange.size();
                    yieldInfo.add(StartNStop);
                    return yieldInfo;
                }
            }
            else{
                if(endpoint){
                    StartNStop += (index);
                    yieldInfo.add(StartNStop);
                    StartNStop = "";
                    endpoint = false;
                }
                NumNegativeExpectations = 0;
            }
            index++;
        }
        //System.out.println(yieldInfo);
        return yieldInfo;
    }
    
    public static ArrayList<Integer> getBadValue(ArrayList<String> Locations){
        
        ArrayList<Integer> BadValues = new ArrayList();
        for(String StartNStop: Locations){
           String locations[] = StartNStop.split("\t");
           int Start = Integer.valueOf(locations[0]);
           int Stop = Integer.valueOf(locations[1]);
           while(Start<=Stop){
               BadValues.add(Start);
               Start++;
            }
        }
        return BadValues;
    }
    
    
}
