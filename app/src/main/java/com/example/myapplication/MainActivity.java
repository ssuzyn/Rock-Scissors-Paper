package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.text.TextWatcher;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    Button btnStart;
    EditText soloPlayerName, playerOneName, playerTwoName;
    RadioGroup rgroup1;
    RadioButton rdoOne, rdoTwo;
    LinearLayout twoModeGameLayout;
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btnStart);
        playerOneName = (EditText) findViewById(R.id.playerOneName);
        playerTwoName = (EditText) findViewById(R.id.playerTwoName);
        rgroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        rdoOne = (RadioButton) findViewById(R.id.RdoOne);
        rdoTwo = (RadioButton) findViewById(R.id.RdoTwo);
        twoModeGameLayout = (LinearLayout) findViewById(R.id.twoModeGameLayout);


        userList = new ArrayList<>();

        ArrayList<User> list = getIntent().getParcelableArrayListExtra("userList");
        if(list != null && !list.isEmpty()){
            userList = list;
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = playerOneName.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("playerName", playerName);
                intent.putParcelableArrayListExtra("userList", (ArrayList<User>) userList);

                startActivity(intent);
            }
        });

        rgroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.RdoOne) {
                    setOnePlayerMode();
                } else if (checkedId == R.id.RdoTwo) {
                    setTwoPlayerMode();
                }
            }
        });

        playerOneName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            @Override
            public void afterTextChanged(Editable s) {
                updateStartButtonState();
            }
        });

        playerTwoName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateStartButtonState();
            }
        });


    }

    private void setOnePlayerMode() {
        twoModeGameLayout.setVisibility(View.INVISIBLE);
    }

    private void setTwoPlayerMode() {
        twoModeGameLayout.setVisibility(View.VISIBLE);
    }

    private void updateStartButtonState() {
        boolean isOnePlayerMode = rdoOne.isChecked();
        boolean isTwoPlayerMode = rdoTwo.isChecked();
        String player1Name = playerOneName.getText().toString();
        String player2Name = playerTwoName.getText().toString();

        boolean isEnabled = isOnePlayerMode ? !player1Name.isEmpty() : (!player1Name.isEmpty() && !player2Name.isEmpty());

        btnStart.setEnabled(isEnabled);
    }
}