package bancodados.myapplication.test;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.util.Log;

import bancodados.myapplication.core.service.dao.AbstractDaoImpl;
import bancodados.myapplication.core.service.dao.UsuarioDaoImpl;
import bancodados.myapplication.model.Usuario;

/**
 * Created by junio on 11/03/16.
 */
public class UsuarioDaoTest extends InstrumentationTestCase {

    public void saveTest(){
        Context context = null;
        //UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(this);

        Usuario usuario = new Usuario("Junio", "12345678910", "junio", "12345",
                "junio.sendreto@hotmail.com", "SP", "rua", "4321", "12321", 1);
        Log.d("------------------", "xxxxxx");
       // Usuario u1 = (Usuario) usuarioDao.save(Usuario.class, usuario);
       // assertEquals(u1, usuario);

    }

    public void updateTest(){

    }

    public void deleteTest(){

    }

    public void listAllTest(){

    }

    public void findByIdTest(){

    }

}
