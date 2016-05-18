package bancodados.test.core.service.dao;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.util.ArrayList;
import java.util.List;

import bancodados.test.model.Usuario;

/**
 * Created by junio on 08/03/16.
 */
public class UsuarioDaoImpl extends AbstractDaoImpl{

    public UsuarioDaoImpl(Context context) {
        super(context);
    }

    public List<Usuario> findByLoginAndPassword(String login, String password){
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
            return usuarioList;
        }
    }

    public List<Usuario> findByLogin(String login) throws java.sql.SQLException {
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
            return usuarioList;
        }
    }
}
