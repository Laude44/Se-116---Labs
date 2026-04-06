public class Movie extends Content{
    //fields
    private boolean hasPostCreditsScene;
    // constructure
    public Movie(int id, String title, int baseDuration, boolean hasPostCreditsScene){
    super(id,title,baseDuration);
    this.hasPostCreditsScene=hasPostCreditsScene;
    }
    // methods
    @Override
    public int calculateTotalDuration(){
        if(hasPostCreditsScene){
            return getBaseDuration()+5;
        }
        return getBaseDuration();
    }
    @Override
    public String getRecommendationCategory(){
        if(getBaseDuration()<90){
            return "Short Watch";
        }
        return "Feature Length";
    }


}
