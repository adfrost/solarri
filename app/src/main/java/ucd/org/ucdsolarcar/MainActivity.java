package ucd.org.ucdsolarcar;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.os.Handler;
import java.lang.*;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView speed;
    private int up_speed = 0; // testing
    private int refresh_rate = 500; // time in milliseconds between refreshes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // maintains landscape mode
        setContentView(R.layout.activity_main); // sets view as activity_main for TextView retrievals


        speed = (TextView) findViewById(R.id.speedInt); // retrieves speedInt from XML
        Thread updateThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(refresh_rate); // sleeps thread for refresh_rate milliseconds
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                update(speed, up_speed); // call to update TextView
                                up_speed++; // testing
                            } // run()
                        }); // runOnUiThread
                    } // while
                } catch (InterruptedException e) {
                } // catches exception on interrupt but I don't know how to deal with it ¯\_(ツ)_/¯
            } // run()
        }; // updateThread thread
        updateThread.start();


    } // onCreate

    void update(TextView spd, int data) {
        // pull data from USB
        spd.setText("" + data); // pulled data from USB would be setText parameter as "data"
    } // update
} // class MainActivity
