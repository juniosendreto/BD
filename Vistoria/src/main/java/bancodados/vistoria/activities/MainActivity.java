package bancodados.vistoria.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.MarkerInfoWindow;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.Util.CustomMarkerInfoWindow;
import bancodados.vistoria.Util.Menssage;
import bancodados.vistoria.Util.PointMap;
import bancodados.vistoria.core.service.dao.GPSTracker;
import bancodados.vistoria.core.service.dao.LocalizacaoDaoImpl;
import bancodados.vistoria.core.service.dao.VistoriaDaoImpl;
import bancodados.vistoria.model.Localizacao;
import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.model.Vistoria;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Localizacao localizacao;
    private LocalizacaoDaoImpl mLocalizacaoDao;
    private Vistoria mVistoria;
    private VistoriaDaoImpl mVistoriaDao;
    private GPSTracker gpsTracker;

    private TextView mNomeDrawer;
    private TextView mLoginDrawer;
    private MapView mOsmdroid;
    private MapController mMapController;
    private Marker mMarker;
    private Integer mListController;
    private MapEventsReceiver mMapEventsReceiver;
    private MapEventsOverlay mMapEventsOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginDrawer = (TextView) findViewById(R.id.loginDrawer);
        mNomeDrawer = (TextView) findViewById(R.id.nomeDrawer);

        mLoginDrawer.setText(Usuario.getInstance().getLogin());
        mNomeDrawer.setText(Usuario.getInstance().getNome());


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        localizacao =  new Localizacao();
        mLocalizacaoDao = new LocalizacaoDaoImpl(getApplicationContext());
        mVistoria = new Vistoria(getApplicationContext());
        mVistoriaDao = new VistoriaDaoImpl(getApplicationContext());
        gpsTracker = new GPSTracker(MainActivity.this);


        final LayoutInflater inflater = this.getLayoutInflater();

        final View janelaLatLong = inflater.inflate(R.layout.janela_lat_long, null);
        final EditText latitudeET = (EditText) janelaLatLong.findViewById(R.id.latitudeET);
        final EditText longitudeET = (EditText) janelaLatLong.findViewById(R.id.longitudeET);


        mListController = 0;
        mOsmdroid = (MapView) findViewById(R.id.mapview);
        mOsmdroid.setTileSource(TileSourceFactory.MAPNIK);
        mOsmdroid.setId(new Integer(1));
        mMapController = (MapController) mOsmdroid.getController();
        mMapController.setZoom(12);

        mMarker = new Marker(mOsmdroid);
        mMarker.setIcon(getResources().getDrawable(R.drawable.marker_red));
        mMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        GeoPoint center = new GeoPoint(-23.20570845486738, -45.873516831970164);
        mMapController.animateTo(center);

        mOsmdroid.setMinZoomLevel(2);
        mOsmdroid.setBuiltInZoomControls(true);
        mOsmdroid.setLongClickable(true);
        mOsmdroid.setMultiTouchControls(true);

        mMapEventsReceiver =  new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint geoPoint) {
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint geoPoint) {
                mMarker.setPosition(geoPoint);
                PointMap.setMarker(getApplicationContext(), mMarker, mOsmdroid);
                mMapController.animateTo(mMarker.getPosition());
                mMarker.getInfoWindow().close();
                return false;
            }

        };

        mMapEventsOverlay = new MapEventsOverlay(getBaseContext(), mMapEventsReceiver);
        mOsmdroid.getOverlays().add(mMapEventsOverlay);
        mOsmdroid.invalidate();



        mMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                if(marker.isInfoWindowOpen())
                    marker.getInfoWindow().close();
                else{
                    marker.setInfoWindow(new CustomMarkerInfoWindow(MainActivity.this, R.layout.bonuspack_bubble, mOsmdroid));
                    marker.showInfoWindow();
                    mMapController.animateTo(marker.getPosition());

                }
                return false;
            }
        });



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

    /*public class CustomMakerInfoWindow extends MarkerInfoWindow{

        TextView bubbleSubdescription = (TextView) mView.findViewById(R.id.bubble_subdescription);
        TextView bubbleTitle = (TextView) mView.findViewById(R.id.bubble_title);
        Button criarVistoria = (Button) mView.findViewById(R.id.criarVistoria);

        TextView idVistoria = (TextView) mView.findViewById(R.id.idVistoria);
        TextView dataVistoria = (TextView) mView.findViewById(R.id.dataVistoria);
        TextView autorVistoria = (TextView) mView.findViewById(R.id.autorVistoria);
        Button verVistoria = (Button) mView.findViewById(R.id.verVistoria);
        int layoutResId;
        MapView mapView;

        public CustomMakerInfoWindow(int layoutResId, MapView mapView) {
            super(layoutResId, mapView);
            this.layoutResId = layoutResId;
            this.mapView = mapView;
        }



        @Override
        public void onOpen(Object item){
            final Marker marker = (Marker) item;

            if(layoutResId == R.layout.bonuspack_bubble){
                bubbleSubdescription.setVisibility(View.VISIBLE);
                bubbleTitle.setText("Ponto");
                criarVistoria.setVisibility(View.VISIBLE);
                bubbleTitle.setVisibility(View.VISIBLE);
                bubbleSubdescription.setText("Latitude: " + marker.getPosition().getLatitude() + " " +
                        "Longitude: " + marker.getPosition().getLongitude());
            }else{
                idVistoria.setVisibility(View.VISIBLE);
                //idVistoria.setText("ID: ");
                dataVistoria.setVisibility(View.VISIBLE);
                //dataVistoria.setText("Data: ");
                autorVistoria.setVisibility(View.VISIBLE);
                //utorVistoria.setText("Autor: ");
                verVistoria.setVisibility(View.VISIBLE);
                verVistoria.setText("Ver Vistoria");
            }


            criarVistoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, VistoriaActivity.class);
                    localizacao.setLatitude(marker.getPosition().getLatitude());
                    localizacao.setLongitude(marker.getPosition().getLongitude());
                    intent.putExtra("localizacao",  localizacao);
                    startActivity(intent);
                }
            });

            verVistoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

    }*/

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
                                intent.putExtra("localizacao", localizacao);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.open_street_map_menu) {
            if(mOsmdroid.getVisibility() == View.GONE)
                mOsmdroid.setVisibility(View.VISIBLE);
            else
                mOsmdroid.setVisibility(View.GONE);
        } else if (id == R.id.lista_vistoria_mapa_menu) {
            if(mOsmdroid.getVisibility() != View.GONE){
                if(mListController == 0){
                    PointMap.setAllMarkers(getApplicationContext(), mOsmdroid);
                    mOsmdroid.getOverlays().get(0).setEnabled(true);
                    mListController = 1;

                }else if(mListController == 1){
                    PointMap.removeAllMarkers(getApplicationContext(), mOsmdroid, mMapEventsOverlay, mMarker);
                    mListController = 0;
                }
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                finish();

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }



}