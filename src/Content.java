public abstract class Content {
    private int id;
    private String title;
    private int baseDuration;

    //Constructure
    public Content(int id, String title, int baseDuration) {
        this.id = id;
        this.title = title;
        this.baseDuration = baseDuration;
    }
        // methods
    public abstract int calculateTotalDuration();

    public abstract String getRecommendationCategory();


    public void displayInfo(){
        System.out.println("All the information id, title, base duration: "+id+" "+title+" "+baseDuration);
    }

        // getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getBaseDuration() {
        return baseDuration;
    }
}
