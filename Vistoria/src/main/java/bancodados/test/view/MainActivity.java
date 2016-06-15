package bancodados.test.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bancodados.test.R;
import bancodados.test.core.service.dao.GPSTracker;
import bancodados.test.model.Localizacao;


public class MainActivity extends Activity {

    Localizacao localizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button novaVistoria = (Button) findViewById(R.id.novaVistoriaB);
        final Button listarVistorias = (Button) findViewById(R.id.listarVistoriasB);
        final EditText latitudeET = (EditText) findViewById(R.id.latitudeET);
        final EditText longitudeET = (EditText)findViewById(R.id.longitudeET);
        final Button coodernadaGPSB = (Button) findViewById(R.id.coodernadaGPSB);
        localizacao =  new Localizacao();
        final Intent intent = new Intent(this, VistoriaActivity.class);
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        final GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
        //Location location;

        novaVistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    localizacao.setLatitude(Double.valueOf(latitudeET.getText().toString()));
                    localizacao.setLongitude(Double.valueOf(longitudeET.getText().toString()));
                    intent.putExtra("localizacao", (Localizacao) localizacao);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                    alert.setTitle("Alerta");
                    alert.setMessage("Você precisa Digitar ou pegar coordenada para realizar vistoria");
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
                }
            }
        });
        coodernadaGPSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsTracker.getLocation();
                if(gpsTracker.podePegarLocalizacao()){
                    latitudeET.setText(String.valueOf(gpsTracker.getLatitude()));
                    longitudeET.setText(String.valueOf(gpsTracker.getLongitude()));
                }else{
                    alert.setTitle("Alerta");
                    alert.setMessage("Você não possui conexão com internet ou GPS não está ligado, digite manualmente");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
                }
            }
        });

        listarVistorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chamarActivity(Class.forName("bancodados.test.view.ListViewVistoriaActivity"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void chamarActivity(Class novaActivity) {
        Intent abrirActivity = new Intent(this, novaActivity);
        startActivity(abrirActivity);

    }

}
