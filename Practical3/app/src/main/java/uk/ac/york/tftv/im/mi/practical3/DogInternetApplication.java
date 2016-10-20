package uk.ac.york.tftv.im.mi.practical3;

import android.app.Application;

public class DogInternetApplication extends Application {
    private int counter = 0;

    public void incrementCounter() {
        counter++;
    };

    public int getCounter() {
        return counter;
    }
}
