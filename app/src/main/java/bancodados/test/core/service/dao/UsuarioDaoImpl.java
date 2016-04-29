package bancodados.test.core.service.dao;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.util.List;

import bancodados.test.model.Usuario;

/**
 * Created by junio on 08/03/16.
 */
public class UsuarioDaoImpl extends AbstractDaoImpl{

    public UsuarioDaoImpl(Context context) {
        super(context);
    }

    public Object findByLoginAndPassword(String login, String password){
        try {
            QueryBuilder<Usuario, String> qb = (QueryBuilder<Usuario, String>) dataBase.getDao(Usuario.class).queryBuilder();
            Where where =  qb.where();
            where.eq(Usuario.COL_LOGIN, login).and().eq(Usuario.COL_PASSWORD, password);
            PreparedQuery<Usuario> pq = qb.prepare();
            return null;

        } catch (java.sql.SQLException e) {
            Log.d("----------", "Problema co  método findByLoginAndPassword");
            return null;
        }
    }
}
