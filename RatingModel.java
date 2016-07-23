/**
 * Created by Nikhil Verma on 5/7/2016.
 */
public class RatingModel {
    int userID, movieID;
    float rating;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = Integer.parseInt(movieID);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = Integer.parseInt(userID);
    }

    public float getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = Float.parseFloat(rating);
    }
}
