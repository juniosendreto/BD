package bancodados.test.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bancodados.test.R;
import bancodados.test.core.service.dao.LocalizacaoDaoImpl;
import bancodados.test.core.service.dao.UsuarioDaoImpl;
import bancodados.test.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.test.core.service.dao.VistoriaDaoImpl;
import bancodados.test.model.Localizacao;
import bancodados.test.model.Usuario;
import bancodados.test.model.UsuarioVistoria;
import bancodados.test.model.ViewHolder;
import bancodados.test.model.Vistoria;

public class ListViewVistoriaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_vistoria);

        final ListView vistoriaLV = (ListView) findViewById(R.id.vistoriaLV);
        final AlertDialog.Builder alert = new AlertDialog.Builder(ListViewVistoriaActivity.this);
        final Intent intent =  new Intent(this, VistoriaActivity.class);
        final Intent intentMainActivivty = new Intent(this, MainActivity.class);
        final Usuario usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");

        final UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getApplication());
        final LocalizacaoDaoImpl localizacaoDao = new LocalizacaoDaoImpl(getApplicationContext());
        final UsuarioVistoriaDaoImpl usuarioVistoriaDao =  new UsuarioVistoriaDaoImpl(getApplicationContext());
        final VistoriaDaoImpl vistoriaDao =  new VistoriaDaoImpl(getApplicationContext());


        List<Vistoria> vistoriaList = (ArrayList) usuarioVistoriaDao.listAll(Vistoria.class);
        final List<ViewHolder> viewHolders = new ArrayList<ViewHolder>();

        UsuarioVistoria usuarioVistoria;
        Usuario usuario;
        Localizacao localizacao;
        ArrayAdapter<ViewHolder> adapter;

        if(!(vistoriaList.isEmpty() == true)){
            for(Vistoria v: vistoriaList) {
                try {
                    ViewHolder viewHolder = new ViewHolder();
                    usuarioVistoria = usuarioVistoriaDao.findByIdVistoria(v);
                    Log.d("-----", usuarioVistoria.getData());
                    Log.d("-----", usuarioVistoria.getId() +"");
                    Log.d("-----", usuarioVistoria.getUsuario().getId()+ "");
                    Log.d("-----", usuarioVistoria.getVistoria().getId() + "");
                    usuario = (Usuario) usuarioDao.findById(Usuario.class, usuarioVistoria.getUsuario().getId());
                    localizacao = (Localizacao) localizacaoDao.findById(Localizacao.class, v.getLocalizacao().getId());

                    viewHolder.setIdUsuario(usuario.getId());
                    viewHolder.setIdUsuarioVistoria(usuarioVistoria.getId());
                    viewHolder.setIdVistoria(v.getId());
                    viewHolder.setIdLocalizacao(localizacao.getId());
                    viewHolder.setAutor(usuario.getNome());
                    viewHolder.setBairro(localizacao.getBairro());
                    viewHolder.setMunicipio(localizacao.getMunicipio());
                    viewHolder.setData(usuarioVistoria.getData());
                    viewHolders.add(viewHolder);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter = new ArrayAdapter<ViewHolder>(this, R.layout.vistoria_item, viewHolders);
                vistoriaLV.setAdapter(adapter);
            }

        }else{

            alert.setTitle("Alerta");
            alert.setMessage("Não há nenhuma vistoria no Banco de dados!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        intentMainActivivty.putExtra("usuarioLogado", getIntent().getSerializableExtra("usuarioLogado"));
                        startActivity(intentMainActivivty);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).show();


        }

        vistoriaLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Vistoria vistoria = (Vistoria) vistoriaDao.findById(Vistoria.class , viewHolders.get(position).getIdVistoria());
                //Usuario usuario1 = (Usuario) usuarioDao.findById(Usuario.class, viewHolders.get(position).getIdUsuario());
                Localizacao localizacao1 = (Localizacao) localizacaoDao.findById(Localizacao.class, viewHolders.get(position).getIdLocalizacao());
                intent.putExtra("vistoria", vistoria);
                intent.putExtra("usuarioLogado", usuarioLogado);
                intent.putExtra("localizacao", localizacao1);
                startActivity(intent);
                return true;
            }
        });


    }

}
