package bancodados.vistoria.core.service.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.util.List;

import bancodados.vistoria.model.Localizacao;

/**
 * Created by junio on 08/03/16.
 */
public class LocalizacaoDaoImpl  extends AbstractDaoImpl{

    public LocalizacaoDaoImpl(Context context) {
        super(context);
    }

    public Localizacao findByLatAndLong(Double latitude, Double longitude){
        List<Localizacao> localizacaoList = null;
        try {
            connectingBD();
            QueryBuilder<Localizacao, Object> queryBuilder = (QueryBuilder<Localizacao, Object>)
                    dataBase.getDao(Localizacao.class).queryBuilder();
            Where<Localizacao, Object> where = queryBuilder.where();
            where.eq(Localizacao.COL_LATITUDE, latitude).and().eq(Localizacao.COL_LONGITUDE, longitude);
            PreparedQuery<Localizacao> preparedQuery = queryBuilder.prepare();
            localizacaoList = dataBase.getDao(Localizacao.class).query(preparedQuery);

        }catch (java.sql.SQLException e){
            e.printStackTrace();
            Log.d("----------", "Problema co  método findByLatAndLong");
        }
        if(localizacaoList.isEmpty())
            return null;
        else
            return localizacaoList.get(0);

    }


}
