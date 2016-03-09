package bancodados.myapplication.core.service.dao;

import java.util.List;

import bancodados.myapplication.dao.UsuarioDao;
import bancodados.myapplication.model.Usuario;

/**
 * Created by junio on 08/03/16.
 */
public class UsuarioVistoriaDaoImpl implements UsuarioDao{

    @Override
    public int save(Usuario usuario) {

        return 1;
    }

    @Override
    public Usuario upgrade(Usuario usuario) {
        return null;
    }

    @Override
    public Boolean delete(Usuario usuario) {
        return null;
    }

    @Override
    public List<Usuario> listAll() {
        return null;
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }

    @Override
    public Boolean findByIdLoginAndPassword(String login, String password) {
        return null;
    }
}
