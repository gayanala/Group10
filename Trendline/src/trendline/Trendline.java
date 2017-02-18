
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;


/**
 *
 * @author Group10
 */
public class Trendline {
    private static ArrayList<VideoEvent> streamingVideo;
    private static String Name;
    private static String FileName = "How I met your mother";
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        
        File file = new File(Trendline.FileName+".csv");
        PrintWriter writer = new PrintWriter(Trendline.FileName+"WithBonus.txt", "UTF-8");
        Trendline ShowTrend = new Trendline(file);
        writer.write(Trendline.Name+"\n");
        
        for(int i = 0; i<Trendline.streamingVideo.size(); i++){
            VideoEvent show = Trendline.streamingVideo.get(i);
            String print = show.toString() + Trendline.getExpectations(i)+", " + show.getNumVotes();
            Trendline.getRunningAverageVoting(i);
            writer.write(print+"\n");
        }
        
        writer.close();
        
    }
    
    public Trendline(File FileName) throws FileNotFoundException{
        Trendline.streamingVideo = new ArrayList();
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
            String Year = line[6];
 
            VideoEvent show = new VideoEvent(order, NumSeason, NumEpisode, episodeName, Rank, NumVotes, Year);

            Trendline.streamingVideo.add(show);
        }
        input.close();
    }
    
    public static double getRunningAverageRating(int ShowIndex){
        double TotalRank = 0.0;
        
        for(int i = 0; i<ShowIndex; i++){
          
            VideoEvent show = Trendline.streamingVideo.get(i); 
            TotalRank += show.getRank();
           
        }
        TotalRank = (double)Math.round(TotalRank*10)/10.0;
        return TotalRank/(1.00*(ShowIndex));
    }
    
    public static double getExpectations(int ShowIndex){
        double expectedRank = 0.0;
        System.out.println("CurrentRank "+Trendline.streamingVideo.get(ShowIndex).getRank()
                    +" and running average is "+Trendline.getRunningAverageRating(ShowIndex));
        expectedRank = Trendline.streamingVideo.get(ShowIndex).getRank()
                    - Trendline.getRunningAverageRating(ShowIndex);
        
        
        return expectedRank;
    }
    
     public static double getRunningAverageVoting(int ShowIndex){
        double TotalVotes = 0.0;
        
        for(int i = 0; i<ShowIndex; i++){
            VideoEvent show = Trendline.streamingVideo.get(i);  
            TotalVotes += show.getNumVotes();
        }
        
        double rav = TotalVotes/(1.00*ShowIndex);
        return rav;  
    }

}
