package nyc.c4q.tarynking.simonsaysmaxtaryn;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static nyc.c4q.tarynking.simonsaysmaxtaryn.SimonMaxTarynActivity.SimonColors.BLUE;
import static nyc.c4q.tarynking.simonsaysmaxtaryn.SimonMaxTarynActivity.SimonColors.GREEN;
import static nyc.c4q.tarynking.simonsaysmaxtaryn.SimonMaxTarynActivity.SimonColors.RED;
import static nyc.c4q.tarynking.simonsaysmaxtaryn.SimonMaxTarynActivity.SimonColors.YELLOW;


/**
 * Created by tarynking on 10/1/16.
 */

public class SimonMaxTarynActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "SimonMaxTarynActivity";

    public SimonMaxTarynActivity() {

    }

    private Button button_yellow;
    private Button button_red;
    private Button button_blue;
    private Button button_green;
    private Button button_start;
    private int numberOfRounds = 0;
    int delay = 0;
    private Handler timer;
    ArrayList<SimonColors> simonSequence = new ArrayList<SimonColors>();
    ArrayList<SimonColors> playerSequence = new ArrayList<SimonColors>();
    TextView mytextView;

    public enum SimonColors {
        GREEN, RED, BLUE, YELLOW;

        @Override
        public String toString() {
            return name();
        }
    }

    SimonColors simonColor;


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
                Log.d("SimonMaxTarynActivity", "onClick: we init");
                startGame(true);
                button_start.setClickable(false);
            }
        });
    }

    public void startGame(boolean play) {
        enableButtons(play);
        playGame(true);
    }

    public void playGame(boolean check) {
        numberOfRounds++;
        button_start.setText(String.valueOf(numberOfRounds));
        if (check == true) {
            int randomColorNum = chooseRandomColor();
            simonColor = SimonColors.values()[randomColorNum];
            simonSequence.add(simonColor);
            printColors();
            flashButtonSequence(simonSequence);
        } else {
            Toast.makeText(SimonMaxTarynActivity.this, "ERROR WITH GAME!", Toast.LENGTH_LONG);
        }
//        validateUserInput();
    }

    public int chooseRandomColor() {
        Random random = new Random();
        int randomColor = random.nextInt(SimonColors.values().length - 1);
        return randomColor;
    }


    public void initializeButtons() {
        button_yellow = (Button) findViewById(R.id.button_yellow);
        button_green = (Button) findViewById(R.id.button_green);
        button_blue = (Button) findViewById(R.id.button_blue);
        button_red = (Button) findViewById(R.id.button_red);
        button_start = (Button) findViewById(R.id.button_start);
        mytextView = (TextView) findViewById(R.id.textView);
        //resetButtonColors();
    }

    public void colorButtons() {
        button_green.setBackgroundResource(R.color.green);
        button_red.setBackgroundResource(R.color.red);
        button_blue.setBackgroundResource(R.color.blue);
        button_yellow.setBackgroundResource(R.color.yellow);
    }

    public void enableButtons(boolean start) {
        button_yellow.setEnabled(start);
        button_green.setEnabled(start);
        button_red.setEnabled(start);
        button_blue.setEnabled(start);
    }

    public void initializeListeners() {
        button_green.setOnClickListener(this);
        button_red.setOnClickListener(this);
        button_blue.setOnClickListener(this);
        button_yellow.setOnClickListener(this);
    }

    public void printColors() {
        for( SimonColors colors : simonSequence)
            System.out.println(colors);
    }


    public void flashButtonSequence(ArrayList<SimonColors> simonSequence) {
        for (int index = 0; index < simonSequence.size(); index++) {
            switch (simonSequence.get(index)) {
                case GREEN:
                    buttonFlash(button_green, R.color.green, R.color.lightgreen);
                    break;
                case RED:
                    buttonFlash(button_red, R.color.red, R.color.lightred);
                    break;
                case BLUE:
                    buttonFlash(button_blue, R.color.blue, R.color.lightblue);
                    break;
                case YELLOW:
                    buttonFlash(button_yellow, R.color.yellow, R.color.darkyellow);
                    break;
            }
        }
        delay = 0;
    }

    


    public void buttonFlash(final Button button, final int originalColor, final int flashColor) {
        timer = new Handler();
        timer.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setBackgroundResource(flashColor);
            }
        }, delay+=500);
        timer.postDelayed(new Runnable() {
            public void run() {
                button.setBackgroundResource(originalColor);
            }
        }, delay+=500);
    }

    public void setTimer(final int indexTime){
        timer = new Handler();
        timer.postDelayed(new Runnable() {
            @Override
            public void run() {
                validateUserInput();

            }
        }, 900 * indexTime + 500);
    }


    @Override
    public void onClick(View v) {
        timer = new Handler();
        switch (v.getId()) {

            case R.id.button_yellow:
//                buttonYellowClicked();
                playerSequence.add(YELLOW);
                if (simonSequence.size() == playerSequence.size())
//                validateUserInput();
                break;

            case R.id.button_red:
//                buttonRedClicked();
                playerSequence.add(RED);
               if (simonSequence.size() == playerSequence.size())
//                validateUserInput();
                break;

            case R.id.button_blue:
//                buttonBlueClicked();
                playerSequence.add(BLUE);
                if (simonSequence.size() == playerSequence.size())
//                validateUserInput();
                break;

            case R.id.button_green:
//                buttonGreenClicked();
                playerSequence.add(GREEN);
                if (simonSequence.size() == playerSequence.size())
//                validateUserInput();
                break;

        }
        validateUserInput();
    }

    public void validateUserInput() {

        if (simonSequence.size() == playerSequence.size()) {

            boolean isGuessCorrect = isUserCorrect();

            if (isGuessCorrect) {
//                numberOfRounds++;
                playerSequence.clear();
                playGame(true);
            } else {
                YouLose();
            }

        }

    }

    public boolean isUserCorrect() {
        int correctCount = 0;

        for (int arrayIndex = 0; arrayIndex < simonSequence.size(); arrayIndex++) {
            if (simonSequence.get(arrayIndex).equals(playerSequence.get(arrayIndex))) {
                //Log.d(TAG, "validateUserInput: ");
                correctCount++;
            }
        }

        if (correctCount == simonSequence.size()) {
            playerSequence.clear();
            return true;
        }
        return false;
    }

    public void YouLose() {
        /**
         * if you lose then we have to reset the game level and disable all buttons until
         * you press start
         * Start button cannot be disabled because you wouldn't be able to restart the game
         */
        Toast.makeText(SimonMaxTarynActivity.this, "You Lost!", Toast.LENGTH_SHORT)
                .show();
        simonSequence.clear();
        playerSequence.clear();
        button_start.setText("START");
        button_start.setClickable(true);
        enableButtons(false);
        numberOfRounds = 0;
    }


}


