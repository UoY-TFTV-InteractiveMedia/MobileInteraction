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

    ArrayList<String> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(notes==null) { //if the application has just started, reset the list of notes.
            notes = new ArrayList<String>();
            ListView lv = (ListView) findViewById(R.id.notesList); //set the ListView to look at our ArrayList of notes.
            lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes));
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
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK) {
            String new_note = data.getStringExtra("note");
            notes.add(new_note); //add note to the list of notes
            ((ArrayAdapter<String>)((ListView) findViewById(R.id.notesList)).getAdapter()).notifyDataSetChanged(); //tell the ListView to update contents.
        }
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
