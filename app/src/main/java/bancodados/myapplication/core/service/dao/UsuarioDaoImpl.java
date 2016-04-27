package bancodados.myapplication.core.service.dao;

import android.content.Context;
import android.database.SQLException;

import bancodados.myapplication.model.Usuario;

/**
 * Created by junio on 08/03/16.
 */
public class UsuarioDaoImpl extends AbstractDaoImpl{

    public UsuarioDaoImpl(Context context) {
        super(context);
    }

    public Usuario findByLoginAndPassword(Usuario usuario){
        try {
            openBD();

        }catch (SQLException e){

        }
        return usuario;
    }
}
