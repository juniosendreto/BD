package bancodados.test.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import bancodados.test.model.Vistoria;

public class ListViewVistoriaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_vistoria);

        final ListView vistoriaLV = (ListView) findViewById(R.id.vistoriaLV);

        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getApplication());
        LocalizacaoDaoImpl localizacaoDao = new LocalizacaoDaoImpl(getApplication());
        UsuarioVistoriaDaoImpl usuarioVistoriaDao =  new UsuarioVistoriaDaoImpl(getApplicationContext());

        List<Vistoria> vistoriaList = (ArrayList) usuarioVistoriaDao.listAll(Vistoria.class);
        List<ViewHolder> viewHolders = new ArrayList<ViewHolder>();

        UsuarioVistoria usuarioVistoria;
        Usuario usuario;
        Localizacao localizacao;
        if(!(vistoriaList.isEmpty() == true)){
            for(Vistoria v: vistoriaList) {
                try {
                    ViewHolder viewHolder = new ViewHolder();
                    usuarioVistoria = usuarioVistoriaDao.findByIdVistoria(v);
                    usuario = (Usuario) usuarioDao.findById(Usuario.class, usuarioVistoria.getUsuario().getId());
                    localizacao = (Localizacao) localizacaoDao.findById(Localizacao.class, v.getLocalizacao().getId());
                    viewHolder.autor = usuario.getNome();
                    viewHolder.bairro = localizacao.getBairro();
                    viewHolder.municipio =  localizacao.getMunicipio();
                    viewHolder.data = usuarioVistoria.getData();
                    viewHolders.add(viewHolder);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        ArrayAdapter<ViewHolder> adapter = new ArrayAdapter<ViewHolder>(this, R.layout.vistoria_item, viewHolders);
        vistoriaLV.setAdapter(adapter);

    }

    private class ViewHolder{
        //Long id;
        String autor;
        String data;
        String municipio;
        String bairro;

        @Override
        public String toString(){
            return "Autor: " + this.autor + "      Data: " + this.data + "\n\nMunicípio: " + this.municipio + "      Bairro: " + this.bairro;
        }
    }
}
