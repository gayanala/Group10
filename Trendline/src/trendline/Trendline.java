
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
        File file = new File("What.csv");
        PrintWriter writer = new PrintWriter("LostWithBonus.txt", "UTF-8");
        double lowestPopularity = 0.0;
        double highestPopularity = 0.0;
        
        Trendline ShowTrend = new Trendline(file);
        writer.write(Trendline.Name+"\n");
        for(VideoEvent show: Trendline.streamingVideo){
            System.out.println(show.toString());
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
            //System.out.println(Arrays.toString(line));
            //Lines of data must be ordered Season#, Episode#, Show Name, Episode Name, Viewer Rank, Viewer Votes, Year
            String NumSeason = line[0];
            String NumEpisode = line[1];
            //the show's name is the third item
            Trendline.Name = line[2];
            String episodeName = line[3];
            String Rank = line[4];
            String NumVotes = line[5];
            String Year = line[6];
            //System.out.println(NumSeason+" "+ NumEpisode+" "+ episodeName+" "+ Rank+" "+ NumVotes+" "+ Year);
            //********************will update when more comes
            VideoEvent show = new VideoEvent(NumSeason, NumEpisode, episodeName, Rank, NumVotes, Year);
            
            Trendline.streamingVideo.add(show);
        }
        input.close();
    }
    
    
    
}
