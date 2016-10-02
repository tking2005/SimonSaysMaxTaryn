package nyc.c4q.tarynking.simonsaysmaxtaryn;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;



/**
 * Created by tarynking on 10/1/16.
 */

public class SimonMaxTarynActivity extends AppCompatActivity implements View.OnClickListener{

    public SimonMaxTarynActivity(){

    }

    private Button button_yellow;
    private Button button_red;
    private Button button_blue;
    private Button button_green;
    private Button button_start;
    private int numberOfRounds = 0;
    private Handler timer;
    ArrayList<Integer> simonSequence = new ArrayList<Integer>();
    ArrayList<String> playerSequence = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeButtons();
        enableButtons(false);
        initializeListeners();
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(true);
                button_start.setClickable(false);
                button_start.setText(String.valueOf(numberOfRounds));
            }
        });
    }

    public void startGame(boolean play){
        enableButtons(true);
        numberOfRounds = 1;
        int randomColor = chooseRandomColor();
        simonSequence.add(randomColor);

    }

    public int chooseRandomColor(){
        Random random = new Random();
        int randomColor = random.nextInt(4);
        return randomColor;
    }







    public void initializeButtons() {
        button_yellow = (Button) findViewById(R.id.button_yellow);
        button_green = (Button) findViewById(R.id.button_green);
        button_blue = (Button) findViewById(R.id.button_blue);
        button_red = (Button) findViewById(R.id.button_red);
        button_start = (Button) findViewById(R.id.button_start);
        //resetButtonColors();
    }

    public void colorButtons(){
        button_green.setBackgroundResource(R.color.green);
        button_red.setBackgroundResource(R.color.red);
        button_blue.setBackgroundResource(R.color.blue);
        button_yellow.setBackgroundResource(R.color.yellow);
    }

    public void enableButtons(boolean start){
        button_yellow.setEnabled(start);
        button_green.setEnabled(start);
        button_red.setEnabled(start);
        button_blue.setEnabled(start);
    }

    public void initializeListeners(){
        button_green.setOnClickListener(this);
        button_red.setOnClickListener(this);
        button_blue.setOnClickListener(this);
        button_yellow.setOnClickListener(this);
    }



    public void buttonYellowClicked() {
        Toast.makeText(SimonMaxTarynActivity.this, "Yellow was clicked!!", Toast.LENGTH_SHORT).show();
        button_yellow.setBackgroundResource(R.color.darkyellow);
        initializeButtons();
        playerSequence.add("Y");
    }

    public void buttonRedClicked() {
        Toast.makeText(SimonMaxTarynActivity.this, "Red was clicked!!", Toast.LENGTH_SHORT).show();
        button_red.setBackgroundResource(R.color.lightred);
        playerSequence.add("R");
    }

    public void buttonBlueClicked() {
        Toast.makeText(SimonMaxTarynActivity.this, "Blue was clicked!!", Toast.LENGTH_SHORT).show();
        button_blue.setBackgroundResource(R.color.lightblue);
        playerSequence.add("B");
    }

    public void buttonGreenClicked() {
        Toast.makeText(SimonMaxTarynActivity.this, "Green was clicked!!", Toast.LENGTH_SHORT).show();
        button_green.setBackgroundResource(R.color.lightgreen);
        playerSequence.add("G");
    }







    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_yellow:
                buttonYellowClicked();
                break;

            case R.id.button_red:
                buttonRedClicked();
                break;

            case R.id.button_blue:
                buttonBlueClicked();
                break;

            case R.id.button_green:
                buttonGreenClicked();
                break;

        }

    }
}
