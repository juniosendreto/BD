package bancodados.myapplication.model;

import com.j256.ormlite.dao.ForeignCollection;
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

    @DatabaseField(columnName = "id", generatedId = true)
    private Long id;

    @ForeignCollectionField(columnName = "VISTORIA_ID")
    private ForeignCollection<Vistoria> vistorias;

    @DatabaseField(columnName = "LATITUDE", canBeNull = false)
    private Double latitude;

    @DatabaseField(columnName = "LONGITUDE", canBeNull = false)
    private Double longitude;

    @DatabaseField(columnName = "MUNICIPIO", canBeNull = false)
    private String municipio;

    @DatabaseField(columnName = "ACESSO_LOCAL", canBeNull = false)
    private String acessoLocal;


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


    public Localizacao(ForeignCollection<Vistoria> vistorias, Double latitude, Double longitude, String municipio,
                       String acessoLocal) {
        this.vistorias = vistorias;
        this.latitude = latitude;
        this.longitude = longitude;
        this.municipio = municipio;
        this.acessoLocal = acessoLocal;
    }

    public Localizacao(Double latitude, Double longitude, String municipio, String acessoLocal) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.municipio = municipio;
        this.acessoLocal = acessoLocal;

    }

    public Localizacao(){}
}
