package bancodados.vistoria.model;

import android.content.Context;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by junio on 01/03/16.
 */

@DatabaseTable(tableName = "USUARIO_VISTORIA")
public class UsuarioVistoria {

    public static final String COL_ID = "ID";
    public static final String COL_USUARIO_ID = "USUARIO_ID";
    public static final String COL_VISTORIA_ID = "VISTORIA_ID";

    private Context context;

    public UsuarioVistoria(Context context){
        this.context = context;
    }

    @DatabaseField(columnName = "id", generatedId = true, canBeNull = false)
    private Long id;

   @DatabaseField(foreign = true, columnName = "USUARIO_ID")
    private Usuario usuario;

    @DatabaseField(foreign = true, columnName = "VISTORIA_ID")
    private Vistoria vistoria;


    @DatabaseField(columnName = "data",  canBeNull = false)
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vistoria getVistoria() {
        return vistoria;
    }

    public void setVistoria(Vistoria vistoria) {
        this.vistoria = vistoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public UsuarioVistoria(String data, Vistoria vistoria,
                           Usuario usuario) {
        this.data = data;
        this.vistoria = vistoria;
        this.usuario = usuario;
    }
    public UsuarioVistoria() {}

}
