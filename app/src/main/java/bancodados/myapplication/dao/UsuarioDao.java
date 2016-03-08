package bancodados.myapplication.dao;

import java.util.List;

import bancodados.myapplication.model.Usuario;

/**
 * Created by junio on 08/03/16.
 */
public interface UsuarioDao {

    public int save(Usuario usuario);

    public Usuario upgrade(Usuario usuario);

    public Boolean delete(Usuario usuario);

    public List<Usuario> listAll();

    public Usuario findById(Long id);

    public Boolean findByIdLoginAndPassword(String login, String password);


}
