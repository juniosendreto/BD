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
public class UsuarioDaoImpl extends AbstractDaoImpl{

    public UsuarioDaoImpl(Context context) {
        super(context);
    }
}
