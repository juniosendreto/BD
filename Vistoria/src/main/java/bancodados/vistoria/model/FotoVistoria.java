package bancodados.vistoria.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by junio on 28/06/16.
 */
@DatabaseTable(tableName = "foto_vistoria")
public class FotoVistoria {

    @DatabaseField(columnName = "id", generatedId = true)
    private Long id;

    @ForeignCollectionField(columnName = "VISTORIA_ID")
    private Collection<Vistoria> vistorias;

    @DatabaseField(columnName = "caminho", canBeNull = false)
    private String caminho;

    @DatabaseField(columnName = "imagem_grande", canBeNull = false)
    private String imagemGrande;

    @DatabaseField(columnName = "imagem_media", canBeNull = false)
    private String imagemMedia;

    @DatabaseField(columnName = "imagem_pequena", canBeNull = false)
    private String imagemPequena;

    public FotoVistoria(){ }


}
