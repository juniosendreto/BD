package bancodados.test.core.service.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import bancodados.test.model.Usuario;
import bancodados.test.model.UsuarioVistoria;
import bancodados.test.model.Vistoria;

/**
 * Created by junio on 08/03/16.
 */
public class UsuarioVistoriaDaoImpl extends AbstractDaoImpl{


    public UsuarioVistoriaDaoImpl(Context context) {
        super(context);
    }

    public UsuarioVistoria findByIdVistoria(Vistoria vistoria) throws SQLException{
        List<UsuarioVistoria> usuarioVistoriaList = null;
        try {
            openBD();
            QueryBuilder<UsuarioVistoria, Object> queryBuilder = (QueryBuilder<UsuarioVistoria, Object>)
                    dataBase.getDao(UsuarioVistoria.class).queryBuilder();
            Where<UsuarioVistoria, Object> where =  queryBuilder.where();
            where.eq(UsuarioVistoria.COL_VISTORIA_ID, vistoria.getId());
            PreparedQuery<UsuarioVistoria> preparedQuery = queryBuilder.prepare();
            usuarioVistoriaList = dataBase.getDao(UsuarioVistoria.class).query(preparedQuery);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("----------", "Problema co  método findByIdVistoria");
        }
        if(usuarioVistoriaList.isEmpty() == true){
            return null;
        }else{
            return usuarioVistoriaList.get(0);
        }
    }


}
