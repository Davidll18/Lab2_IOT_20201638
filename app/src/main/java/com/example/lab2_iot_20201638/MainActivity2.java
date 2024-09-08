package com.example.lab2_iot_20201638;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {
    private ImageView head, torso, piernaIzq, brazoIzq, piernaDer, brazoDer;
    private TextView wordDisplay;
    private GridLayout keyboardLayout;

    private Set<Character> guessedLetters;
    private int incorrectGuesses;
    private String currentWord;
    private String[] words = {"example", "hangman", "words"}; // Replace with your actual word list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        head = findViewById(R.id.head);
        torso = findViewById(R.id.torso);
        piernaIzq = findViewById(R.id.piernaIzq);
        brazoIzq = findViewById(R.id.brazoIzq);
        piernaDer = findViewById(R.id.piernaDer);
        brazoDer = findViewById(R.id.brazoDer);
        keyboardLayout = findViewById(R.id.keyboard_layout);

        guessedLetters = new HashSet<>();
        incorrectGuesses = 0;

        setupKeyboard();
        newGame();
    }

    private void setupKeyboard() {
        for (int i = 0; i < keyboardLayout.getChildCount(); i++) {
            View view = keyboardLayout.getChildAt(i);
            if (view instanceof Button) {
                final Button button = (Button) view;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleGuess(button.getText().toString().charAt(0));
                        button.setEnabled(false);
                    }
                });
            }
        }
    }

    private void handleGuess(char letter) {
        if (currentWord.indexOf(letter) >= 0) {
            guessedLetters.add(letter);
            updateWordDisplay();
        } else {
            incorrectGuesses++;
            updateHangmanImage();
        }
    }

    private void updateWordDisplay() {
        StringBuilder display = new StringBuilder();
        for (char letter : currentWord.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                display.append(letter).append(" ");
            } else {
                display.append("_ ");
            }
        }
        wordDisplay.setText(display.toString().trim());
        if (!display.toString().contains("_")) {
            // Winning logic
        }
    }

    private void updateHangmanImage() {
        head.setVisibility(incorrectGuesses >= 1 ? View.VISIBLE : View.INVISIBLE);
        torso.setVisibility(incorrectGuesses >= 2 ? View.VISIBLE : View.INVISIBLE);
        piernaIzq.setVisibility(incorrectGuesses >= 3 ? View.VISIBLE : View.INVISIBLE);
        brazoIzq.setVisibility(incorrectGuesses >= 4 ? View.VISIBLE : View.INVISIBLE);
        piernaDer.setVisibility(incorrectGuesses >= 5 ? View.VISIBLE : View.INVISIBLE);
        brazoDer.setVisibility(incorrectGuesses >= 6 ? View.VISIBLE : View.INVISIBLE);
        if (incorrectGuesses >= 6) {
            // Losing logic
        }
    }

    private void newGame() {
        currentWord = words[(int) (Math.random() * words.length)];
        guessedLetters.clear();
        incorrectGuesses = 0;
        updateWordDisplay();
        updateHangmanImage();
    }
}