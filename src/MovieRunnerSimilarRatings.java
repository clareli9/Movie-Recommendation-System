
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    // Similar to that in thirdRatings
    public void printAverageRatings() {
        RaterDatabase.initialize("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr = new FourthRatings();
	    int minimalRaters = 1;
	    ArrayList<Rating> ratings = fr.getAverageRatings(minimalRaters);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
	    }
	    
    }
    // New methods
    public void printSimilarRatings() {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings fr = new FourthRatings();
        String raterid = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> target = fr.getSimilarRatings(raterid, numSimilarRaters, minimalRaters);
        if (target.size() == 0 || target.size() == 1){
            System.out.println(target.size() + " movie matched");
        }
        else {
            System.out.println(target.size() + " movies matched");
        }
        for (int i = 0; i < target.size(); i++){
            if (i < 20){
                System.out.printf("%d %.2f %s\n", i, target.get(i).getValue(), MovieDatabase.getTitle(target.get(i).getItem()));
            }
        }
    }
}
