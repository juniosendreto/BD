package bancodados.myapplication.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.DateFormat;

/**
 * Created by junio on 01/03/16.
 */

@DatabaseTable(tableName = "USUARIO_VISTORIA")
public class UsuarioVistoria {

    public static final String COL_ID = "ID";


    @DatabaseField(columnName = "id", id = true, generatedId = true)
    private Long id;

    /* USUÁRIO */
    @DatabaseField(columnName = "USUARIO_ID", canBeNull = false, uniqueCombo = true)
    private Usuario usuario;

    /* VISTORIA */
    @DatabaseField(columnName = "VISTORIA_ID", canBeNull = false, uniqueCombo = true)
    private Vistoria vistoria;

    @DatabaseField(columnName = "DATA", dataType = DataType.DATE_TIME, canBeNull = false)
    private DateFormat latitude;


}
