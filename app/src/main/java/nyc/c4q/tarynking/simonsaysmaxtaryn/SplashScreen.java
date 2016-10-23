package nyc.c4q.tarynking.simonsaysmaxtaryn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tarynking on 10/5/16.
 */


public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        final Intent intent = new Intent(this, nyc.c4q.tarynking.simonsaysmaxtaryn.SimonMaxTarynActivity.class);
        final Handler handler = new Handler();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent intent = new Intent();
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}