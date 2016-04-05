package bancodados.myapplication;

import android.util.Log;

import bancodados.myapplication.core.service.dao.UsuarioDaoImpl;
import bancodados.myapplication.model.Usuario;


/**
 * Created by junio on 18/03/16.
 */
public class UsuarioDaoTest extends ConfingBDTestCase{

    public void testSave(){

        Log.d("------------------", getContext() + "");
        UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("jakson", "39761555860", "junio", "12345", "junio.sendreto@hotmail.com", "SP", "rua", "4321", "12321", 1);
        usuarioDao.save(Usuario.class, actualUsuario);

        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, usuarioDao.countAllRows(Usuario.class));

        //Log.d(actualUsuario.getNome(), expectedUsuario.getNome());
        assertEquals(actualUsuario.getNome(), expectedUsuario.getNome());
        //assertEquals(true, true);

    }

    public void updateTest(){

    }

    public void deleteTest(){

    }

    public void listAlltest(){

    }

    public void findByIdTest(){

    }

    public void countAllRowsTest(){

    }
}
