
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        int minimalRaters = 3;
	    ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
	    }
        
    }
    
    public void getAverageRatingOneMovie(){
        
    }
}
