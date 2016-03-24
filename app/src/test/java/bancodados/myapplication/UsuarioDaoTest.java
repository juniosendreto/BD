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

    public void testSave(){
        UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(context);
        Usuario actualUsuario = new Usuario("jak", "39761555860", "junio", "12345", "junio.sendreto@hotmail.com", "SP", "rua", "4321", "12321", 1);
        usuarioDao.save(Usuario.class, actualUsuario);

        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, usuarioDao.countAllRows(Usuario.class));
        assertEquals(actualUsuario, expectedUsuario);

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
