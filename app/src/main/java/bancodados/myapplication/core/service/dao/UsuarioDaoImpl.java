package bancodados.myapplication.core.service.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import bancodados.myapplication.core.service.DataBase;
import bancodados.myapplication.dao.UsuarioDao;
import bancodados.myapplication.model.Usuario;

/**
 * Created by junio on 08/03/16.
 */
public class UsuarioDaoImpl implements UsuarioDao{

    private Dao<Usuario, String> usuarioDao;
    private DataBase dataBase;
    private Context context;

    public UsuarioDaoImpl() {

    }

    /* public UsuarioDaoImpl(Dao<Usuario, String> usuarioDao) {
        this.usuarioDao = usuarioDao;
    }*/

    @Override
    public int save(Usuario usuario) {

        try {

            if(dataBase == null || !dataBase.isOpen() ) {
                dataBase = new DataBase(context);

            }
            dataBase.getDao(Usuario.class).create(usuario);

        }catch (java.sql.SQLException e){
            Log.d("------------", "ERRO SALVAR USER" + e.getMessage());
            e.printStackTrace();
        }
        return 100;
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
