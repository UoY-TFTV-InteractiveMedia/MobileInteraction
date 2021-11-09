package uk.ac.york.tftv.im.mi.practical7;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonPressed(View v) {

        if (v.getId() == R.id.savebutton) {
            //Get data from the edittext element
            String data = ((EditText) findViewById(R.id.editText)).getText().toString();

            //access the shared preferences file "my_data_file" (or whatever you want to call it). MODE_PRIVATE says we dont want to share this with other apps.
            SharedPreferences datafile = getSharedPreferences("my_data_file", MODE_PRIVATE);
            SharedPreferences.Editor editor = datafile.edit();
            //put the contents of the edittext in a record called "name".
            editor.putString("name", data);
            editor.commit();
            Snackbar.make(findViewById(R.id.savebutton), "This is a snackbar message!", Snackbar.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.loadbutton) {
            //retrieve data from the data file. Note we have to load the same file
            SharedPreferences datafile = getSharedPreferences("my_data_file", MODE_PRIVATE);
            //get the actual string. If the string does not exist, instead it will return the second string here
            String name = datafile.getString("name","");

            //change the contents of the textview
            ((TextView) findViewById(R.id.textView)).setText("Hello "+name+"!");

        }
    }
}
