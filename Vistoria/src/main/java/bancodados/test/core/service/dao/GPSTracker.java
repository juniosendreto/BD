package bancodados.test.core.service.dao;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by junio on 10/06/16.
 */
public class GPSTracker extends Service  implements LocationListener{

    private final Context context;

    private Boolean gPSAtivado = false;
    private Boolean internetAtivada = false;
    private Boolean podePegarLocalizacao = false;

    private Double latitude;
    private Double longitude;

    protected LocationManager locationManager;

    private Location location;

    public GPSTracker(Context context) {
        this.context = context;
        //getLocation();
    }

    public Location getLocation(){
        try{
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            gPSAtivado = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            internetAtivada = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);

            if (internetAtivada) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    this.podePegarLocalizacao = true;
                }
            }else if(gPSAtivado){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    this.podePegarLocalizacao = true;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //pararUsoGps();
        }
        return location;
    }

    public void pararUsoGps(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    public Boolean podePegarLocalizacao(){
        return this.podePegarLocalizacao;
    }


    @Override
    public void onLocationChanged(Location location) {

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Double getLongitude() {
        if(location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        if(location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public Boolean getPodePegarLocalizacao() {
        return podePegarLocalizacao;
    }

    public void setPodePegarLocalizacao(Boolean podePegarLocalizacao) {
        this.podePegarLocalizacao = podePegarLocalizacao;
    }

    public Boolean getInternetAtivada() {
        return internetAtivada;
    }

    public void setInternetAtivada(Boolean internetAtivada) {
        this.internetAtivada = internetAtivada;
    }

    public Boolean getgPSAtivado() {
        return gPSAtivado;
    }

    public void setgPSAtivado(Boolean gPSAtivado) {
        this.gPSAtivado = gPSAtivado;
    }

    public Context getContext() {
        return context;
    }
}
