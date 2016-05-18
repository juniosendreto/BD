package bancodados.test.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import bancodados.test.model.Localizacao;
import bancodados.test.model.Usuario;
import bancodados.test.model.UsuarioVistoria;
import bancodados.test.model.Vistoria;

/**
 * Created by junio on 01/03/16.
 */
public class DataBase extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sys_vistoria.db";

//    private Dao<Usuario, Integer> usuarioDao = null;
//    private Dao<UsuarioVistoria, Integer> usuarioVistoriaDao = null;
//    private Dao<Vistoria, Integer> vistoriaDao = null;
//    private Dao<Localizacao, Integer> localizacaoDao = null;



    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Vistoria.class);
            TableUtils.createTable(connectionSource, UsuarioVistoria.class);
            TableUtils.createTable(connectionSource, Localizacao.class);

        } catch(Exception e) {
            e.printStackTrace();
            Log.e("-------", "Não foi possível criar a base de dados " + e.getMessage());
            throw new RuntimeException(e);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        try {
            TableUtils.dropTable(connectionSource, UsuarioVistoria.class, true);
            TableUtils.dropTable(connectionSource, Usuario.class, true);
            TableUtils.dropTable(connectionSource, Vistoria.class, true);
            TableUtils.dropTable(connectionSource, Localizacao.class, true);

            onCreate(sqLiteDatabase, connectionSource);

        } catch(SQLException e) {
            e.printStackTrace();
            Log.e("----------", "Não foi possível dropar o banco" + e.getMessage());

            throw new RuntimeException(e);

        }

    }

//    public Dao<Usuario, Integer> getUsuarioDao(){
//        if(null == usuarioDao){
//            try {
//                usuarioDao = getDao(Usuario.class);
//            }catch (java.sql.SQLException e){
//                Log.d("------------", "DAO Usuario " + e.getMessage());
//            }
//        }
//        return usuarioDao;
//    }
//
//    public Dao<UsuarioVistoria, Integer> getUsuarioVistoriaDao(){
//        if(null == usuarioVistoriaDao){
//            try {
//                usuarioVistoriaDao = getDao(UsuarioVistoria.class);
//            }catch (java.sql.SQLException e){
//                Log.d("-------", "DAO UsuarioVistoria " + e.getMessage());
//
//            }
//        }
//        return usuarioVistoriaDao;
//    }
//
//    public Dao<Vistoria, Integer> getVistoriaDao(){
//        if(null == vistoriaDao){
//            try {
//                vistoriaDao = getDao(Vistoria.class);
//            }catch (java.sql.SQLException e){
//                Log.d("-----", "DAO Vistoria " + e.getMessage());
//            }
//        }
//        return vistoriaDao;
//    }
//
//    public Dao<Localizacao, Integer> getLocalizacaoDao(){
//        if(null == localizacaoDao){
//            try {
//                localizacaoDao = getDao(Localizacao.class);
//            }catch (java.sql.SQLException e){
//                Log.d("------", "DAO Localizacao " + e.getMessage());
//            }
//        }
//        return localizacaoDao;
//    }

   // @Override
   // public void close(){
    //    super.close();
    //}

}
