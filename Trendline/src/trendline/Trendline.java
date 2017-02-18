
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;


/**
 *
 * @author colinrichards
 */
public class Trendline {
    //private double lowestPopularity;
    //private double highestPopularity;
    private static TreeSet<VideoEvent> streamingVideo;
    private static String Name;
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //************file name needs edit
        File file = new File("file.txt");
        PrintWriter writer = new PrintWriter("fileWithBonus.txt", "UTF-8");
        double lowestPopularity = 0.0;
        double highestPopularity = 0.0;
        
        Trendline ShowTrend = new Trendline(file);
        writer.write(Trendline.Name);
        for(VideoEvent show: Trendline.streamingVideo){
            writer.write(show.toString());
        }
        
        
    }
    
    
    public Trendline(File FileName) throws FileNotFoundException{
        Trendline.streamingVideo = new TreeSet();
        Scanner input = new Scanner(FileName);
        Trendline.Name = "";
        while (input.hasNextLine()){
            String[] line = input.nextLine().split(",");
            System.out.println(Arrays.toString(line));
            //Lines of data must be ordered Season#, Episode#, Show Name, Episode Name, Viewer Rank, Viewer Votes, Year
            String NumSeason = line[0];
            String NumEpisode = line[1];
            //the show's name is the third item
            Trendline.Name = line[2];
            String episodeName = line[3];
            String Rank = line[4];
            String NumVotes = line[5];
            String Year = line[6];
            //********************will update when more comes
            VideoEvent show = new VideoEvent(NumSeason, NumEpisode, episodeName, Rank, NumVotes, Year);
            
            Trendline.streamingVideo.add(show);
        }
    }
    
    
    
}
