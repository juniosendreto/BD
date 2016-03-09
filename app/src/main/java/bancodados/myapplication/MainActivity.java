package bancodados.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import bancodados.myapplication.R;
import bancodados.myapplication.core.service.dao.UsuarioDaoImpl;
import bancodados.myapplication.model.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

        Usuario usuario = new Usuario("Junio", "39761555860", "junio", "12345", "junio.sendreto@hotmail.com",
               "SP", "rua", "4321", "12321", 1);

        Log.d("-------", usuarioDao.save(usuario)+" Oi Denovo");

    }
}
