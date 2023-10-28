package com.example.threadssemaforo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
public class MainActivity extends AppCompatActivity {

    private LinearLayout b1, b2, b3;
    private Button Start;
    private boolean iniciar = false;
    private int counter = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        b1 = findViewById(R.id.Bombilla_Roja);
        b2 = findViewById(R.id.Bombilla_Amarillo);
        b3 = findViewById(R.id.Bombilla_Verde);
        Start = findViewById(R.id.Btn_iniciar);
        b1.setBackgroundColor(getResources().getColor(R.color.gris));
        b2.setBackgroundColor(getResources().getColor(R.color.gris));
        b3.setBackgroundColor(getResources().getColor(R.color.gris));
    }

    private void setColorCounter() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (iniciar) {
                    counter++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            switch (counter) {
                                case 1:
                                    b1.setBackgroundColor(getResources().getColor(R.color.rojo));
                                    b2.setBackgroundColor(getResources().getColor(R.color.gris));
                                    b3.setBackgroundColor(getResources().getColor(R.color.gris));
                                    break;
                                case 2:
                                    b1.setBackgroundColor(getResources().getColor(R.color.gris));
                                    b2.setBackgroundColor(getResources().getColor(R.color.amarillo));
                                    b3.setBackgroundColor(getResources().getColor(R.color.gris));
                                    break;
                                case 3:
                                    b1.setBackgroundColor(getResources().getColor(R.color.gris));
                                    b2.setBackgroundColor(getResources().getColor(R.color.gris));
                                    b3.setBackgroundColor(getResources().getColor(R.color.verde));
                                    counter = 0;
                                    break;
                            }
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    public void Start(View view) {
        if (iniciar) {
            iniciar = false;
            counter = 0;
            Start.setText("Start");
            b1.setBackgroundColor(getResources().getColor(R.color.gris));
            b2.setBackgroundColor(getResources().getColor(R.color.gris));
            b3.setBackgroundColor(getResources().getColor(R.color.gris));
        } else {
            iniciar = true;
            Start.setText("Stop");
            setColorCounter();
        }
    }
}

