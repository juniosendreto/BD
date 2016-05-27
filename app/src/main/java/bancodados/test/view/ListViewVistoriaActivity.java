package bancodados.test.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import bancodados.test.R;
import bancodados.test.core.service.dao.LocalizacaoDaoImpl;
import bancodados.test.core.service.dao.UsuarioDaoImpl;
import bancodados.test.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.test.core.service.dao.VistoriaDaoImpl;
import bancodados.test.model.Localizacao;
import bancodados.test.model.Usuario;
import bancodados.test.model.Vistoria;

public class ListViewVistoriaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_vistoria);

        Context context;

        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getApplication());
        UsuarioVistoriaDaoImpl usuarioVistoriaDao =  new UsuarioVistoriaDaoImpl(getApplicationContext());
        VistoriaDaoImpl vistoriaDao =  new VistoriaDaoImpl(getApplicationContext());
        LocalizacaoDaoImpl localizacaoDao = new LocalizacaoDaoImpl(getApplicationContext());

        List<Usuario> usuarioList = (ArrayList) usuarioVistoriaDao.listAll(Usuario.class);
        List<UsuarioVistoriaDaoImpl> usuarioVistoriaDaoList = (ArrayList) usuarioVistoriaDao.listAll(UsuarioVistoriaDaoImpl.class);
        List<Vistoria> vistoriaList = (ArrayList) usuarioVistoriaDao.listAll(Vistoria.class);
        List<Localizacao> localizacaoList = (ArrayList) localizacaoDao.listAll(Localizacao.class);

        for(Vistoria v: vistoriaList){
            List<ViewHolder> viewHolders = new ArrayList<ViewHolder>();

        }

    }

    private class ViewHolder{
        Long id;
        String autor;
        String data;
        String municipio;
        String bairro;
    }
}
