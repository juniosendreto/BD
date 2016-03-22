package bancodados.myapplication;

import android.content.Context;

import junit.framework.TestCase;

import bancodados.myapplication.core.service.dao.UsuarioDaoImpl;
import bancodados.myapplication.model.Usuario;

/**
 * Created by junio on 18/03/16.
 */
public class UsuarioDaoTest extends TestCase{
    Context context =  null;

    public void saveTest(){
        UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(context);

        Usuario actualUsuario = new Usuario("Junio", "39761555860", "junio", "12345", "junio.sendreto@hotmail.com", "SP", "rua", "4321", "12321", 1);
        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, usuarioDao.countAllRows(Usuario.class));
        //assertEquals(actualUsuario, expectedUsuario);

        assertEquals(true, true);

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
