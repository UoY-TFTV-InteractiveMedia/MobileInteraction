package uk.ac.york.tftv.im.mi.practical4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CatShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_show);
    }

    /**
     * This method changes the textview to show the cat fact passed as a parameter.
     * @param cat_fact This variable contains the String of the fact, to be shown in the textview
     */
    public void setFact(String cat_fact) {
        TextView tv = (TextView) findViewById(R.id.catFact);
        tv.setText(cat_fact);
    }

    /**
     * This is the method attached to the button onClick property
     */
    public void getFact(View v) {
        String url = "https://catfact.ninja/fact";

        try {
            getDataFromServer(url); //Here we call the function that will connect to the server
        }
        catch (IOException e) { //The getDataFromServer function can "throw" an IOException if there is an error. We have to "catch" that here, otherwise our entire app will crash.
            setFact("Error connecting to server"); //put the error on screen
            return;
        }
    }

    /**
     * Method that uses OkHTTP to connect to the server
     * @param url the URL of the remote data source
     * @throws IOException
     */
    void getDataFromServer(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() { //This is an inner class that will be used to handle the response.

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException { //If the response is good...
                readJSONFact(response.body().string()); //send the JSON we got from the server to the readJSONFact function.
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) { //If there is an error in the response...
                e.printStackTrace(); //Print the error to the console
            }
        });
    }

    /**
     * Method that takes a string containing JSON, and extracts the Cat Fact.
     * @param rawJSON A string containing JSON encoded daa
     */
    void readJSONFact(String rawJSON) {
        runOnUiThread(new Runnable() { //This section has to happen on the same thread as the user interface.
            @Override
            public void run() {
                try {
                    JSONObject json = new JSONObject(rawJSON); //Convert the string into a JSONObject
                    String fact = json.getString("fact"); //Extract the fact from the property "fact", and put it in a string
                    setFact(fact); //call the method that will set the text on screen to show the fact
                }
                catch (JSONException e) { //Handle any issues where the JSON is badly formed or invalid
                    setFact("Invalid JSON text");
                }
            }
        });
    }


}
