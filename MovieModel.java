/**
 * Created by Nikhil Verma on 5/6/2016.
 */
public class MovieModel {
    int movieId;
    String title;
    String genres[];

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getGenres() {
        return genres;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = Integer.parseInt(movieId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
