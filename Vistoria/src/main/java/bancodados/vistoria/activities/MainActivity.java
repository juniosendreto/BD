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
import android.widget.ImageButton;
import android.widget.TextView;

import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

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

    private View mJanelaInfo;
    private LayoutInflater mInflater;

    private ImageButton mMyLocation;
    private ImageButton mMyLocationPoint;

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

        mInflater = this.getLayoutInflater();

        mMyLocation = (ImageButton) findViewById(R.id.myLocation);
        mMyLocation.setId(new Integer(1));
        mMyLocationPoint = (ImageButton) findViewById(R.id.myLocationPoint);
        mMyLocation.setId(new Integer(2));

        mListController = 0;
        mOsmdroid = (MapView) findViewById(R.id.mapview);
        mOsmdroid.setTileSource(TileSourceFactory.MAPNIK);
        mOsmdroid.setId(new Integer(1));
        mMapController = (MapController) mOsmdroid.getController();

        mMarker = new Marker(mOsmdroid);
        mMarker.setIcon(getResources().getDrawable(R.drawable.marker_point));
        mMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mMapController.setZoom(3);

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
            case R.id.action_listar_vistorias:
                intent = new Intent(this, ListViewVistoriaActivity.class);
                startActivity(intent);
                break;
            case R.id.action_sobre:
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                mJanelaInfo = mInflater.inflate(R.layout.janela_inf, null);
                alertDialog
                        .setTitle("Projeto de Iniciação Científica")
                        .setView(mJanelaInfo)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                mJanelaInfo = null;
                            }
                        })
                        .show();
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
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch (id){
            case R.id.open_street_map_menu:{
                if(mOsmdroid.getVisibility() == View.GONE) {
                    mOsmdroid.setVisibility(View.VISIBLE);
                    mMyLocation.setVisibility(View.VISIBLE);
                    mMyLocationPoint.setVisibility(View.VISIBLE);
                }else {
                    mOsmdroid.setVisibility(View.GONE);
                    mMyLocation.setVisibility(View.GONE);
                    mMyLocationPoint.setVisibility(View.GONE);
                }
                drawer.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.lista_vistoria_mapa_menu:{
                if(mOsmdroid.getVisibility() != View.GONE) {
                    if (mListController == 0) {
                        PointMap.setAllMarkers(MainActivity.this, mOsmdroid, getSupportFragmentManager());
                        mOsmdroid.getOverlays().get(0).setEnabled(true);
                        mListController = 1;
                    } else if (mListController == 1) {
                        PointMap.removeAllMarkers(getApplicationContext(), mOsmdroid, mMapEventsOverlay, mMarker);
                        mListController = 0;
                    }
                }
                break;
            }
            default:{
                break;
            }
        }
        return true;
    }

    public void getCoordinate(final View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(view.getId() == mMyLocation.getId()){
                    mMapController.setZoom(15);
                    GeoPoint center = new GeoPoint(location.getLatitude(), location.getLongitude());
                    mMapController.animateTo(center);
                }else if(view.getId() == mMyLocationPoint.getId()){
                    mMapController.setZoom(15);
                    mMarker.setPosition(new GeoPoint(location.getLatitude(), location.getLongitude()));
                    PointMap.setMarker(getApplicationContext(), mMarker, mOsmdroid);
                    mMapController.animateTo(mMarker.getPosition());
                    mMarker.getInfoWindow().close();
                    mOsmdroid.invalidate();
                }
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
                alert.setTitle("Alerta")
                        .setMessage("Você não possui conexão com internet ou GPS não está ligado, digite manualmente")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
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