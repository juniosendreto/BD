package bancodados.myapplication.core.service.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import bancodados.myapplication.core.service.DataBase;

/**
 * Created by junio on 11/03/16.
 */

public abstract class AbstractDaoImpl {

    protected DataBase dataBase;
    private Context context;

    public AbstractDaoImpl(Context context){
       this.context = context;
    }

    public void conexaoBD(){
        if(dataBase == null || !(dataBase.isOpen())) {
            dataBase = new DataBase(context);

            Log.d("---------", dataBase.getDatabaseName());
            Log.d("---------", dataBase.isOpen() + "");

        }

    }

    public Object save(Class classe, Object object){
        try{
            conexaoBD();
            dataBase.getDao(classe).create(object);
            return object;
        }catch (Exception e){
            Log.d("--------", "ERRO SAVE(ABSTRACTDAO) " + e.getMessage());
        }finally {
            dataBase.close();

        }
        return null;
    }

    public Object update(Class classe, Object object){
        try{
            conexaoBD();
            dataBase.getDao(classe).update(object);
            return object;
        }catch (SQLException e){
            Log.d("------", "ERRO UPDATE(ABSDAO)");
        }finally {
            dataBase.close();
        }
        return null;
    }

    public Boolean delete(Class classe, Object object){
        try{
            conexaoBD();
            dataBase.getDao(classe).delete(object);
            return true;
        }catch (SQLException e){
            Log.d("------", "ERRO DELETE(ABSDAO)");
        }finally {
            dataBase.close();
        }
        return null;
    }

    public List<Class> listAll(Class classe){
        List<Class> lista = null;
        try{
            conexaoBD();
            lista = dataBase.getDao(classe).queryForAll();
            return lista;
        }catch (SQLException e){
            Log.d("------", "ERRO listAll(ABSDAO)");
        }finally {
            dataBase.close();
        }
        return null;
    }

    public Object findById(Class classe, Long id){
       Object classeId = null;
        try{
            conexaoBD();
            Dao<Class, Integer> dao = dataBase.getDao(classe);
            classeId = dao.queryForId(Integer.valueOf(id.intValue()));
            return classeId;
        }catch (SQLException e){
            Log.d("------", "ERRO findById(ABSDAO)");
        }finally {
            dataBase.close();
        }
        return null;
    }

    public Long countAllRows(Class classe){
        try {
            return dataBase.getDao(classe).countOf();
        }catch (SQLException e){
            Log.d("------", "ERRO countAllRows(ABSDAO)");

        }finally {
            dataBase.close();
        }
        return Long.parseLong(null);
    }

    public Long deleteAllrows(Class classe){
        try {
            dataBase.getDao(classe).deleteIds(listAll(classe));
            return countAllRows(classe);
        }catch (SQLException e){
            Log.d("------", "ERRO deleteAllRowsAllRows(ABSDAO)");

        }finally {
            dataBase.close();
        }
        return Long.parseLong(null);
    }

}
