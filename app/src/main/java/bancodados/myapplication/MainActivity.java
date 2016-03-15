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

       // UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

        UsuarioDaoImpl u1 = new UsuarioDaoImpl(MainActivity.this);
        Usuario usuario = new Usuario("Junio", "39761555860", "junio", "12345", "junio.sendreto@hotmail.com",
               "SP", "rua", "4321", "12321", 1);
        Usuario u;
        u = (Usuario) u1.save(Usuario.class, usuario);


    }
}
