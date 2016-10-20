package uk.ac.york.tftv.im.mi.practical3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class ViewArtwork extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewartwork);
        if(getIntent().getIntExtra("image_num",1)==2) {
            ((ImageView)findViewById(R.id.artwork)).setImageResource(R.drawable.doginternet2);
        }
        else {
            ((ImageView)findViewById(R.id.artwork)).setImageResource(R.drawable.doginternet1);
        }
        ((DogInternetApplication) getApplication()).incrementCounter();
    }

    public void goBack(View v) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
