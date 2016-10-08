package uk.ac.york.tftv.im.mi.practical1;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random; //Challenge 1

public class HelloWorld extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
    }

    /** Called when the user touches the button */
    public void textChangeButton(View view) {
        Random rand = new Random(); //Challenge 2
        Calendar cal = Calendar.getInstance(); //Challenge 3
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        // find the textview control to change
        // Note - findViewById returns an Object that is "cast" as (TextView).
        TextView tv1 = (TextView)findViewById(R.id.changeText);
        // set visibility appropriately
        // View.INVISIBLE, and View.VISIBLE are static variables (actually ints). visibility is not a boolean.
        // You'll see this a lot. For example, Color.BLACK Calendar.HOUR

        if(tv1.getVisibility()==View.INVISIBLE) {//Challenge 1
            //Also note how we use .getVisibility() and .setVisibility(). In processing you might say tv1.visibility = true; but this has issues. For safety reasons it is common to see this kind of "getter" and "setter" used.
            tv1.setVisibility(View.VISIBLE);
            //On the next line we access a static method of the Color class to generate a Color object that setTextColor is expecting.
            tv1.setTextColor(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))); //Challenge 2
            //Here we change the text depending on the current hour, for Challenge 3. See how we use the setter to do it .setText()
            if(hour<12) tv1.setText("Good Morning");
            else if (hour<18) tv1.setText("Good Afternoon");
            else tv1.setText("Good Evening");
        }
        else tv1.setVisibility(View.INVISIBLE);//Challenge 1
    }

    /**
     * Called when the browser button is pressed for Challenge 4
     */
    public void browserButtonPressed(View view) {
        //Challenge 4 uses an Intent to launch a new browser activity
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tinyurl.com/2fcpre6"));
        startActivity(browserIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_world, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
