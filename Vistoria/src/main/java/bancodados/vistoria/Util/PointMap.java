package bancodados.vistoria.Util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bancodados.vistoria.R;
import bancodados.vistoria.core.service.dao.LocalizacaoDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.VistoriaDaoImpl;
import bancodados.vistoria.model.Localizacao;
import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.model.UsuarioVistoria;
import bancodados.vistoria.model.Vistoria;


/**
 * Created by junio on 26/09/16.
 */

public class PointMap {

    public static void setMarker(Context context, Marker marker, MapView mapView){
        if(!(mapView.getOverlays().contains(marker))) {
            mapView.getOverlays().add(marker);
            mapView.invalidate();
        }
    }

    public static void setAllMarkers(Context context, MapView mapView, FragmentManager fragmentManager){
        LocalizacaoDaoImpl localizacaoDao = new LocalizacaoDaoImpl(context);

        List<Localizacao> localizacoes = (ArrayList)localizacaoDao.listAll(Localizacao.class);

        for(Localizacao l: localizacoes){
            CustomMarkerInfoWindow customMarkerInfoWindow = new CustomMarkerInfoWindow(context, R.layout.inf_vistoria, mapView);

            Marker marker = new Marker(mapView);
            marker.setPosition(new GeoPoint(l.getLatitude(), l.getLongitude()));
            customMarkerInfoWindow.getView().setId(((int) (long) l.getId()));
            marker.setIcon(context.getResources().getDrawable(R.drawable.marker_default));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker, MapView mapView) {
                    if(marker.getInfoWindow().isOpen()){
                        marker.getInfoWindow().close();
                    }else{
                        marker.showInfoWindow();
                    }
                    return false;
                }
            });
            marker.setInfoWindow(customMarkerInfoWindow);
            mapView.getOverlays().add(marker);
        }
        mapView.invalidate();
    }

    public static void removeAllMarkers(Context context, MapView mapView, MapEventsOverlay mapEventsOverlay, Marker marker){
        if(mapView.getOverlays().contains(mapEventsOverlay)){
            if(mapView.getOverlays().contains(marker)){
                mapView.getOverlays().clear();
                mapView.getOverlays().add(mapEventsOverlay);
                mapView.getOverlays().add(marker);
            }else{
                mapView.getOverlays().clear();
                mapView.getOverlays().add(mapEventsOverlay);
            }
        }else{
            mapView.getOverlays().clear();
        }
        mapView.invalidate();
    }

    public static void closeAllMarkInf(List<Overlay> overlays, Marker marker){

        List<Marker> markers = new ArrayList<>();

        for(Overlay o: overlays){
            if(o.getClass() != MapEventsOverlay.class)
                markers.add((Marker) o);
        }

        for(Marker m: markers){
            if(!(m.equals(marker)) || m.getInfoWindow().isOpen())
                m.closeInfoWindow();
        }
    }
}
