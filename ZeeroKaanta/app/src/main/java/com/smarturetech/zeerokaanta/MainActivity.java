package com.smarturetech.zeerokaanta;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.animation.*;


public class MainActivity extends AppCompatActivity {


//    private BillingClient mBillingClient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "X first", Toast.LENGTH_LONG).show();
        setBoard();
    }

        int check[][];
        int i, j;
        Button b[][];
        String c[][];
        int player = 0;
        TextView textView;
        ImageView im[][],z,w;
        ImageButton newGame;
        int images[] = {R.drawable.cross, R.drawable.zero,R.drawable.d1,R.drawable.d2,R.drawable.v1,R.drawable.v2,R.drawable.v3,R.drawable.h1,R.drawable.h2,R.drawable.h3};

    // Set up the game board.
    private void setBoard()
    {

        b = new Button[4][4];
        im = new ImageView[4][4];
        check = new int[4][4];




        newGame = (ImageButton) findViewById(R.id.newgame);
        newGame.setOnClickListener (new View.OnClickListener(){

            public void onClick(View v)
            {
                if(newGame.isEnabled())
                {
                    player=0;
                    setBoard();
                }
            }
        });

        b[1][3] = (Button) findViewById(R.id.one);
        b[1][2] = (Button) findViewById(R.id.two);
        b[1][1] = (Button) findViewById(R.id.three);

        im[1][3] = (ImageView) findViewById(R.id.onei);
        im[1][2] = (ImageView) findViewById(R.id.twoi);
        im[1][1] = (ImageView) findViewById(R.id.threei);

        im[1][3].setImageDrawable(null);
        im[1][2].setImageDrawable(null);
        im[1][1].setImageDrawable(null);

        b[2][3] = (Button) findViewById(R.id.four);
        b[2][2] = (Button) findViewById(R.id.five);
        b[2][1] = (Button) findViewById(R.id.six);

        im[2][3] = (ImageView) findViewById(R.id.fouri);
        im[2][2] = (ImageView) findViewById(R.id.fivei);
        im[2][1] = (ImageView) findViewById(R.id.sixi);


        im[2][3].setImageDrawable(null);
        im[2][2].setImageDrawable(null);
        im[2][1].setImageDrawable(null);

        b[3][3] = (Button) findViewById(R.id.seven);
        b[3][2] = (Button) findViewById(R.id.eight);
        b[3][1] = (Button) findViewById(R.id.nine);

        im[3][3] = (ImageView) findViewById(R.id.seveni);
        im[3][2] = (ImageView) findViewById(R.id.eighti);
        im[3][1] = (ImageView) findViewById(R.id.ninei);


        im[3][3].setImageDrawable(null);
        im[3][2].setImageDrawable(null);
        im[3][1].setImageDrawable(null);

        w =(ImageView) findViewById(R.id.win);
        w.setImageDrawable(null);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                check[i][j] = 2;
        }

        // add the click listeners for each button

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if (!b[i][j].isEnabled()) {
                    b[i][j].setEnabled(true);
                }
            }
        }
    }

    class MyClickListener implements View.OnClickListener {
        int x;
        int y;


        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public void onClick(View view) {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                if (player == 0) {
                    im[x][y].setImageResource(images[0]);
                    z=im[x][y];
                    animate(z);
                    check[x][y] = 0;
                    player = 1;
                    checkBoard();
                } else {
                    im[x][y].setImageResource(images[1]);
                        z=im[x][y];
                    animate(z);
                    check[x][y] = 1;
                    player = 0;
                    checkBoard();
                }
            }
        }

    }


        // check the board to see if someone has won
    public void animate(ImageView z) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            int cx = w.getWidth() / 2;
            int cy = w.getHeight() / 2;

            // get the final radius for the clipping circle
            float finalRadius = (float) Math.hypot(cx, cy);

            // create the animator for this view (the start radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(w, cx, cy, 0, finalRadius);

            // make the view visible and start the animation
            w.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            // set the view to visible without a circular reveal animation below Lollipop
            w.setVisibility(View.VISIBLE);
        }
    }

        private boolean checkBoard() {
            boolean gameOver = false;
            if ( check[1][1] == 0 && check[2][2]==0  &&  check[3][3]==0){
                w.setImageResource(images[2]);
                animate(w);
                Toast.makeText(MainActivity.this, "Player 1 Wins.", Toast.LENGTH_SHORT).show();

                gameOver = true;

            }
                else if ( check[1][3] == 0 && check[2][2]== 0&& check[3][1]==0){
                w.setImageResource(images[3]);
                animate(w);
                    Toast.makeText(MainActivity.this, "Player 1 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
                    else if (check[1][2] == 0 && check[2][2]==0  &&  check[3][2]==0){
                w.setImageResource(images[5]);
                animate(w);
                    Toast.makeText(MainActivity.this, "Player 1 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
                    else if ( check[1][3] == 0 && check[2][3]== 0 && check[3][3]== 0)
            {
                w.setImageResource(images[4]);

                animate(w);
                    Toast.makeText(MainActivity.this, "Player 1 Wins.", Toast.LENGTH_SHORT).show();

                gameOver = true;
            }
                    else if ( check[1][1] == 0 && check[1][2]==0 &&  check[1][3] == 0){
                w.setImageResource(images[7]);
                animate(w);
                    Toast.makeText(MainActivity.this, "Player 1 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
            else if ( check[2][1] == 0 &&  check[2][2] == 0 &&  check[2][3] == 0){
                w.setImageResource(images[8]);
                animate(w);
                Toast.makeText(MainActivity.this, "Player 1 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
                    else if ( check[3][1] == 0 &&  check[3][2] == 0 &&  check[3][3] == 0){
                w.setImageResource(images[9]);
                animate(w);
                Toast.makeText(MainActivity.this, "Player 1 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
                    else if ( check[1][1] == 0 &&  check[2][1] == 0 &&  check[3][1] == 0) {
                w.setImageResource(images[6]);
                animate(w);
                Toast.makeText(MainActivity.this, "Player 1 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            } else if ( check[1][1] == 1 && check[2][2] == 1 &&  check[3][3] == 1){
                w.setImageResource(images[2]);

                animate(w);
                Toast.makeText(MainActivity.this, "Player 2 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;

            }
            else if ( check[1][3] == 1 && check[2][2] == 1 &&  check[3][1] == 1){
                w.setImageResource(images[3]);

                animate(w);
                Toast.makeText(MainActivity.this, "Player 2 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
            else if( check[1][2] == 1 &&  check[2][2] == 1 &&  check[3][2] == 1){
                w.setImageResource(images[5]);
                animate(w);
                Toast.makeText(MainActivity.this, "Player 2 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
            else if ( check[1][3] == 1 &&  check[2][3] == 1 &&  check[3][3] == 1)
            {
                w.setImageResource(images[4]);

                animate(w);
                Toast.makeText(MainActivity.this, "Player 2 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
            else if ( check[1][1] == 1 &&  check[1][2] == 1 &&  check[1][3] == 1){
                w.setImageResource(images[7]);
                animate(w);
                Toast.makeText(MainActivity.this, "Player 2 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
            else if ( check[2][1] == 1 &&  check[2][2] == 1 &&  check[2][3] == 1){
                w.setImageResource(images[8]);
                animate(w);
                Toast.makeText(MainActivity.this, "Player 2 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
            else if ( check[3][1] == 1 &&  check[3][2] == 1 &&  check[3][3] == 1){
                w.setImageResource(images[9]);
                animate(w);
                Toast.makeText(MainActivity.this, "Player 2 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
            else if ( check[1][1] == 1 &&  check[2][1] == 1 &&  check[3][1] == 1) {
                w.setImageResource(images[6]);
             animate(w);
             Toast.makeText(MainActivity.this, "Player 2 Wins.", Toast.LENGTH_SHORT).show();
                gameOver = true;
            } else {
                boolean empty = false;
                for (i = 1; i <= 3; i++) {
                    for (j = 1; j <= 3; j++) {
                        if (check[i][j] == 2) {
                            empty = true;
                            break;
                        }
                    }
                }
                if (!empty) {
                    gameOver = true;
                    Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_SHORT).show();

                }
            }if(gameOver)

                for(i=1;i<=3;i++)
                {
                    for(j=1;j<=3;j++)
                    {
                        b[i][j].setEnabled(false);
                    }
                }

            return gameOver;
        }




    }



