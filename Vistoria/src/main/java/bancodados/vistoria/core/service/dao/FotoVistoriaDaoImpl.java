package bancodados.vistoria.core.service.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.bd.DataBase;
import bancodados.vistoria.model.FotoVistoria;
import bancodados.vistoria.model.Vistoria;

/**
 * Created by junio on 16/08/16.
 */
public class FotoVistoriaDaoImpl extends AbstractDaoImpl{

    public FotoVistoriaDaoImpl(Context context) {
        super(context);
    }

    public List<FotoVistoria> findByIdVistoria(Vistoria vistoria) {
        List<FotoVistoria> fotoVistoriaList = new ArrayList<>();
        try {
            connectingBD();
            QueryBuilder<FotoVistoria, ?> queryBuilder = dataBase.getDao(FotoVistoria.class).queryBuilder();
            Where<FotoVistoria, ?> where = queryBuilder.where();
            where.eq("VISTORIA_ID", vistoria.getId());
            PreparedQuery<FotoVistoria> preparedQuery = queryBuilder.prepare();
            fotoVistoriaList = dataBase.getDao(FotoVistoria.class).query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("----------", e.getMessage());
        }finally {
            dataBase.close();
        }
        if (fotoVistoriaList.isEmpty()) {
            return null;
        } else {
            return fotoVistoriaList;
        }
    }

    public List<FotoVistoria> querryImagemPequena(Vistoria vistoria) {
        List<FotoVistoria> fotoVistoriaList = new ArrayList<>();
        try {
            connectingBD();
            QueryBuilder<FotoVistoria, ?> queryBuilder = dataBase.getDao(FotoVistoria.class).queryBuilder();
            queryBuilder.selectColumns("IMAGEM_MEDIA", "DESCRICAO", "VISTORIA_ID");
            Where<FotoVistoria, ?> where = queryBuilder.where();
            where.eq("VISTORIA_ID", vistoria.getId());
            PreparedQuery<FotoVistoria> preparedQuery = queryBuilder.prepare();
            fotoVistoriaList = dataBase.getDao(FotoVistoria.class).query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("----------", e.getMessage());
        }finally {
            dataBase.close();
        }
        if (fotoVistoriaList.isEmpty()) {
            return null;
        } else {
            return fotoVistoriaList;
        }
    }

    public void deleteFromVistoria(Context context, Vistoria vistoria){
        try {
            DataBase helper = OpenHelperManager.getHelper(context, DataBase.class);
            Dao dao = helper.getDao(FotoVistoria.class);
            DeleteBuilder<FotoVistoria, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq("VISTORIA_ID", vistoria.getId());
            deleteBuilder.delete();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
