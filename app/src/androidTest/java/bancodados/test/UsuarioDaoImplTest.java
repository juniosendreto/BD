package bancodados.test;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodados.test.core.service.dao.UsuarioDaoImpl;
import bancodados.test.model.Usuario;

/**
 * Created by junio on 05/04/16.
 */
public class UsuarioDaoImplTest extends ConfigBDTestCase{

   public void testSave(){
        Long Id;
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("carro", "123445546", "junio", "12345", "jax@jax", "SP", "rua", "4321", "12321", 1);
        usuarioDao.save(Usuario.class, actualUsuario);
        Id = actualUsuario.getId();
        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, Id);
        assertEquals(actualUsuario, expectedUsuario);
        usuarioDao.delete(Usuario.class, expectedUsuario);

   }

    public void testUpdate() throws Exception {
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario normalUsuario = new Usuario("jax", "123445546", "jax", "12345", "jax@jax", "SP", "rua", "4321", "12321", 1);
        usuarioDao.save(Usuario.class, normalUsuario);
        Usuario actualUsuario = (Usuario) usuarioDao.findById(Usuario.class, normalUsuario.getId());

        Log.d("------------", actualUsuario.getNome());
        Usuario newFieldsUsuario = new Usuario("jaxson", "12345", "jaxson@jaxson", "SJC", "avenida", "111222", "222111");

        if(!(newFieldsUsuario.getNome() == null)){
            actualUsuario.setNome(newFieldsUsuario.getNome());
        }
        if(!(newFieldsUsuario.getPassword() == null)){
            actualUsuario.setPassword(newFieldsUsuario.getPassword());
        }
        if(!(newFieldsUsuario.getEmail() == null)){
            actualUsuario.setEmail(newFieldsUsuario.getEmail());
        }
        if(!(newFieldsUsuario.getMunicipio() == null)){
            actualUsuario.setMunicipio(newFieldsUsuario.getMunicipio());
        }
        if(!(newFieldsUsuario.getEndereco() == null)){
            actualUsuario.setEndereco(newFieldsUsuario.getEndereco());
        }
        if(!(newFieldsUsuario.getTelefone() == null)){
            actualUsuario.setTelefone(newFieldsUsuario.getTelefone());
        }
        if(!(newFieldsUsuario.getCelular() == null)){
            actualUsuario.setCelular(newFieldsUsuario.getCelular());
        }

        usuarioDao.update(Usuario.class, actualUsuario);

        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, actualUsuario.getId());
        Log.d("------------", expectedUsuario.getNome());

        assertEquals(actualUsuario, expectedUsuario);

        usuarioDao.delete(Usuario.class, actualUsuario);
    }



    public void testDelete(){
        Long Id;
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("carro", "123445546", "junio", "12345", "jax@jax", "SP", "rua", "4321", "12321", 1);
        usuarioDao.save(Usuario.class, actualUsuario);
        Id = actualUsuario.getId();
        usuarioDao.delete(Usuario.class, actualUsuario);
        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, Id);
        assertEquals(true, expectedUsuario == null);
    }

    public void testListAll(){
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Boolean expected = true, actual = true;
        Usuario usuario1 = new Usuario("carlos", "123445546", "carlos", "12345", "carlos@carlos", "SP", "rua", "4321", "12321", 1);
        Usuario usuario2 = new Usuario("jarvan", "123445546", "jarvan", "12345", "jarvan@jarvan", "SP", "rua", "4321", "12321", 1);
        Usuario usuario3 = new Usuario("fiora", "123445546", "fiora", "12345", "fiora@fiora", "SP", "rua", "4321", "12321", 1);
        List<Usuario> listFixa = new ArrayList<Usuario>();
        listFixa.add(usuario1);
        listFixa.add(usuario2);
        listFixa.add(usuario3);
        usuarioDao.save(Usuario.class, usuario1);
        usuarioDao.save(Usuario.class, usuario2);
        usuarioDao.save(Usuario.class, usuario3);
        List<Usuario> listBD = (ArrayList) usuarioDao.listAll(Usuario.class);

        for(int i = 0; i < listFixa.size(); i++){
            assertEquals(listBD.get(i).getNome().equals(listFixa.get(i).getNome()), true);
        }
        // assertEquals(actual, expected);
        usuarioDao.delete(Usuario.class, usuario1);
        usuarioDao.delete(Usuario.class, usuario2);
        usuarioDao.delete(Usuario.class, usuario3);
    }

    public void testFindById(){
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("jax", "123445546", "jax", "12345", "jax@jax", "SP", "rua", "4321", "12321", 1);
        usuarioDao.save(Usuario.class, actualUsuario);
        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, actualUsuario.getId());

        assertEquals(actualUsuario.getId(), expectedUsuario.getId());

        usuarioDao.delete(Usuario.class, actualUsuario);
    }

    public void testCountAllRows(){
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario usuario1 = new Usuario("carlos", "123445546", "carlos", "12345", "carlos@carlos", "SP", "rua", "4321", "12321", 1);
        Usuario usuario2 = new Usuario("jarvan", "123445546", "jarvan", "12345", "jarvan@jarvan", "SP", "rua", "4321", "12321", 1);
        Usuario usuario3 = new Usuario("fiora", "123445546", "fiora", "12345", "fiora@fiora", "SP", "rua", "4321", "12321", 1);

        usuarioDao.save(Usuario.class, usuario1);
        usuarioDao.save(Usuario.class, usuario2);
        usuarioDao.save(Usuario.class, usuario3);

        Long actualQuantity = usuarioDao.countAllRows(Usuario.class);
        Long expectedQuantity = 3l;

        assertEquals(actualQuantity, expectedQuantity);

        usuarioDao.delete(Usuario.class, usuario1);
        usuarioDao.delete(Usuario.class, usuario2);
        usuarioDao.delete(Usuario.class, usuario3);
    }

    public void testFindByLoginAndPassword(){
        UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("tibers2", "123445546", "carlos1", "12345", "carlos@carlos", "SP", "rua", "4321", "12321", 1);
        usuarioDao.save(Usuario.class, actualUsuario);

        Usuario expectedUsuario = usuarioDao.findByLoginAndPassword(actualUsuario.getLogin(), actualUsuario.getPassword());

        assertEquals(actualUsuario.getLogin(), expectedUsuario.getLogin());

        usuarioDao.delete(Usuario.class, actualUsuario);

    }

    public void testFindByLogin() throws SQLException {
        UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("tibers2", "123445546", "carlos1", "12345", "carlos@carlos", "SP", "rua", "4321", "12321", 1);
        usuarioDao.save(Usuario.class, actualUsuario);

        Usuario expectedUsuario = usuarioDao.findByLogin(actualUsuario.getLogin());

        assertEquals(actualUsuario.getLogin(), expectedUsuario.getLogin());

        usuarioDao.delete(Usuario.class, actualUsuario);

    }
}
