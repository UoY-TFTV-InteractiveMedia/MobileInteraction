package uk.ac.york.tftv.im.mi.practical3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.textCounter);
        int counter = ((DogInternetApplication) getApplication()).getCounter();
        tv.setText(String.valueOf(counter));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showImage(View v) {
        Intent i = new Intent(this,ViewArtwork.class);
        if(v==findViewById(R.id.button1))//if it was the first button pressed
            i.putExtra("image_num",1);
        else
            i.putExtra("image_num",2);
        startActivity(i);
    }

    public void phoneTaxi(View v) {
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:01904656565"));
        startActivity(call);
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
