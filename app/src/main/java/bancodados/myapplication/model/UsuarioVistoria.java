package bancodados.myapplication.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.support.ConnectionSource;
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

    /* USUÁRIO

    @DatabaseField(columnName = "USUARIO_ID", canBeNull = false, uniqueCombo = true)
    private Usuario usuario;
    */

    @ForeignCollectionField
    private ForeignCollection<Usuario> usuarios;

    /* VISTORIA
    @DatabaseField(columnName = "VISTORIA_ID", canBeNull = false, uniqueCombo = true)
    private Vistoria vistoria;
    */

    @ForeignCollectionField
    private ForeignCollection<Vistoria> vistorias;

    @DatabaseField(columnName = "DATA", dataType = DataType.DATE_TIME, canBeNull = false)
    private DateFormat data;

    public UsuarioVistoria(Long id, DateFormat data, ForeignCollection<Vistoria> vistorias,
                           ForeignCollection<Usuario> usuarios) {
        this.id = id;
        this.data = data;
        this.vistorias = vistorias;
        this.usuarios = usuarios;
    }
}
