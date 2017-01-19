
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings frs = new FirstRatings();
        myMovies = frs.loadMovies("data/" + moviefile);
        myRaters = frs.loadEfficientRaters("data/" + ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    //public ArrayList<>
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
        ArrayList<Rating> list = new ArrayList<Rating>();
        for (Movie mv : myMovies){
            if (getAverageByID(mv.getID(), minimalRaters) > 0){
                Rating rt = new Rating(mv.getID(), getAverageByID(mv.getID(), minimalRaters));
                list.add(rt);
            }
        }
        return list;
    }
    
    public String getTitle(String id){
        for (Movie mv : myMovies){
            if (mv.getID().equals(id)){
                return mv.getTitle();
            }
        }
        return "Not found";
    }
    
    public String getID(String title){
        for (Movie mv : myMovies){
            if (mv.getTitle().equals(title)){
                return mv.getID();
            }
        }
        return "NOT SUCH TITLE";
    }
    /*
    public void printAverageRatings(int minimalRaters){
        HashMap<double,String> table = new HashMap<double,String>();
        ArrayList
    }*/
}
