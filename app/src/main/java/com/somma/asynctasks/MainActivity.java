package com.somma.asynctasks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MIS_LOGS = "MIS_LOGS";

    protected TextView tvMensaje;
    protected ProgressBar pbProgreso;

    private String mensajePredeterminado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMensaje = findViewById(R.id.tvMensaje);
        pbProgreso = (ProgressBar)findViewById(R.id.pbProgreso);

        mensajePredeterminado = getResources().getString(R.string.tvMensaje);
    }

    public void btnProcesarOnClick(View view) {
        //enseguida volvemos
    }

    public void btnRestaurarOnClick(View view) {
        tvMensaje.setText(mensajePredeterminado);
    }

    private class TareaProcesarMensaje extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.i(MIS_LOGS, "onPreExecute corre en el hilo " + Thread.currentThread().getId());
            pbProgreso.setProgress(0);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.i(MIS_LOGS, "doInBackground corre en el hilo " + Thread.currentThread().getId());

            String mensaje = strings[0];
            int longitudMensaje = mensaje.length();
            StringBuilder mensajeProcesado = new StringBuilder();

            for (int i = 0; i < longitudMensaje; i++) {
                SystemClock.sleep(1000);

                char caracter;
                if (i % 2 == 0){
                    caracter = Character.toUpperCase(mensaje.charAt(i));
                } else {
                    caracter = Character.toLowerCase(mensaje.charAt(i));
                }

                mensajeProcesado.append(caracter);
            }
            return mensaje.toString();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
