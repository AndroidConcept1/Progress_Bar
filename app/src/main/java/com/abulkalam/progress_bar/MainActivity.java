package com.abulkalam.progress_bar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnStartProgress;
    private ProgressBar progressBar;
    private TextView textView;

    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textView    = findViewById(R.id.textView);
        btnStartProgress = findViewById(R.id.btnShow);

        btnStartProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBarStatus = progressBar.getProgress();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressBarStatus < 100){
                            progressBarStatus += 1;
                            //To update the progressBar and display the current value in TextView
                            progressBarHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progressBarStatus);
                                    textView.setText(progressBarStatus+" / "+progressBar.getMax());
                                }
                            });
                            try {
                                //sleep for 100 milli-second to show the progress slowly
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}