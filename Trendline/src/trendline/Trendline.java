
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 *
 * @author colinrichards
 */
public class Trendline {
    //private double lowestPopularity;
    //private double highestPopularity;
    private static TreeSet<VideoEvent> streamingVideo;
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //************file name needs edit
        File file = new File("file.txt");
        PrintWriter writer = new PrintWriter(file+"Structured", "UTF-8");
        double lowestPopularity = 0.0;
        double highestPopularity = 0.0;
        
        Trendline ShowTrend = new Trendline(file);
        for(VideoEvent show: Trendline.streamingVideo){
            writer.write(show.toString());
        }
        
        
    }
    
    
    public Trendline(File FileName) throws FileNotFoundException{
        this.streamingVideo = new TreeSet();
        Scanner input = new Scanner(FileName);
        //First line in data file is the name of the show
        VideoSequence Season = new VideoSequence(input.nextLine().trim());
        while (input.hasNextLine()){
            String[] line = input.nextLine().split("");
            //Lines of data must be ordered Name(episode number), Rank, Votes************will be changed as more comes
            String Identifier = line[0];
            double ViewerRank = Double.parseDouble(line[1]);
            int NumVotes = Integer.valueOf(line[2]);
            //********************will update when more comes
            VideoEvent show = new VideoEvent(Identifier, ViewerRank, NumVotes);
            
            this.streamingVideo.add(show);
        }
    }
    
    
    
}
