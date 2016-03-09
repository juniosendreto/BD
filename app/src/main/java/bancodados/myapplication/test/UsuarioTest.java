package bancodados.myapplication.test;

import android.test.InstrumentationTestCase;
import android.util.Log;

import bancodados.myapplication.core.service.dao.UsuarioDaoImpl;
import bancodados.myapplication.model.Usuario;

/**
 * Created by junio on 08/03/16.
 */
public class UsuarioTest extends InstrumentationTestCase{

    public void saveTest(){
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        Usuario usuario = new Usuario("Junio", "12345678910", "junio", "12345",
                "junio.sendreto@hotmail.com", "SP", "rua", "4321", "12321", 1);
        Log.d("------------------", usuarioDao.save(usuario) + "");
        assertEquals(usuarioDao.save(usuario), 100);

    }


}
