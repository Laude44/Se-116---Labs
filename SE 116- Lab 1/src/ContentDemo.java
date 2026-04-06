import java.util.ArrayList;

public class ContentDemo {
    ArrayList<Content> contents;

    public static void main(String[] args) {
        Movie movie1 = new Movie(0001,"Aleynanın Savaşı",120,false);
        Series serie1 = new Series(0002,"Aleynanın Barışı",130,20);

        movie1.displayInfo();
        serie1.displayInfo();
        System.out.println("Total duration : "+ movie1.calculateTotalDuration());
        System.out.println("Total duration : "+ serie1.calculateTotalDuration());
        System.out.println("Category: " + movie1.getRecommendationCategory());
        System.out.println("Category: " + serie1.getRecommendationCategory());

    }

}
