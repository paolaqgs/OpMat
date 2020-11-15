package com.example.opmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton btsuma, btresta, btmult, btdiv;
    private TextView txtv;
    public static int puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btsuma = findViewById(R.id.btnSuma);
        btresta = findViewById(R.id.btnResta);
        btmult = findViewById(R.id.btnMult);
        btdiv = findViewById(R.id.btnDiv);
        txtv = findViewById(R.id.tv1);

        btsuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpActivity.class);
                intent.putExtra("operacion","suma");
                startActivity(intent);
            }
        });

        btresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpActivity.class);
                intent.putExtra("operacion","resta");
                startActivity(intent);
            }
        });

        btmult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpActivity.class);
                intent.putExtra("operacion","multiplicacion");
                startActivity(intent);
            }
        });
        btdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpActivity.class);
                intent.putExtra("operacion","division");
                startActivity(intent);
            }
        });
    }
}