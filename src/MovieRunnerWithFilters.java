
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.util.Collections;
public class MovieRunnerWithFilters {
    public void printAverageRatings(int minimalRaters){
        // initialize
        ThirdRatings trs = new ThirdRatings("ratings_short.csv");
        ArrayList<Rating> ratinglist = new ArrayList<Rating>();
        ArrayList<Double> valuelist = new ArrayList<Double>();
        HashMap<String,Double> table = new HashMap<String,Double>();
        
        System.out.println(trs.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.ourMovies.size());
        ratinglist = trs.getAverageRatings(minimalRaters);
        for (Rating rt : ratinglist){
            valuelist.add(rt.getValue());
        }
        // Why no use??
        Collections.sort(valuelist);
        
        for (double v : valuelist){
       
            for (Rating rt : ratinglist){
                if (rt.getValue() == v){
                    table.put(rt.getItem(), v);
                    ratinglist.remove(rt);
                    break;
                }
                
            }
            
        }
        // Print
        for (String name : table.keySet()){
            System.out.println(table.get(name) + " " + name);
        }
    }
    
    public void printAverageRatingByYear(int minimalRaters, int year){
        ThirdRatings trs = new ThirdRatings("ratings_short.csv");
        
    }
}
