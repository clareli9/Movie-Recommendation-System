
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    private ArrayList<Movie> movieList;
    private ArrayList<EfficientRater> EfficientRaterList;
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            Movie mv = new Movie(record.get("id"), record.get("title"), record.get("year"),
            record.get("genre"), record.get("director"), record.get("country"),
            record.get("poster"), Integer.parseInt(record.get("minutes")));
            list.add(mv);
        }
        
        return list;
    }
    
    public void testLoadMovies() {
        /*
        movieList = loadMovies();
        for (Movie mv : movieList){
            System.out.println(mv);
        }*/
    }
    
    public ArrayList<EfficientRater> loadEfficientRaters(String filename){
        ArrayList<EfficientRater> list = new ArrayList<EfficientRater>();
        // Helper array to store the EfficientRater ids
        ArrayList<String> ids = new ArrayList<String>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            if (ids.contains(record.get("rater_id"))){
                
                for (EfficientRater rt : list){
                    if (rt.getID().equals(record.get("rater_id"))){
                        rt.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                        break;
                    }
                }
                
            }
            else{
                EfficientRater rt = new EfficientRater(record.get("rater_id"));
                rt.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                list.add(rt);
                ids.add(record.get("rater_id"));
            }
        }
        
        return list;
    }
    
    public void testLoadEfficientRaters() {
        /*
        EfficientRaterList = loadEfficientRaters();
        System.out.println(EfficientRaterList.size());*/
    }
}
