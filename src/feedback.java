import java.util.ArrayList;

class review{
    private float rating;
    private String comment;
    review(float rating, String comment){
        this.rating=rating;
        this.comment=comment;
    }
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
public class feedback {
    ArrayList<review> idk;
    private float overall;
    private float temp;
    feedback(){
        idk = new ArrayList<review>();
    }
    public float getOverall() {
        return overall;
    }
    void add(review a ){
        idk.add(a);
        temp +=a.getRating();
        overall = temp/idk.size();
    }
}
