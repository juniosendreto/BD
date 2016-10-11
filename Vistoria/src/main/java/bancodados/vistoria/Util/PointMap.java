package bancodados.vistoria.Util;

import android.content.Context;
import android.widget.TextView;

import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static void setAllMarkers(Context context, MapView mapView){
        Usuario usuario;
        UsuarioVistoria usuarioVistoria;
        Localizacao localizacao;
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(context);
        UsuarioVistoriaDaoImpl usuarioVistoriaDao = new UsuarioVistoriaDaoImpl(context);
        VistoriaDaoImpl vistoriaDao = new VistoriaDaoImpl(context);
        LocalizacaoDaoImpl localizacaoDao = new LocalizacaoDaoImpl(context);

        List<Localizacao> localizacoes = (ArrayList)localizacaoDao.listAll(Localizacao.class);
        List<Vistoria> vistorias = (ArrayList) vistoriaDao.listAll(Vistoria.class);

        for(Vistoria v: vistorias){
            CustomMarkerInfoWindow customMarkerInfoWindow = new CustomMarkerInfoWindow(context, R.layout.inf_vistoria, mapView);

            TextView idVistoria = (TextView) customMarkerInfoWindow.getView().findViewById(R.id.idVistoria);
            TextView dataVistoria = (TextView) customMarkerInfoWindow.getView().findViewById(R.id.dataVistoria);
            TextView autorVistoria = (TextView) customMarkerInfoWindow.getView().findViewById(R.id.autorVistoria);

            usuarioVistoria = usuarioVistoriaDao.findByIdVistoria(v);
            usuario = (Usuario) usuarioDao.findById(Usuario.class, usuarioVistoria.getUsuario().getId());
            localizacao = (Localizacao) localizacaoDao.findById(Localizacao.class, v.getLocalizacao().getId());

            idVistoria.setText("ID: " + v.getId());
            idVistoria.setId((int) (long) v.getId());
            dataVistoria.setText("Data: " + usuarioVistoria.getData());
            autorVistoria.setText("Autor: " + usuario.getNome());
            autorVistoria.setId((int) (long) usuario.getId());

            Marker marker = new Marker(mapView);
            marker.setPosition(new GeoPoint(localizacao.getLatitude(), localizacao.getLongitude()));
            marker.getInfoWindow().getView().setId(((int) (long) localizacao.getId()));
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
        if(!(mapView.getOverlays().size() == 2)){
            mapView.getOverlays().clear();
            mapView.getOverlays().add(mapEventsOverlay);
            mapView.getOverlays().add(marker);
        }
        mapView.invalidate();
    }

}
