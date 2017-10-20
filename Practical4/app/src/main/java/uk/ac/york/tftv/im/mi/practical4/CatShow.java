package uk.ac.york.tftv.im.mi.practical4;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CatShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_show);
    }

    /**
     * This is the method attached to the button onClick property
     * @param v
     */
    public void getCat(View v) {
        new GetCatFact().execute("https://catfact.ninja/fact"); //starts thread to get cat fact
    }

    /**
     * This is the method called by the network thread when it has finished.
     * @param cat_fact This variable contains the String of the fact
     */
    public void setFact(String cat_fact) {
        TextView tv = (TextView) findViewById(R.id.catFact);
        tv.setText(cat_fact);
    }

    /**
     * This is a custom class that is acting as the thread that will handle our networking
     * Note it is private, so only CatShow can create it. Also note that it extends a basic threading class that gives it the important behaviour.
     */
    private class GetCatFact extends AsyncTask<String, Void, String> {
        /**
         * When we call "execute" in getCat(), this is the method that is called. Note that "String..." means it can accept a list of strings.
         * We need to do it this way, because this is how AsyncTask is wired.
         */
        @Override
        protected String doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]); //set the url to be the first parameter sent in the list of urls. See note above.

            } catch (MalformedURLException e) {
                //Java requires us to handle the error "MalformedURLException", in case we pass an invalid URL.
                //here we would show some kind of error, but let's just exit and return a blank string:
                return "";
            }

            StringBuilder sb = new StringBuilder();  //Stringbuilder is a helper class to build strings from remote sources (remember, we don't get all the data at once).

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //attempt to connect to the server
                connection.connect();
                BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream())); //a bufferedreader is used to read from the data stream. this line hooks it to the connection
                String line;//this will hold each line of data from the server.
                while ((line = bf.readLine()) != null)//while the connection still has data arriving
                {
                    sb.append(line + "\n"); //add the data to our growing stringbuilder
                }
                bf.close();//it has finished, so close the buffer...
                connection.getInputStream().close(); //and close the connection

                return(sb.toString());//return the entire contents of the stringbuilder as a string. (this will be a JSON formatted string containing the cat fact)

            } catch (IOException e) {
                //the above functions might raise an exception if there is an error communicating with the remote server (or there is a network issue)
                //as before, let's just exit and return a blank string again:
                return "";
            }
        }

        /**
         * onPostExecute displays the results of the AsyncTask. It is here we pass back the data to the main thread of the application.
         * the String "result" contains the cat fact we retrieved from the server in some JSON
         */
        @Override
        protected void onPostExecute(String result) {
            if(result.length()==0) {//remember we returned an empty string if there was an error. In that case lets return an error message.
                setFact("Error!");
                return;
            }

            //If there hasn't been an error, "result" now contains a string in JSON format. We only want the fact out of it.
            //Load the link in a web browser to see the kind of data we get: https://catfact.ninja/fact

            //We have to grab the fact out of the JSON.
            String fact;
            try {
                JSONObject json = new JSONObject(result);
                fact = json.getString("fact"); //the fact is within the property "fact"
            }
            catch(JSONException e) {
                fact = e.getLocalizedMessage(); //if there is an error in the JSON.
            }
            setFact(fact); //once the data has been collected, set the cat fact on the screen
        }
    }
}
