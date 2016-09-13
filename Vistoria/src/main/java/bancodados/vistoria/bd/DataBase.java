package bancodados.vistoria.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;

import bancodados.vistoria.model.FotoVistoria;
import bancodados.vistoria.model.Localizacao;
import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.model.UsuarioVistoria;
import bancodados.vistoria.model.Vistoria;

/**
 * Created by junio on 01/03/16.
 */
public class DataBase extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String PATH = "/mnt/sdcard/vistoria/bd/";
    private static final String DATABASE_NAME = "sys_vistoria.db";

    public DataBase(Context context) {
        super(context, PATH + DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Vistoria.class);
            TableUtils.createTable(connectionSource, UsuarioVistoria.class);
            TableUtils.createTable(connectionSource, Localizacao.class);
            TableUtils.createTable(connectionSource, FotoVistoria.class);

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
            TableUtils.dropTable(connectionSource, FotoVistoria.class, true);

            onCreate(sqLiteDatabase, connectionSource);

        } catch(SQLException e) {
            e.printStackTrace();
            Log.e("----------", "Não foi possível dropar o banco" + e.getMessage());

            throw new RuntimeException(e);

        }

    }
}
