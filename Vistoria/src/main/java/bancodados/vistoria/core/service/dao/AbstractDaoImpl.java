package bancodados.vistoria.core.service.dao;

import android.content.Context;
import android.util.Log;


import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import bancodados.vistoria.Util.FileUtil;
import bancodados.vistoria.bd.DataBase;

/**
 * Created by junio on 11/03/16.
 */

public abstract class AbstractDaoImpl {

    protected DataBase dataBase;
    private Context context;

    public AbstractDaoImpl(Context context) {
        this.context = context;
    }

    public void connectingBD() {
        try {
            if (dataBase == null || !(dataBase.isOpen())) {
                dataBase = new DataBase(context);

                
            }
        }catch (Exception e){
            Log.d("--------", "ERRO OpenBD(ABSTRACTDAO) " + e.getMessage());

        }

    }

    public Object save(Class classe, Object object) {
        try {
            connectingBD();
            dataBase.getDao(classe).create(object);
            return object;
        } catch (Exception e) {
            Log.d("--------", "ERRO SAVE(ABSTRACTDAO) " + e.getMessage());
        } finally {
            dataBase.close();
        }
        return null;
    }

    public Boolean update(Class classe, Object object) {
        try {
            connectingBD();
            dataBase.getDao(classe).update(object);
            return true;
        } catch (SQLException e) {
            Log.d("------", "ERRO UPDATE(ABSDAO)");
        } finally {
            dataBase.close();
        }
        return false;
    }

    public Boolean delete(Class classe, Object object) {
        Integer validarDelete = 0;
        try {
            connectingBD();
            validarDelete = dataBase.getDao(classe).delete(object);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            Log.d("------", "ERRO DELETE(ABSDAO)");
        } finally {
            dataBase.close();
        }
        if(validarDelete == 1){
            return true;
        }else {
            return false;
        }
    }

    public List<Class> listAll(Class classe) {
        List<Class> lista = null;
        try {
            connectingBD();
            lista =  dataBase.getDao(classe).queryForAll();
            return lista;
        } catch (SQLException e) {
            Log.d("------", "ERRO listAll(ABSDAO)");
        } finally {
            dataBase.close();
        }
        return null;
    }

    public Object findById(Class classe, Long id) {
        Object classeId = null;
        try {
            connectingBD();
            Dao<Class, Integer> dao = dataBase.getDao(classe);
            classeId = dao.queryForId(Integer.valueOf(id.toString()));
            return classeId;
        } catch (SQLException e) {
            Log.d("------", "ERRO findById(ABSDAO)");
        }finally {
            dataBase.close();
        }
        return null;
    }


    public Long countAllRows(Class classe) {
        try {
            connectingBD();
            return dataBase.getDao(classe).countOf();
        } catch (SQLException e) {
            Log.d("------", "ERRO countAllRows(ABSDAO) - " + e.getMessage());

        }finally {
            dataBase.close();
        }
        return Long.valueOf(0);
    }

    public Boolean saveAll(Class classe, List<Object> objects){
        try{
            connectingBD();
            for(Object o: objects)
                dataBase.getDao(classe).create(o);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }finally {
            dataBase.close();
        }
    }
}