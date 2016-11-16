package bancodados.vistoria.core.service.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.bd.DataBase;
import bancodados.vistoria.model.Localizacao;
import bancodados.vistoria.model.Vistoria;

/**
 * Created by junio on 08/03/16.
 */
public class LocalizacaoDaoImpl  extends AbstractDaoImpl{

    public LocalizacaoDaoImpl(Context context) {
        super(context);
    }

    public Boolean existsLocation(Localizacao localizacao){
        List<Localizacao> localizacaos = (ArrayList) listAll(Localizacao.class);
        for (Localizacao l: localizacaos)
            if(localizacao.getLatitude() == l.getLatitude() && localizacao.getLongitude() == localizacao.getLongitude())
                return true;
        return false;
    }

}
