
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    public FourthRatings() {
        
    }
    
    public double getAverageByID(String id, int minimalRaters){
        double count = 0;
        double sum = 0;
        EfficientRater temp;
        for (String rater : RaterDatabase.ourRaters.keySet()){
            temp = RaterDatabase.ourRaters.get(rater);
            if (temp.myRatings.containsKey(id)){
                sum += temp.myRatings.get(id).getValue();
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
    public ArrayList<Rating> getAverageRatings (int minimalRaters){
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
    
    // About the comparison
    private double dotProduct(Rater me, Rater r){
        double sum = 0;
        ArrayList<String> movies = me.getItemsRated();
        
        for (String mv : movies){
            if (r.hasRating(mv)){
                sum += (me.getRating(mv)-5)*(r.getRating(mv)-5);
            }
        }
        
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        double result = 0;
        for (Rater rt : RaterDatabase.getRaters()){
            if (!rt.equals(me)){
                result = dotProduct(me, rt);
                if (result > 0){
                    list.add(new Rating(rt.getID(), result));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    //public ArrayList<Rating> getRecommendations(String id, int numRaters){
        
    //}
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> candidates =  getSimilarities(id);
        ArrayList<Rating> target = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        double raterCount = 0;
        double ratingCount = 0;
        int i = 0;
        
        for (String mv : movies){
            while (i < numSimilarRaters && i < candidates.size()){
                Rating rt = candidates.get(i);
                Rater r = RaterDatabase.getRater(rt.getItem());
                if(r.hasRating(mv)){
                    raterCount++;
                    ratingCount += rt.getValue()*r.getRating(mv); 
                }
                i++;
            }
            
            if (raterCount >= minimalRaters){
                    target.add(new Rating(mv, ratingCount/raterCount));
            }
            i = 0;
            ratingCount = 0;
            raterCount = 0;
        }
        
        Collections.sort(target, Collections.reverseOrder());
        return target;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> candidates =  getSimilarities(id);
        ArrayList<Rating> target = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        double raterCount = 0;
        double ratingCount = 0;
        int i = 0;
        
        for (String mv : movies){
            while (i < numSimilarRaters){
                Rating rt = candidates.get(i);
                Rater r = RaterDatabase.getRater(rt.getItem());
                if(r.hasRating(mv)){
                    raterCount++;
                    ratingCount = rt.getValue()*r.getRating(mv); 
                }
            }
            
            if (raterCount >= minimalRaters){
                    target.add(new Rating(mv, ratingCount/raterCount));
            }
            i = 0;
            ratingCount = 0;
            raterCount = 0;
        }
        
        Collections.sort(target, Collections.reverseOrder());
        return target;
    }

}
