package bancodados.vistoria.core.service.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.util.List;

import bancodados.vistoria.model.Usuario;

/**
 * Created by junio on 08/03/16.
 */
public class UsuarioDaoImpl extends AbstractDaoImpl{

    public UsuarioDaoImpl(Context context) {
        super(context);
    }

    public Usuario findByLoginAndPassword(String login, String password){
        List<Usuario> usuarioList = null;
        try {
            openBD();
            QueryBuilder<Usuario, Object> queryBuilder = (QueryBuilder<Usuario, Object>) dataBase.getDao(Usuario.class).queryBuilder();
            Where<Usuario, Object> where =  queryBuilder.where();
            where.eq(Usuario.COL_LOGIN, login).and().eq(Usuario.COL_PASSWORD, password);
            PreparedQuery<Usuario> preparedQuery = queryBuilder.prepare();
            usuarioList = dataBase.getDao(Usuario.class).query(preparedQuery);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            Log.d("----------", "Problema co  método findByLoginAndPassword");
        }

        if(usuarioList.isEmpty() == true){
            return null;
        }else{
            return usuarioList.get(0);
        }
    }

    public Usuario findByLogin(String login) throws java.sql.SQLException {
        List<Usuario> usuarioList;
        openBD();
        QueryBuilder<Usuario, Object> queryBuilder = (QueryBuilder<Usuario, Object>) dataBase.getDao(Usuario.class).queryBuilder();
        Where<Usuario, Object> where =  queryBuilder.where();
        where.eq(Usuario.COL_LOGIN, login);
        PreparedQuery<Usuario> preparedQuery = queryBuilder.prepare();
        usuarioList = dataBase.getDao(Usuario.class).query(preparedQuery);

        if(usuarioList.isEmpty() == true){
            return null;
        }else{
            return usuarioList.get(0);
        }
    }
}
