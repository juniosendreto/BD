package bancodados.vistoria.Util;

import android.content.Context;
import android.util.Log;

import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.model.Localizacao;


/**
 * Created by junio on 26/09/16.
 */

public class PointMap {

    public static void setMarker(Context context, Marker marker, MapView mapView){
        mapView.getOverlays().add(marker);
        mapView.invalidate();
    }

    public static void setAllMarkers(Context context, MapView mapView, List<Localizacao> localizacoes){

        for(Localizacao l: localizacoes){
            Marker marker = new Marker(mapView);
            marker.setPosition(new GeoPoint(l.getLatitude(), l.getLongitude()));
            marker.setIcon(context.getResources().getDrawable(R.drawable.marker_default));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            mapView.getOverlays().add(marker);
        }
        mapView.invalidate();

    }


    public static void removeAllMarkers(Context context, MapView mapView, MapEventsOverlay mapEventsOverlay, Marker marker){
        if(!(mapView.getOverlays().size() == 2)){
            mapView.getOverlays().clear();
            mapView.getOverlays().add(mapEventsOverlay);
            mapView.getOverlays().add(marker);
        }
        mapView.invalidate();

    }
}
