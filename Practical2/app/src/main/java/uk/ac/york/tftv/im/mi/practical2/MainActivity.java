package uk.ac.york.tftv.im.mi.practical2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String new_note = (String) getIntent().getStringExtra("note");
        if(new_note!=null) {
            if(((NoteApplication) this.getApplication()).notes==null) {
                ((NoteApplication) this.getApplication()).notes = new ArrayList<String>();
            }
            ((NoteApplication) this.getApplication()).notes.add(new_note);

            ListView lv = (ListView) findViewById(R.id.notesList);
            lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ((NoteApplication) this.getApplication()).notes));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void buttonPressed(View view) {
        Intent i = new Intent(this,CreateNote.class);
        startActivity(i);
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
