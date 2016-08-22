package bancodados.vistoria;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.core.service.dao.UsuarioDaoImpl;
import bancodados.vistoria.model.Usuario;

/**
 * Created by junio on 05/04/16.
 */
public class UsuarioDaoImplTest extends ConfigBDTestCase{

   public void testSave(){
        Long Id;
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        usuarioDao.save(Usuario.class, actualUsuario);
        Id = actualUsuario.getId();
        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, Id);
        assertEquals(actualUsuario, expectedUsuario);
        usuarioDao.delete(Usuario.class, expectedUsuario);

   }

    public void testUpdate() throws Exception {
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario normalUsuario = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        usuarioDao.save(Usuario.class, normalUsuario);
        Usuario actualUsuario = (Usuario) usuarioDao.findById(Usuario.class, normalUsuario.getId());

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
        assertEquals(actualUsuario, expectedUsuario);
        usuarioDao.delete(Usuario.class, actualUsuario);
    }



    public void testDelete(){
        Long Id;
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        usuarioDao.save(Usuario.class, actualUsuario);
        Id = actualUsuario.getId();
        usuarioDao.delete(Usuario.class, actualUsuario);
        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, Id);
        assertEquals(true, expectedUsuario == null);
    }

    public void testListAll(){
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario usuario1 = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        Usuario usuario2 = new Usuario("Jarvan", "00000000000", "Jarvan", "12345", "jarvan@hotmail.com", "SJC", "Guaraciaba", "11111111", "222222222", 1);
        Usuario usuario3 = new Usuario("Fiora", "00000000000", "Fiora", "12345", "fiora@hotmail.com", "SJC", "Caparaó", "11111111", "222222222", 1);
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

        usuarioDao.delete(Usuario.class, usuario1);
        usuarioDao.delete(Usuario.class, usuario2);
        usuarioDao.delete(Usuario.class, usuario3);
    }

    public void testFindById(){
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        usuarioDao.save(Usuario.class, actualUsuario);
        Usuario expectedUsuario = (Usuario) usuarioDao.findById(Usuario.class, actualUsuario.getId());

        assertEquals(actualUsuario.getId(), expectedUsuario.getId());

        usuarioDao.delete(Usuario.class, actualUsuario);
    }

    public void testFindByLoginAndPassword(){
        UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        usuarioDao.save(Usuario.class, actualUsuario);

        Usuario expectedUsuario = usuarioDao.findByLoginAndPassword(actualUsuario.getLogin(), actualUsuario.getPassword());

        assertEquals(actualUsuario.getLogin(), expectedUsuario.getLogin());

        usuarioDao.delete(Usuario.class, actualUsuario);

    }

    public void testFindByLogin() throws SQLException {
        UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(getContext());
        Usuario actualUsuario = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        usuarioDao.save(Usuario.class, actualUsuario);

        Usuario expectedUsuario = usuarioDao.findByLogin(actualUsuario.getLogin());

        assertEquals(actualUsuario.getLogin(), expectedUsuario.getLogin());

        usuarioDao.delete(Usuario.class, actualUsuario);

    }

    public void testCountAllRows(){
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario usuario1 = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        Usuario usuario2 = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        Usuario usuario3 = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);

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

    public void testSaveAll(){
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        Usuario usuario1 = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        Usuario usuario2 = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);
        Usuario usuario3 = new Usuario("Junio", "00000000000", "junio", "12345", "junio@hotmail.com", "SJC", "Uberaba", "11111111", "222222222", 1);

        List<Usuario> usersActual = new ArrayList<>();
        usersActual.add(usuario1);
        usersActual.add(usuario2);
        usersActual.add(usuario3);

        usuarioDao.saveAll(Usuario.class, (ArrayList) usersActual);

        List<Usuario> usersExpected = (ArrayList) usuarioDao.listAll(Usuario.class);

        for(int i = 0; i < 3; i++)
            assertEquals(usersActual.get(i), usersExpected.get(i));

        usuarioDao.delete(Usuario.class, usuario1);
        usuarioDao.delete(Usuario.class, usuario2);
        usuarioDao.delete(Usuario.class, usuario3);
    }
}
