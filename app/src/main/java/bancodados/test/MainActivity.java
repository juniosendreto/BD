package bancodados.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import bancodados.test.core.service.dao.LocalizacaoDaoImpl;
import bancodados.test.core.service.dao.UsuarioDaoImpl;
import bancodados.test.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.test.core.service.dao.VistoriaDaoImpl;
import bancodados.test.model.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(MainActivity.this);
        LocalizacaoDaoImpl localizacaoDao = new LocalizacaoDaoImpl(MainActivity.this);
        UsuarioVistoriaDaoImpl usuarioVistoriaDao = new UsuarioVistoriaDaoImpl(MainActivity.this);
        VistoriaDaoImpl vistoriaDao = new VistoriaDaoImpl(MainActivity.this);

        //Usuario u = new Usuario("Jarvan", "39761555860", "junio", "12345", "junio.sendreto@hotmail.com", "SP", "rua", "4321", "12321", 1);
        //usuarioDao.save(Usuariemulador n funciona com junit
        // o.class, u);
        Usuario u = (Usuario) usuarioDao.findById(Usuario.class, 4l);
        u.setNome("mudei de nome");
        usuarioDao.update(Usuario.class, u);
        Usuario u2 = (Usuario) usuarioDao.findById(Usuario.class, 4l);

        Log.d("--------", "" + u2.getId() + " " + u2.getNome());

    }
}
