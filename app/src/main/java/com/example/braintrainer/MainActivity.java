package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

	Button startButton;
	Button playAgainButton;
	Button option1Button;
	Button option2Button;
	Button option3Button;
	Button option4Button;

	TextView timerTextView;
	TextView scoreTextView;
	TextView sumTextView;
	TextView resultTextView;

	ConstraintLayout gameLayout;

	int locationOfAnswer;
	int score;
	int numberOfQuestions;

	ArrayList<Integer> answers = new ArrayList<Integer>();

	public void startGame(View view) {

		startButton.setVisibility(View.INVISIBLE);

		gameLayout.setVisibility(View.VISIBLE);

		newQuestion();

		new CountDownTimer(30000, 1000) {
			public void onTick(long millisecondsUntilFinish) {
				long secondsUntilFinish = millisecondsUntilFinish / 1000;
				if (secondsUntilFinish < 10) {
					timerTextView.setText("0"+Long.toString(secondsUntilFinish) + "s");
				} else {
					timerTextView.setText(Long.toString(secondsUntilFinish) + "s");
				}
			}
			public void onFinish() {
				resultTextView.setText("Finished!");
				option1Button.setClickable(false);
				option2Button.setClickable(false);
				option3Button.setClickable(false);
				option4Button.setClickable(false);
				playAgainButton.setVisibility(View.VISIBLE);
			}
		}.start();

	}

	public void playAgain(View view) {

		playAgainButton.setVisibility(View.INVISIBLE);
		score = 0;
		numberOfQuestions = 0;

		option1Button.setClickable(true);
		option2Button.setClickable(true);
		option3Button.setClickable(true);
		option4Button.setClickable(true);

		startGame(view);
	}

	public void chooseAnswer(View view) {

		resultTextView = findViewById(R.id.resultTextView);
		resultTextView.setVisibility(View.VISIBLE);

		if(Integer.toString(locationOfAnswer).equals(view.getTag().toString())){
			Log.i("Correct","Correct!");
			resultTextView.setText("Correct! :)");
			score++;
		} else {
			Log.i("Wrong","Wrong!");
			resultTextView.setText("Wrong :(");
		}
		scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
		numberOfQuestions++;
		newQuestion();
	}

	public void newQuestion() {

		Random rand = new Random();

		int a = rand.nextInt(21);
		int b = rand.nextInt(21);

		sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
		scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

		locationOfAnswer = rand.nextInt(4);

		answers.clear();

		for (int i = 0; i < 4 ;i++) {
			if(i == locationOfAnswer) {
				answers.add(a+b);
			} else {
				int wrongAnswer = rand.nextInt(41);
				while (wrongAnswer == a + b) {
					wrongAnswer = rand.nextInt(41);
				}
				answers.add(wrongAnswer);
			}
		}

		option1Button.setText(Integer.toString(answers.get(0)));
		option2Button.setText(Integer.toString(answers.get(1)));
		option3Button.setText(Integer.toString(answers.get(2)));
		option4Button.setText(Integer.toString(answers.get(3)));

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startButton = findViewById(R.id.startButton);
		playAgainButton = findViewById(R.id.playAgainButton);

		option1Button = findViewById(R.id.optionButton1);
		option2Button = findViewById(R.id.optionButton2);
		option3Button = findViewById(R.id.optionButton3);
		option4Button = findViewById(R.id.optionButton4);

		timerTextView = findViewById(R.id.timerTextView);
		scoreTextView = findViewById(R.id.scoreTextView);
		sumTextView = findViewById(R.id.sumTextView);

		gameLayout = findViewById(R.id.gameLayout);
	}
}