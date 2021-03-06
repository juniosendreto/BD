package bancodados.vistoria.core.service.dao;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by junio on 10/06/16.
 */
public class GPSTracker extends Service{

    private final Context context;

    private Boolean isGPSEnabled = false;
    private Boolean isInternetEnabled = false;
    private Boolean canGetLocation = false;

    private LocationManager locationManager;

    private Location location;

    public GPSTracker(Context context) {
        this.context = context;
    }

    public  Boolean getLocation(LocationListener locationListener) {
        try{
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isInternetEnabled = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);


            if(isGPSEnabled){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    return true;

                }
            }else if (isInternetEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    return true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }


    public void closeGPS(LocationListener locationListener){
        if(locationManager != null){
            locationManager.removeUpdates(locationListener);
        }
    }

    public Boolean canGetLocation(){
        return this.canGetLocation;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public Boolean getCanGetLocation() {
        return canGetLocation;
    }

    public void setCanGetLocation(Boolean canGetLocation) {
        this.canGetLocation = canGetLocation;
    }

    public Boolean getIsInternetEnabled() {
        return isInternetEnabled;
    }

    public void setIsInternetEnabled(Boolean isInternetEnabled) {
        this.isInternetEnabled = isInternetEnabled;
    }

    public Boolean getIsGPSEnabled() {
        return isGPSEnabled;
    }

    public void setIsGPSEnabled(Boolean isGPSEnabled) {
        this.isGPSEnabled = isGPSEnabled;
    }

    public Context getContext() {
        return context;
    }
}
