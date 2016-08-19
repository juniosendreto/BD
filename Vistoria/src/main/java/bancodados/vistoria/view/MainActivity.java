package bancodados.vistoria.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import bancodados.vistoria.R;
import bancodados.vistoria.Util.Menssage;
import bancodados.vistoria.core.service.dao.GPSTracker;
import bancodados.vistoria.model.Localizacao;


public class MainActivity extends AppCompatActivity {

    Localizacao localizacao;
    GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localizacao =  new Localizacao();
        gpsTracker = new GPSTracker(MainActivity.this);


        final LayoutInflater inflater = this.getLayoutInflater();

        final View janelaLatLong = inflater.inflate(R.layout.janela_lat_long, null);
        final EditText latitudeET = (EditText) janelaLatLong.findViewById(R.id.latitudeET);
        final EditText longitudeET = (EditText) janelaLatLong.findViewById(R.id.longitudeET);



        final MapView osmdroid = (MapView) findViewById(R.id.mapview);
        osmdroid.canZoomIn();
        osmdroid.canZoomOut();


        /*
        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                IMapController mapController = osmdroid.getController();
                mapController.setZoom(20);
                GeoPoint startPoint = new GeoPoint(location);
                mapController.setCenter(startPoint);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        gpsTracker.getLocation(locationListener);
           */

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.action_update_usuario:
                intent = new Intent(this, UpdateUsuarioActivity.class);
                startActivity(intent);
                break;

            case R.id.action_criar_vistoria:
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = this.getLayoutInflater();
                View janelaLatLong = inflater.inflate(R.layout.janela_lat_long, null);

                final EditText latitudeET = (EditText) janelaLatLong.findViewById(R.id.latitudeET);
                final EditText longitudeET = (EditText) janelaLatLong.findViewById(R.id.longitudeET);

                alertDialog
                        .setTitle("Criando Vistoria")
                        .setView(janelaLatLong)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, VistoriaActivity.class);

                                localizacao.setLatitude(Double.valueOf(latitudeET.getText().toString()));
                                localizacao.setLongitude(Double.valueOf(longitudeET.getText().toString()));
                                intent.putExtra("localizacao", (Localizacao) localizacao);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

                break;
            case R.id.action_listar_vistorias:
                intent = new Intent(this, ListViewVistoriaActivity.class);
                startActivity(intent);
                break;
            case R.id.action_sobre:
                break;
            case R.id.action_sair:
                onBackPressed();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }
    public void getCoordinate(View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        final ProgressDialog progressDialog = Menssage.startProgressDialog(MainActivity.this,
                "Aguade, Gerando Coordenada");

        final LayoutInflater inflater = this.getLayoutInflater();

        final View janelaLatLong = inflater.inflate(R.layout.janela_lat_long, null);
        final EditText latitudeET = (EditText) janelaLatLong.findViewById(R.id.latitudeET);
        final EditText longitudeET = (EditText) janelaLatLong.findViewById(R.id.longitudeET);

        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                progressDialog.dismiss();

                latitudeET.setText(String.valueOf(location.getLatitude()));
                longitudeET.setText(String.valueOf(location.getLongitude()));
                gpsTracker.closeGPS(this);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                alert.setTitle("Alerta");
                alert.setMessage("Você não possui conexão com internet ou GPS não está ligado, digite manualmente");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
                progressDialog.dismiss();
            }
        };

        gpsTracker.getLocation(locationListener);

    }

    public void onBackPressed(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alerta");
        alert.setMessage("Se você voltar, você terá que logar novamente!");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }

}