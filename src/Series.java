public class Series extends Content{
    //field
    private int numberOfEpisodes;
    //constructure
    public Series(int id, String title, int baseDuration, int numberOfEpisodes){
        super(id, title, baseDuration);
        this.numberOfEpisodes=numberOfEpisodes;
    }

    //methods
    @Override
    public int calculateTotalDuration(){
        return getBaseDuration()*numberOfEpisodes;
    }

    @Override
    public String getRecommendationCategory(){
        if(numberOfEpisodes>=10){
            return "Binge Worthy";
        }
        return "Mini Series";
    }

}
