package com.example.opmat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class OpActivity extends AppCompatActivity {
    TextView  resultado, puntuacion;
    String operacion;
    ImageView  signo, imgOp, num1, num2;
    Button comprobar;
    int nu1, nu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op);

        signo = findViewById(R.id.ivSigno);
        num1 = findViewById(R.id.ivNum1);
        num2 = findViewById(R.id.ivNum2);
        resultado = findViewById(R.id.Respuesta);
        comprobar = findViewById(R.id.btnComprobar);
        puntuacion = findViewById(R.id.tvPuntos);
        imgOp = findViewById(R.id.ivop);


        Intent in= getIntent();
        Bundle bundle = in.getExtras();
        if(bundle != null){
            operacion = (String) bundle.get("operacion");
        }



        nu1 =GeneraNum();
        nu2 = GeneraNum();

        ImgNumero(nu1,nu2);

        puntuacion.setText(""+MainActivity.puntos);
        switch (operacion){
            case "suma":
                imgOp.setImageResource(R.drawable.sum);
                signo.setImageResource(R.drawable.signosum);

                break;
            case "resta":
                imgOp.setImageResource(R.drawable.resta);
                signo.setImageResource(R.drawable.signores);
                break;
            case "multiplicacion":
                imgOp.setImageResource(R.drawable.mult);
                signo.setImageResource(R.drawable.signomult);
                break;
            case "division":
                imgOp.setImageResource(R.drawable.div);
                signo.setImageResource(R.drawable.signodiv);
                Toast mensaje=Toast.makeText(getApplicationContext(),"Ignora los decimales en esta ocasión",Toast.LENGTH_LONG);
                mensaje.show();
                break;
        }

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
                puntuacion.setText(""+MainActivity.puntos); //no sirve
                nu1 =GeneraNum();
                nu2 = GeneraNum();
                ImgNumero(nu1,nu2);
                //num1.setText(""+nu1);
                //num2.setText(""+nu2);
                resultado.setText("");
                OcultarTeclado();
            }
        });

    }

    private void ImgNumero(int x, int y) {
        switch (x){ //cambia imagen numero 1
            case 0:
                num1.setImageResource(R.drawable.cero);
                break;
            case 1:
                num1.setImageResource(R.drawable.uno);
                break;
            case 2:
                num1.setImageResource(R.drawable.dos);
                break;
            case 3:
                num1.setImageResource(R.drawable.tres);
                break;
            case 4:
                num1.setImageResource(R.drawable.cuatro);
                break;
            case 5:
                num1.setImageResource(R.drawable.cinco);
                break;
            case 6:
                num1.setImageResource(R.drawable.seis);
                break;
            case 7:
                num1.setImageResource(R.drawable.siete);
                break;
            case 8:
                num1.setImageResource(R.drawable.ocho);
                break;
            case 9:
                num1.setImageResource(R.drawable.nueve);
                break;
        }
        switch (y){ //cambia imagen numero 2
            case 0:
                num2.setImageResource(R.drawable.cero);
                break;
            case 1:
                num2.setImageResource(R.drawable.uno);
                break;
            case 2:
                num2.setImageResource(R.drawable.dos);
                break;
            case 3:
                num2.setImageResource(R.drawable.tres);
                break;
            case 4:
                num2.setImageResource(R.drawable.cuatro);
                break;
            case 5:
                num2.setImageResource(R.drawable.cinco);
                break;
            case 6:
                num2.setImageResource(R.drawable.seis);
                break;
            case 7:
                num2.setImageResource(R.drawable.siete);
                break;
            case 8:
                num2.setImageResource(R.drawable.ocho);
                break;
            case 9:
                num2.setImageResource(R.drawable.nueve);
                break;
        }
    }


    private void OcultarTeclado() {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(resultado.getWindowToken(), 0);
    }

    private void alertDialog(int x) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("MAL, la respuesta correcta es: " + x);
        dialog.setTitle("Estudia más");
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }


    private void validar() {
        int x, y, temp;
        switch (operacion){
            case "suma":
                x = nu1 + nu2;
                y = Integer.parseInt(resultado.getText().toString());
                if (y == x){
                    AgregaPunto();
                    Toast mensaje=Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_LONG);
                    mensaje.show();
                } else {
                    alertDialog(x);
                }
                resultado.setText("");
                break;
            case "resta":
                if (nu2 > nu1){
                    temp = nu1;
                    nu1= nu2;
                    nu2= temp;
                }
                x = nu1 - nu2;
                y = Integer.parseInt(resultado.getText().toString());
                if (y == x){
                    AgregaPunto();
                    Toast mensaje=Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_LONG);
                    mensaje.show();
                } else {
                    alertDialog(x);
                };
                break;
            case "multiplicacion":
                x = nu1 * nu2;
                y = Integer.parseInt(resultado.getText().toString());
                if (y == x){
                    AgregaPunto();
                    Toast mensaje=Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_LONG);
                    mensaje.show();
                } else {
                    alertDialog(x);
                }
                break;
            case "division":

                if ( (nu1/nu2) % 1 == 0) {
                    x = nu1 / nu2;
                    y = Integer.parseInt(resultado.getText().toString());
                    if (y == x){
                        AgregaPunto();
                        Toast mensaje=Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_LONG);
                        mensaje.show();
                    } else {
                        alertDialog(x);
                    };
                }
                break;
        }
    }

    private void AgregaPunto() {
        MainActivity.puntos ++ ;
    }

    private int GeneraNum(){
        Random rn = new Random();
        int n = rn.nextInt(10);
        return n ;
    }


}