
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
   
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings frs = new FirstRatings();
        //myMovies = frs.loadMovies("data/" + moviefile);
        myRaters = frs.loadEfficientRaters("data/" + ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID (String id, int minimalRaters){
        double count = 0;
        double sum = 0;
        for (EfficientRater ert : myRaters){
            if(ert.myRatings.containsKey(id)){
                sum += ert.myRatings.get(id).getValue();
                count ++;
            }
        }
        
        if (count >= minimalRaters){
            return sum/count;
        }
        else {
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> list = new ArrayList<Rating>();
        for (String mv : movies){
            if (getAverageByID(mv, minimalRaters) > 0){
                Rating rt = new Rating(mv,getAverageByID(mv, minimalRaters));
                list.add(rt);
            }
        }
        return list;
    }
    
    // Helper function
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria){
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> list = new ArrayList<Rating>();
        for (String mv : movies){
            if (getAverageByID(mv, minimalRaters) > 0){
                Rating rt = new Rating(mv,getAverageByID(mv, minimalRaters));
                list.add(rt);
            }
        }
        return list;
    }

}

