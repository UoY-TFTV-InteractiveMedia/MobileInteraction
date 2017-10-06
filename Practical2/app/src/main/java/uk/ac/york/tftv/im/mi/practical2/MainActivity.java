package uk.ac.york.tftv.im.mi.practical2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void buttonPressed(View view) {
        Intent i = new Intent(this, ShowLight.class);
        startActivity(i);
    }

}
