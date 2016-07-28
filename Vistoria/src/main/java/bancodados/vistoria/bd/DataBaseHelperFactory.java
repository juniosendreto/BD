package bancodados.vistoria.bd;

import android.content.Context;

/**
 * Created by junio on 04/04/16.
 */
public class DataBaseHelperFactory {
    private static DataBase dataBase = null;

    public static DataBase getIntanceConnection(Context context) {
        if (dataBase == null){
            dataBase = new DataBase(context);
            return dataBase;
        }
        return dataBase;
    }
}
