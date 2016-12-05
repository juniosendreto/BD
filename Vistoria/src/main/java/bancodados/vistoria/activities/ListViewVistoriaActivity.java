package bancodados.vistoria.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.Util.FileUtil;
import bancodados.vistoria.Util.VistoriaListAdapter;
import bancodados.vistoria.core.service.dao.FotoVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.LocalizacaoDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.VistoriaDaoImpl;
import bancodados.vistoria.model.Localizacao;
import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.model.UsuarioVistoria;
import bancodados.vistoria.model.ViewHolderVistoria;
import bancodados.vistoria.model.Vistoria;

public class ListViewVistoriaActivity extends Activity {

    private List<ViewHolderVistoria> viewHolderVistorias;
    ListView vistoriaLV;
    Intent intentMainActivivty;

    Localizacao mLocalizacao;

    UsuarioDaoImpl mUsuarioDao;
    LocalizacaoDaoImpl mLocalizacaoDao;
    UsuarioVistoriaDaoImpl mUsuarioVistoriaDao;
    VistoriaDaoImpl mVistoriaDao;
    FotoVistoriaDaoImpl mFotoVistoriaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_vistoria);

        vistoriaLV = (ListView) findViewById(R.id.vistoriaLV);
        final AlertDialog.Builder alert = new AlertDialog.Builder(ListViewVistoriaActivity.this);
        final Intent intent =  new Intent(this, VistoriaActivity.class);
        intentMainActivivty = new Intent(this, MainActivity.class);

        mLocalizacao = new Localizacao();
        mUsuarioDao = new UsuarioDaoImpl(getApplication());
        mLocalizacaoDao = new LocalizacaoDaoImpl(getApplicationContext());
        mUsuarioVistoriaDao =  new UsuarioVistoriaDaoImpl(getApplicationContext());
        mVistoriaDao =  new VistoriaDaoImpl(getApplicationContext());
        mFotoVistoriaDao = new FotoVistoriaDaoImpl(getApplicationContext());


        List<Vistoria> vistorias = (ArrayList) mVistoriaDao.listAll(Vistoria.class);
        viewHolderVistorias = new ArrayList<>();
        UsuarioVistoria usuarioVistoria;
        Usuario usuario;
        Localizacao localizacao;

        if(!(vistorias.isEmpty())) {
            for (Vistoria v: vistorias) {
                ViewHolderVistoria viewHolderVistoria = new ViewHolderVistoria();
                usuarioVistoria = mUsuarioVistoriaDao.findByIdVistoria(v);
                usuario = (Usuario) mUsuarioDao.findById(Usuario.class, usuarioVistoria.getUsuario().getId());
                localizacao = (Localizacao) mLocalizacaoDao.findById(Localizacao.class, v.getLocalizacao().getId());
                viewHolderVistoria.setIdUsuario(usuario.getId());
                viewHolderVistoria.setIdUsuarioVistoria(usuarioVistoria.getId());
                viewHolderVistoria.setIdVistoria(v.getId());
                viewHolderVistoria.setIdLocalizacao(localizacao.getId());
                viewHolderVistoria.setAutor("Autor: " + usuario.getNome() + "  ");
                viewHolderVistoria.setData("Data: " + usuarioVistoria.getData());
                viewHolderVistoria.setMunicipio("Municipio: " + localizacao.getMunicipio() + "  ");
                viewHolderVistoria.setBairro("Bairro: " + localizacao.getBairro());
                viewHolderVistorias.add(viewHolderVistoria);
            }
            VistoriaListAdapter adapter = new VistoriaListAdapter(ListViewVistoriaActivity.this, R.layout.vistoria_vistorias, viewHolderVistorias);
            vistoriaLV.setAdapter(adapter);
        }else{
            alert.setTitle("Alerta");
            alert.setMessage("Não há nenhuma vistoria no Banco de dados!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        startActivity(intentMainActivivty);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).show();

        }

    }

    public void verVistoria(View view){
        ViewHolderVistoria viewHolderVistoria = (ViewHolderVistoria)  view.getTag();
        Vistoria vistoria = (Vistoria) mVistoriaDao.findById(Vistoria.class, viewHolderVistoria.getIdVistoria());
        mLocalizacao = (Localizacao) mLocalizacaoDao.findById(Localizacao.class, vistoria.getLocalizacao().getId());
        Intent intent = new Intent(getApplicationContext(), VistoriaActivity.class);
        intent.putExtra("localizacao",  mLocalizacao);
        intent.putExtra("vistoria",  vistoria);
        intent.putExtra("localizacaoVelha", true);
        intent.putExtra("tipoVistoria", 2);
        startActivity(intent);

    }

    public void removerVistoria(View view){
        final ViewHolderVistoria viewHolderVistoria = (ViewHolderVistoria) view.getTag();
        AlertDialog.Builder alertRemover = new AlertDialog.Builder(ListViewVistoriaActivity.this);
        AlertDialog.Builder alertVazio = new AlertDialog.Builder(ListViewVistoriaActivity.this);

        alertRemover.setTitle("Alerta")
                .setMessage("Você realmente deseja excluir essa vistoria?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewHolderVistorias.remove(viewHolderVistoria);
                        vistoriaLV.invalidateViews();
                        Vistoria vistoria = (Vistoria) mVistoriaDao.findById(Vistoria.class, viewHolderVistoria.getIdVistoria());
                        if(mVistoriaDao.countVistoriasWithTheFK(getApplicationContext(), vistoria.getLocalizacao().getId()) == 1){
                            mUsuarioVistoriaDao.delete(UsuarioVistoria.class,
                                    mUsuarioVistoriaDao.findByIdVistoria(vistoria));
                            mFotoVistoriaDao.deleteFromVistoria(getApplicationContext(), vistoria);
                            mVistoriaDao.delete(Vistoria.class, mVistoriaDao.findById(Vistoria.class,
                                    vistoria.getId()));
                            mLocalizacaoDao.delete(Localizacao.class, mLocalizacaoDao.findById(
                                    Localizacao.class, vistoria.getLocalizacao().getId()));
                        }else{
                            mUsuarioVistoriaDao.delete(UsuarioVistoria.class,
                                    mUsuarioVistoriaDao.findByIdVistoria(vistoria));
                            mFotoVistoriaDao.deleteFromVistoria(getApplicationContext(), vistoria);
                            mVistoriaDao.delete(Vistoria.class, mVistoriaDao.findById(Vistoria.class,
                                    vistoria.getId()));
                        }
                        if(new File(FileUtil.PATH + "vistoria/vistorias/V_" + vistoria.getId()).exists()) {
                            FileUtil.removeAllFile(new File(FileUtil.PATH + "/vistoria/vistorias/V_" + vistoria.getId()));
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();

        if(viewHolderVistorias.isEmpty()){
            alertVazio.setTitle("Alerta")
                    .setMessage("Não há mais vistoria no banco de dados!").setCancelable(false)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(intentMainActivivty);
                            dialog.dismiss();
                        }
                    }).show();
        }
    }
}
