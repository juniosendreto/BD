package bancodados.vistoria.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.MarkerInfoWindow;
import org.osmdroid.views.MapView;

import java.io.File;
import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.activities.VistoriaActivity;
import bancodados.vistoria.core.service.dao.FotoVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.LocalizacaoDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioDaoImpl;
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

    private Integer mControlador;
    private Long idVistoria;

    private Context context;

    private Usuario mUsuario;
    private UsuarioVistoria mUsuarioVistoria;
    private Localizacao mLocalizacao;

    private UsuarioDaoImpl mUsuarioDao;
    private UsuarioVistoriaDaoImpl mUsuarioVistoriaDao;
    private VistoriaDaoImpl mVistoriaDao;
    private LocalizacaoDaoImpl mLocalizacaoDao;
    private FotoVistoriaDaoImpl mFotoVistoriaDao;

    private List<Vistoria> mVistorias;

    private TextView bubbleSubdescription = (TextView) mView.findViewById(R.id.bubble_subdescription);
    private TextView bubbleTitle = (TextView) mView.findViewById(R.id.bubble_title);
    private Button criarVistoria = (Button) mView.findViewById(R.id.criarVistoria);

    private TextView dataVistoria = (TextView) mView.findViewById(R.id.dataVistoria);
    private TextView autorVistoria = (TextView) mView.findViewById(R.id.autorVistoria);
    private Button verVistoria = (Button) mView.findViewById(R.id.verVistoria);
    private Button adicionarVistoria = (Button) mView.findViewById(R.id.adicionarVistoria);
    private Button removerVistoria = (Button) mView.findViewById(R.id.removerVistoria);
    private ImageButton setaEsquerda = (ImageButton) mView.findViewById(R.id.setaEsquerda);
    private TextView indice = (TextView) mView.findViewById(R.id.indice);
    private ImageButton setaDireita = (ImageButton) mView.findViewById(R.id.setaDireita);


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

         mControlador = 0;

        final Marker marker = (Marker) item;
        mUsuario = new Usuario();
        mUsuarioVistoria = new UsuarioVistoria();
        mLocalizacao = new Localizacao();
        mUsuarioDao = new UsuarioDaoImpl(context);
        mUsuarioVistoriaDao = new UsuarioVistoriaDaoImpl(context);
        mVistoriaDao = new VistoriaDaoImpl(context);
        mLocalizacaoDao = new LocalizacaoDaoImpl(context);
        mFotoVistoriaDao = new FotoVistoriaDaoImpl(context);

        PointMap.closeAllMarkInf(mapView.getOverlays(), marker);

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
                        intent.putExtra("tipoVistoria", 0);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case R.layout.inf_vistoria:{
                mVistorias = mVistoriaDao.findByFK((long) marker.getInfoWindow().getView().getId());


                dataVistoria.setVisibility(View.VISIBLE);
                autorVistoria.setVisibility(View.VISIBLE);
                verVistoria.setVisibility(View.VISIBLE);
                adicionarVistoria.setVisibility(View.VISIBLE);

                mUsuarioVistoria = mUsuarioVistoriaDao.findByIdVistoria(mVistorias.get(0));
                mUsuario = (Usuario) mUsuarioDao.findById(Usuario.class, mUsuarioVistoria.getUsuario().getId());
                mLocalizacao = (Localizacao) mLocalizacaoDao.findById(Localizacao.class, mVistorias.get(0).getLocalizacao().getId());
                dataVistoria.setText("Data: " + mUsuarioVistoria.getData());
                autorVistoria.setText("Autor: " + mUsuario.getNome());
                idVistoria = mVistorias.get(0).getId();
                if(mUsuario.getId() == Usuario.getInstance().getId())
                    removerVistoria.setVisibility(View.VISIBLE);
                else
                    removerVistoria.setVisibility(View.INVISIBLE);

                indice.setText("1/" + mVistorias.size());

                if(mVistorias.size() > 1 && mControlador == 0){
                    setaEsquerda.setVisibility(View.INVISIBLE);
                    setaDireita.setVisibility(View.VISIBLE);
                }else if(mVistorias.size() == 1){
                    setaEsquerda.setVisibility(View.INVISIBLE);
                    setaDireita.setVisibility(View.INVISIBLE);
                }else{
                    setaEsquerda.setVisibility(View.VISIBLE);
                    setaDireita.setVisibility(View.INVISIBLE);
                }

                setaEsquerda.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mControlador-=1;
                        if(mControlador-1  == -1){
                            setaEsquerda.setVisibility(View.INVISIBLE);
                            setaDireita.setVisibility(View.VISIBLE);
                        }else{
                            setaEsquerda.setVisibility(View.VISIBLE);
                            setaDireita.setVisibility(View.VISIBLE);
                        }
                        indice.setText(mControlador+1  +"/" + mVistorias.size());
                        mUsuarioVistoria = mUsuarioVistoriaDao.findByIdVistoria(mVistorias.get(mControlador));
                        mUsuario = (Usuario) mUsuarioDao.findById(Usuario.class, mUsuarioVistoria.getUsuario().getId());
                        mLocalizacao = (Localizacao) mLocalizacaoDao.findById(Localizacao.class, mVistorias.get(mControlador).getLocalizacao().getId());
                        dataVistoria.setText("Data: " + mUsuarioVistoria.getData());
                        autorVistoria.setText("Autor: " + mUsuario.getNome());
                        idVistoria = mVistorias.get(mControlador).getId();
                        if(mUsuario.getId() == Usuario.getInstance().getId())
                            removerVistoria.setVisibility(View.VISIBLE);
                        else
                            removerVistoria.setVisibility(View.INVISIBLE);
                    }
                });

                setaDireita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mControlador+= 1;

                        if(mControlador+1 > mVistorias.size()){
                            setaEsquerda.setVisibility(View.VISIBLE);
                            setaDireita.setVisibility(View.INVISIBLE);
                        }else{
                            setaEsquerda.setVisibility(View.VISIBLE);
                            setaDireita.setVisibility(View.VISIBLE);
                        }

                        indice.setText(mControlador+1 +"/" + mVistorias.size());
                        mUsuarioVistoria = mUsuarioVistoriaDao.findByIdVistoria(mVistorias.get(mControlador));

                        mUsuario = (Usuario) mUsuarioDao.findById(Usuario.class, mUsuarioVistoria.getUsuario().getId());
                        mLocalizacao = (Localizacao) mLocalizacaoDao.findById(Localizacao.class, mVistorias.get(mControlador).getLocalizacao().getId());
                        dataVistoria.setText("Data: " + mUsuarioVistoria.getData());
                        autorVistoria.setText("Autor: " + mUsuario.getNome());
                        if(mControlador+1  == mVistorias.size()){
                            setaEsquerda.setVisibility(View.VISIBLE);
                            setaDireita.setVisibility(View.INVISIBLE);
                        }
                        idVistoria = mVistorias.get(mControlador).getId();
                        if(mUsuario.getId() == Usuario.getInstance().getId())
                            removerVistoria.setVisibility(View.VISIBLE);
                        else
                            removerVistoria.setVisibility(View.INVISIBLE);
                    }
                });

                verVistoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Vistoria vistoria = (Vistoria) mVistoriaDao.findById(Vistoria.class, idVistoria);
                        mLocalizacao = (Localizacao) mLocalizacaoDao.findById(Localizacao.class, vistoria.getLocalizacao().getId());
                        Intent intent = new Intent(context, VistoriaActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("localizacao",  mLocalizacao);
                        intent.putExtra("tipoVistoria", 2);
                        intent.putExtra("vistoria",  vistoria);
                        context.startActivity(intent);
                    }
                });

                adicionarVistoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Vistoria vistoria = (Vistoria) mVistoriaDao.findById(Vistoria.class, idVistoria);
                        mLocalizacao = (Localizacao) mLocalizacaoDao.findById(Localizacao.class, vistoria.getLocalizacao().getId());
                        Intent intent = new Intent(context, VistoriaActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("localizacao",  mLocalizacao);
                        intent.putExtra("tipoVistoria", 1);
                        context.startActivity(intent);
                    }
                });

                removerVistoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
                        alertDialog.setTitle("Atenção");
                        alertDialog.setMessage("Se remover essa vistoria você não conseguirá ve-lá novamente!");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Vistoria vistoria = (Vistoria) mVistoriaDao.findById(Vistoria.class, idVistoria);
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
            default: {
                break;
            }
        }
    }
}
