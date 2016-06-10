package bancodados.test.core.service.dao;

import android.app.Service;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by junio on 10/06/16.
 */
public class GPSTracker implements LocationListener{

    private final Context context;

    Boolean gPSAtivado = false;
    Boolean internetAtivada = false;
    Boolean podePegarLocalizacao = false;

    Double latitude;
    Double Longitude;

    protected LocationManager locationManager;

    public GPSTracker(Context context) {
        this.context = context;
        getLocation();
    }

    public Location getLocation(){
        try{
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            gPSAtivado = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            internetAtivada = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            if((!gPSAtivado) && (!internetAtivada)){

            }else{
                this.podePegarLocalizacao = true;
                if(internetAtivada){
                    //locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
}
