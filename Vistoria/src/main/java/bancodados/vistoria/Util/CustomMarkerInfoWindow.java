package bancodados.vistoria.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.MarkerInfoWindow;
import org.osmdroid.views.MapView;

import java.io.File;

import bancodados.vistoria.R;
import bancodados.vistoria.activities.VistoriaActivity;
import bancodados.vistoria.core.service.dao.FotoVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.LocalizacaoDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.VistoriaDaoImpl;
import bancodados.vistoria.model.Localizacao;
import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.model.UsuarioVistoria;
import bancodados.vistoria.model.Vistoria;

/**
 * Created by junio on 06/10/16.
 */

public class CustomMarkerInfoWindow extends MarkerInfoWindow {

    private Context context;

    private Localizacao mLocalizacao;

    private UsuarioVistoriaDaoImpl mUsuarioVistoriaDao;
    private VistoriaDaoImpl mVistoriaDao;
    private LocalizacaoDaoImpl mLocalizacaoDao;
    private FotoVistoriaDaoImpl mFotoVistoriaDao;

    private TextView bubbleSubdescription = (TextView) mView.findViewById(R.id.bubble_subdescription);
    private TextView bubbleTitle = (TextView) mView.findViewById(R.id.bubble_title);
    private Button criarVistoria = (Button) mView.findViewById(R.id.criarVistoria);

    private TextView idVistoria = (TextView) mView.findViewById(R.id.idVistoria);
    private TextView dataVistoria = (TextView) mView.findViewById(R.id.dataVistoria);
    private TextView autorVistoria = (TextView) mView.findViewById(R.id.autorVistoria);
    private Button verVistoria = (Button) mView.findViewById(R.id.verVistoria);
    private Button adicionarVistoria = (Button) mView.findViewById(R.id.adicionarVistoria);
    private Button removerVistoria = (Button) mView.findViewById(R.id.removerVistoria);

    private int layoutResId;
    private MapView mapView;

    public CustomMarkerInfoWindow(Context context, int layoutResId, MapView mapView) {
        super(layoutResId, mapView);
        this.context = context;
        this.layoutResId = layoutResId;
        this.mapView = mapView;
    }

    @Override
    public void onOpen(Object item){

        final Marker marker = (Marker) item;

        mLocalizacao = new Localizacao();
        mUsuarioVistoriaDao = new UsuarioVistoriaDaoImpl(context);
        mVistoriaDao = new VistoriaDaoImpl(context);
        mLocalizacaoDao = new LocalizacaoDaoImpl(context);
        mFotoVistoriaDao = new FotoVistoriaDaoImpl(context);


        switch(layoutResId) {
            case R.layout.bonuspack_bubble:{
                bubbleSubdescription.setVisibility(View.VISIBLE);
                bubbleTitle.setText("Ponto");
                criarVistoria.setVisibility(View.VISIBLE);
                bubbleTitle.setVisibility(View.VISIBLE);
                bubbleSubdescription.setText("Latitude: " + marker.getPosition().getLatitude() + " " +
                        "Longitude: " + marker.getPosition().getLongitude());

                criarVistoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, VistoriaActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mLocalizacao.setLatitude(marker.getPosition().getLatitude());
                        mLocalizacao.setLongitude(marker.getPosition().getLongitude());
                        intent.putExtra("localizacao",  mLocalizacao);
                        context.startActivity(intent);
                    }
                });


                break;
            }
            case R.layout.inf_vistoria:{
                idVistoria.setVisibility(View.VISIBLE);
                dataVistoria.setVisibility(View.VISIBLE);
                autorVistoria.setVisibility(View.VISIBLE);
                verVistoria.setVisibility(View.VISIBLE);
                adicionarVistoria.setVisibility(View.VISIBLE);
                if(((long) autorVistoria.getId() == Usuario.getInstance().getId()))
                    removerVistoria.setVisibility(View.VISIBLE);


                verVistoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("------", "we whenever getting older");
                    }
                });

                adicionarVistoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Vistoria vistoria = (Vistoria) mVistoriaDao.findById(Vistoria.class, (long) idVistoria.getId());
                        mLocalizacao = (Localizacao) mLocalizacaoDao.findById(Localizacao.class, vistoria.getLocalizacao().getId());
                        Intent intent = new Intent(context, VistoriaActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        intent.putExtra("localizacao",  mLocalizacao);
                        intent.putExtra("localizacaoVelha", true);
                        context.startActivity(intent);
                    }
                });

                removerVistoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
                        alertDialog.setTitle("Atenção");
                        alertDialog.setMessage("Se remover essa vistoria você não conseguirá ve-lá novamente");
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Vistoria vistoria = (Vistoria) mVistoriaDao.findById(Vistoria.class, (long) idVistoria.getId());
                                if(mVistoriaDao.countVistoriasWithTheFK(context, vistoria.getLocalizacao().getId()) == 1){
                                    mUsuarioVistoriaDao.delete(UsuarioVistoria.class,
                                            mUsuarioVistoriaDao.findByIdVistoria(vistoria));
                                    mFotoVistoriaDao.deleteFromVistoria(context, vistoria);
                                    mVistoriaDao.delete(Vistoria.class, mVistoriaDao.findById(Vistoria.class,
                                            vistoria.getId()));
                                    mLocalizacaoDao.delete(Localizacao.class, mLocalizacaoDao.findById(
                                            Localizacao.class, vistoria.getLocalizacao().getId()));
                                    mapView.getOverlays().remove(marker);
                                    marker.getInfoWindow().close();

                                }else{
                                    mUsuarioVistoriaDao.delete(UsuarioVistoria.class,
                                            mUsuarioVistoriaDao.findByIdVistoria(vistoria));
                                    mFotoVistoriaDao.deleteFromVistoria(context, vistoria);
                                    mVistoriaDao.delete(Vistoria.class, mVistoriaDao.findById(Vistoria.class,
                                            vistoria.getId()));
                                    mapView.getOverlays().remove(marker);
                                    marker.getInfoWindow().close();
                                }
                                if(new File(FileUtil.PATH + "vistoria/vistorias/V_" + vistoria.getId()).exists()) {
                                    FileUtil.removeAllFile(new File(FileUtil.PATH + "/vistoria/vistorias/V_" + vistoria.getId()));
                                }
                                mMapView.invalidate();
                            }
                        });
                        alertDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialog.show();
                    }
                });
                break;
            }
            default:
                break;
        }
    }
}
