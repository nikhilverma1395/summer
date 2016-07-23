/**
 * Created by Nikhil Verma on 5/6/2016.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadCVS {
    private static final float RATING_THRESHOLD = 4.5f;
    private static int USER = 2;
    private static final int USERS_FINAL = 668;
    String movieCSV = "G:\\ml-latest-small\\movies.csv";
    String ratingCSV = "G:\\ml-latest-small\\ratings.csv";
    String SPLIT_PARAM = ",";
    List<MovieModel> movies = new ArrayList<>();
    List<RatingModel> ratings = new ArrayList<>();
    HashMap<Integer, Float> mapDis = new HashMap<>();
    HashMap<Integer, Float> ourUser = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter value of user between 1 to 668 ");
        int r = 0;
        while ((r = scanner.nextInt()) > 668) {
            System.out.println("Only enter value between 1 to 668 ");
            continue;
        }
        USER = r;
        ReadCVS obj = new ReadCVS();
        obj.run();

    }

    public void run() {


        Thread[] thread = new Thread[2];
        thread[0] = new Thread(new Runnable() {
            @Override
            public void run() {
                parseMovie();
            }
        });
        thread[0].start();
        thread[1] = new Thread(new Runnable() {
            @Override
            public void run() {
                parseRatings();
            }
        });
        thread[1].start();
        for (Thread th : thread) {
            try {
                th.join();//block the program
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        recommend();
        //   Print userId, Dis-agreement
        for (Map.Entry<Integer, Float> uyp : mapDis.entrySet()) {
            System.out.println("User Id" + uyp.getKey() + "\t Disagreement" + uyp.getValue());
        }
        Object[] a = mapDis.entrySet().toArray();//cast to array
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<Integer, Float>) o2).getValue().compareTo(
                        ((Map.Entry<Integer, Float>) o1).getValue());
            }
        });//Sorting code on basis of disagreement

        HashMap<Integer, Float> rec = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            Object e = a[i];//each object
            if ((a.length - i) < 4)//For first 3 neighbours
            {
                //   System.out.println(((Map.Entry<Integer, Integer>) e).getKey() + "\t" + ((Map.Entry<Integer, Integer>) e).getValue());

                int uid = ((Map.Entry<Integer, Float>) e).getKey();

                for (int i2 = 0; i2 < ratings.size(); i2++) {//iterate over ratings
                    if (ratings.get(i2).getUserID() == uid) {//ratings match on user
                        for (Map.Entry<Integer, Float> entry : ourUser.entrySet()) {//iterate over our users data
                            if (ratings.get(i2).getMovieID() != entry.getKey()) {//not the same movies
                                if (ratings.get(i2).getRating() > RATING_THRESHOLD)//value greter than threshold
                                    rec.put(ratings.get(i2).getMovieID(), ratings.get(i2).getRating());//put data in map
                            }

                        }
                    }

                }
            }
        }
        printMovies(rec);
    }

    private void printMovies(HashMap<Integer, Float> rec) {

        System.out.println("Recommended Movies are as :");
        for (MovieModel mv : movies)//iterate over movie CSV file
            for (Map.Entry<Integer, Float> entry : rec.entrySet()) {
                if (entry.getKey().equals(mv.getMovieId()))//match movie ID
                    System.out.println(mv.getTitle());
            }
    }

    private void recommend() {
        for (int i = 0; i < ratings.size(); i++) {//iterate over 100k ratings
            if (ratings.get(i).getUserID() == USER)//if it matches with our user
                ourUser.put(ratings.get(i).getMovieID(), ratings.get(i).getRating());//put movieID,rating in HashMap
        }
        for (int u = 1; u <= USERS_FINAL; u++) {//Browse over 668 users
            int ctr = 0;
            float dis = 100.0f;
            for (int i = 0; i < ratings.size(); i++) {//then over all 100k ratings
                if (u != USER)//not our users, as disagreement for other users
                    if (u == ratings.get(i).getUserID()) {//id in rating csv matches user
                        int mid = ratings.get(i).getMovieID();//get movie id
                        float rat = ratings.get(i).getRating();//get rating
                        if (ourUser.containsKey(mid))//check if our users has seen this movie
                        {
                            // if (u == 24 || u == 25 || u == 27) {
                            //   System.out.println(u + "\t" + mid + "\t" + rat + "\t" + ourUser.get(mid));
                            //}
                            ctr++;
                            dis += Math.abs(rat - ourUser.get(mid));//add disagreement as ABS(ratingByElse- ratingByOurUser)
                        }
                    }

            }
            //As if only 1 matches and then disagreement is 0, so average doesn't play a part in that
            // case, So initial value of dis be something, that way we can get the ratios
            if (ctr != 0.0f)
                mapDis.put(u, dis / ctr);//put data as userid,disagreement in HashMap
            else mapDis.put(u, Float.MAX_VALUE);//put disagreement as max value when no match is found


        }

    }

    private void parseRatings() {
        String line = "";

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(ratingCSV));//read buffered reader file from i/p
            int i = 0;
            while ((line = br.readLine()) != null) {//get each input line in String line
                // use comma as separator
                if (++i == 1) continue;//don't continue for 1st line as they have titles
                String[] data = line.split(SPLIT_PARAM);
                RatingModel ratingModel = new RatingModel();//model of array
                ratingModel.setRating(data[2]);
                ratingModel.setMovieID(data[1]);
                ratingModel.setUserID(data[0]);
                ratings.add(ratingModel);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private void parseMovie() {
        String line = "";

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(movieCSV));//read buffered reader file from i/p
            int i = 0;
            while ((line = br.readLine()) != null) {//get each input line in String line
                // use comma as separator
                if (++i == 1) continue;//don't continue for 1st line as they have titles
                String[] data = line.split(SPLIT_PARAM);//split on basis of ,
                MovieModel movieModel = new MovieModel();//model of array
                movieModel.setMovieId(data[0]);
                //http://stackoverflow.com/questions/9808689/why-does-string-split-need-pipe-delimiter-to-be-escaped
                if (data[2].contains("\"")) { // If movie has illegal format as "The, Revenant" i.e more than 3 comma

                    movieModel.setTitle(data[1] + data[2]);
                    movieModel.setTitle(movieModel.getTitle().replace("\""," "));
                    movieModel.setGenres(data[3].split("\\|"));
                } else {
                    movieModel.setTitle(data[1]);
                    movieModel.setGenres(data[2].split("\\|"));
                }

                movies.add(movieModel);//add model in List
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
