package com.rocqjones.intoverflown.connect3game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = green and 1 = red
    int active_player = 0;
    //2 means unplayed
    int gameState [] = {2,2,2,2,2,2,2,2,2};
    //this is manage user input for 3 match numbers with unique arr. Consider horizontal first, verticals, and then diagonals
    int winning_positions [] [] = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    //a variable that keeps track of whether the game is active or not that will solve not to play when player has already won
    boolean gameIsActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        //we can get counter created in xml as
        counter.getTag().toString();
        //for tapped ball
        int tapped_counter = Integer.parseInt(counter.getTag().toString());
        //after parsing to string we check that whick is tapped through game_state

        if (gameState[tapped_counter] == 2 && gameIsActive) {
            //this allows when you tap a current player not to change but remain fixed
            gameState[tapped_counter] = active_player;

            counter.setTranslationY(-1000f);//1000px on top of the screen

            if(active_player == 0) {
                counter.setImageResource(R.drawable.green);
                active_player = 1;
            }else if(active_player == 1){
                counter.setImageResource(R.drawable.red);
                active_player = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(360);

            //am going to loop through winning_positions to check if any of them in gameState have the same value
            //I will call each winningPosition int array
            for (int [] winningPosition : winning_positions) {
                //check game state
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    //printing logs to the console and should print 0 or 1 according to the winner
                    //System.out.println(gameState[winningPosition[0]]);

                    //someone has won
                    gameIsActive = false; //game can't continue playing

                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Green";
                    }
                    //updating winner before the layout pops up
                    TextView winner_message = (TextView) findViewById(R.id.winnerMessage);
                    winner_message.setText(winner + " has WON!!!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }else {
                    //If the game is a draw
                    boolean gameIsOver = true;//Temporary variable
                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }
                    //and if the game is over
                    if (gameIsOver) {
                        TextView winner_message = (TextView) findViewById(R.id.winnerMessage);
                        winner_message.setText("It's a DRAW!!!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    //play the game again method class
    public void playAgain(View view) {

        gameIsActive = true; //After next launch

        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        int active_player = 0;
        //upating game state into initial state
        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        //call this will help us loop through all images and reset them to zero
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<grid.getChildCount(); i++) {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
