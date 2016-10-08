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
        String new_note = (String) getIntent().getStringExtra("note"); //Get the contents of the note from the Intent. Note this might be null if the Intent wasn't started by CreateNote...
        if(new_note!=null) { //So let's check. If this is null it probably means the app just started.
            if(((NoteApplication) this.getApplication()).notes==null) { //if the global list of strings hasn't been used yet, it needs setting up
                ((NoteApplication) this.getApplication()).notes = new ArrayList<String>(); //set it up.
            }
            ((NoteApplication) this.getApplication()).notes.add(new_note); //add our new note to the end of the array

            ListView lv = (ListView) findViewById(R.id.notesList);
            lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ((NoteApplication) this.getApplication()).notes)); //set the listview to draw items from our global list
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
