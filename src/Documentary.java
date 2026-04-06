public class Documentary extends Content{
    //Field
    private int researchDepthLevel;
    private int numberOfScientistInterviews;
    //Constructure
    public Documentary(int id, String title, int baseDuration, int researchDepthLevel, int numberOfScientistInterviews){
        super(id,title,baseDuration);
        this.numberOfScientistInterviews=numberOfScientistInterviews;
        this.researchDepthLevel=researchDepthLevel;
    }
    //Methods
    @Override
    public int calculateTotalDuration(){
        return getBaseDuration()+(numberOfScientistInterviews*10);
    }

    @Override
    public String getRecommendationCategory(){
        if(researchDepthLevel>=4){
            return "In-Depth";
        }
        return "Light Informative";
    }


}
