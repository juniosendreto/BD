package bancodados.myapplication.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by junio on 01/03/16.
 */

@DatabaseTable(tableName = "LOCALIZACAO")
public class Localizacao {

    public static final String COL_ID = "ID";

    public static final String COL_LATITUDE = "LATITUDE";

    public static final String COL_LONGITUDE = "LONGITUDE";

    public static final String COL_MUNICIPIO = "MUNICIPIO";

    public static final String COL_ACESSO_LOCAL = "ACESSO_LOCAL";

    public static final String COL_NOME_MORADOR = "NOME_MORADOR";

    public static final String COL_TIPO_MORADIA = "TIPO_MORADIA";

    public static final String TABLE_NAME = "LOCALIZACAO";

    @DatabaseField(columnName = "id", id = true, generatedId = true)
    private Long id;

    @DatabaseField(columnName = "LATITUDE", dataType = DataType.DOUBLE, canBeNull = false)
    private Double latitude;

    @DatabaseField(columnName = "LONGITUDE", dataType = DataType.DOUBLE, canBeNull = false)
    private Double longitude;

    @DatabaseField(columnName = "MUNICIPIO", dataType = DataType.STRING, canBeNull = false)
    private String municipio;

    @DatabaseField(columnName = "ACESSO_LOCAL", dataType = DataType.STRING, canBeNull = false)
    private String acessoLocal;

    @DatabaseField(columnName = "NOME_MORADOR", dataType = DataType.STRING, canBeNull = false)
    private String nomeMorador;

    @DatabaseField(columnName = "TIPO_MORADIA", dataType = DataType.STRING, canBeNull = true)
    private String tipoMoradia;

    @ForeignCollectionField(eager = true)
    private Collection vistorias;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getAcessoLocal() {
        return acessoLocal;
    }

    public void setAcessoLocal(String acessoLocal) {
        this.acessoLocal = acessoLocal;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public String getTipoMoradia() {
        return tipoMoradia;
    }

    public void setTipoMoradia(String tipoMoradia) {
        this.tipoMoradia = tipoMoradia;
    }

    public Localizacao(Collection vistorias, String nomeMorador, String tipoMoradia, String acessoLocal,
                       String municipio, Double longitude, Double latitude) {

        this.vistorias = vistorias;
        this.nomeMorador = nomeMorador;
        this.tipoMoradia = tipoMoradia;
        this.acessoLocal = acessoLocal;
        this.municipio = municipio;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
