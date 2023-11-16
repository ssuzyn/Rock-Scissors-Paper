package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    ImageView computer, rock, scissors, paper;
    TextView playerName, gameResult, score;
    int userScore = 0;
    int rounds = 0;
    String user;
    ArrayList<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        computer = (ImageView) findViewById(R.id.computerImg);
        rock = (ImageView) findViewById(R.id.rock);
        scissors = (ImageView) findViewById(R.id.scissors);
        paper = (ImageView) findViewById(R.id.paper);
        playerName = (TextView) findViewById(R.id.playerName);
        gameResult= (TextView) findViewById(R.id.gameResult);
        score = (TextView) findViewById(R.id.score);

        user = getIntent().getStringExtra("playerName");
        userList = getIntent().getParcelableArrayListExtra("userList");
        playerName.setText(user);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(0);
            }
        });

        scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(1);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(2);
            }
        });
    }

    private void playGame(int userChoice) {
        Random random = new Random();
        int computerChoice = random.nextInt(3);

        if (computerChoice == 0) {
            computer.setImageResource(R.drawable.rock);
        } else if (computerChoice == 1) {
            computer.setImageResource(R.drawable.scissors);
        } else {
            computer.setImageResource(R.drawable.paper);
        }

        String result;
        if (userChoice == computerChoice) {
            result = "무승부";
        } else if ((userChoice == 0 && computerChoice == 1) || (userChoice == 1 && computerChoice == 2) || (userChoice == 2 && computerChoice == 0)) {
            result = user + "승 !!";
            userScore += 1;
        } else {
            result = user + "패 ㅠ.ㅠ";
            userScore -= 1;
        }

        rounds += 1;
        updateGameResult(result);
    }

    private void updateGameResult(String result) {
        gameResult.setText("결과: " + result);
        score.setText("점수: " + userScore);

        if (rounds == 5) {
            endGame();
        }
    }

    private void endGame() {

        Intent intent = new Intent(GameActivity.this, ResultActivity.class);

        userList.add(new User(user, userScore));
        intent.putParcelableArrayListExtra("userList", (ArrayList<? extends Parcelable>) userList);

        startActivity(intent);
    }
}
