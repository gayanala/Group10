
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.TreeSet;


/**
 *
 * @author Group10
 */
public class Trendline {
    private static TreeSet<VideoEvent> streamingVideo;
    private static String Name;
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //************file name needs edit
        File file = new File("What.csv");
        PrintWriter writer = new PrintWriter("LostWithBonus.txt", "UTF-8");
        double lowestPopularity = 0.0;
        double highestPopularity = 0.0;
        
        Trendline ShowTrend = new Trendline(file);
        writer.write(Trendline.Name+"\n");
        for(VideoEvent show: Trendline.streamingVideo){
            String print = show.toString();
            writer.write(print+"\n");
        }
        writer.close();
        
    }
    
    
    public Trendline(File FileName) throws FileNotFoundException{
        Trendline.streamingVideo = new TreeSet();
        Scanner input = new Scanner(FileName);
        Trendline.Name = "";
        while (input.hasNextLine()){
            String[] line = input.nextLine().split("\t");
            
            String NumSeason = line[0];
            String NumEpisode = line[1];
            //the show's name is the third item
            Trendline.Name = line[2];
            String episodeName = line[3];
            String Rank = line[4];
            String NumVotes = line[5];
            String Year = line[6];
 
            VideoEvent show = new VideoEvent(NumSeason, NumEpisode, episodeName, Rank, NumVotes, Year);

            Trendline.streamingVideo.add(show);
        }
        input.close();
    }
    
    
    
}
