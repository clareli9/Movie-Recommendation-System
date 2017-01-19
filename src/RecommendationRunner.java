
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    public RecommendationRunner () {
        
    }
    
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> movieToRate = new ArrayList<String>();
        // Create the filter list
        AllFilters list = new AllFilters();
        Filter f1 = new YearAfterFilter(2015);
        Filter f2 = new MinutesFilter(60, 100);
        list.addFilter(f1);
        list.addFilter(f2);
        movieToRate = MovieDatabase.filterBy(list);
        return movieToRate;
    }
    
    public void printRecommendationsFor (String webRaterID){
        FourthRatings frt = new FourthRatings();
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> target = frt.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        if (target.size() == 0 || target.size() == 1){
            System.out.println(target.size() + " movie matched");
        }
        else {
            System.out.println(target.size() + " movies matched");
        }
        /*
        for (int i = 0; i < target.size(); i++){
            if (i < 20){
                System.out.printf("%d %.2f %s\n", i, target.get(i).getValue(), MovieDatabase.getTitle(target.get(i).getItem()));
            }
        }*/
        printHtmlTable(target);
        
    }
    
    // Print the html table
    public void printHtmlTable(ArrayList<Rating> list){
        StringBuilder htmlTable = new StringBuilder("<html>\n");
        // CSS style begins
        htmlTable.append("<head>\n");
        htmlTable.append("<style>\n");
        // CSS style 1
        htmlTable.append("table, th, td { \n");
        htmlTable.append("   border: 1px solid black;\n");
        htmlTable.append("   border-collapse: collapse;  \n");
        htmlTable.append("} \n");
        // CSS style 2
        htmlTable.append("th, td { \n");
        htmlTable.append("    padding: 10px; \n");
        htmlTable.append("} \n");
        // CSS style 3
        htmlTable.append("table#alter tr:nth-child(even) { \n");
        htmlTable.append("    background-color: #eee; \n");
        htmlTable.append("} \n");
        // CSS style 4
        htmlTable.append("table#alter tr:nth-child(odd) { \n");
        htmlTable.append("    background-color: #fff; \n");  
        htmlTable.append("} \n");
        // CSS style 5
        htmlTable.append("table#alter th { \n");
        htmlTable.append("    color: white; \n"); 
        htmlTable.append("    background-color: gray;   \n");
        htmlTable.append("} \n");
        // CSS style ends
        htmlTable.append("</style>\n");
        htmlTable.append("</head>\n");
        
        // Html table
        htmlTable.append("<table>\n");
        htmlTable.append("<tr><th>Name</th><th>Genre</th><th>Minutes</th><th>Year</th></tr>\n");  
        
        for(int i = 0; i < 10 && i < list.size() ;i++) {  
            String id = list.get(i).getItem();
            
            htmlTable.append("<tr>");  
   
  
            htmlTable.append("<td>");  
            htmlTable.append(MovieDatabase.getTitle(id));  
            htmlTable.append("</td>"); 
            
            htmlTable.append("<td>");  
            htmlTable.append(MovieDatabase.getCountry(id));  
            htmlTable.append("</td>"); 
       
            
            htmlTable.append("<td>");  
            htmlTable.append(MovieDatabase.getMinutes(id));  
            htmlTable.append("</td>"); 
            
            htmlTable.append("<td>");  
            htmlTable.append(MovieDatabase.getYear(id));  
            htmlTable.append("</td>"); 
            
            htmlTable.append("</tr>\n");  
        }  
        htmlTable.append("</table></html>");  
        System.out.println(htmlTable.toString());
        
    }
}
