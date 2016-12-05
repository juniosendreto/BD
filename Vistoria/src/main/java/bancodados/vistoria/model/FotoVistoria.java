package bancodados.vistoria.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by junio on 28/06/16.
 */
@DatabaseTable(tableName = "FOTO_VISTORIA")
public class FotoVistoria implements Parcelable{

    @DatabaseField(columnName = "id", generatedId = true, canBeNull = false)
    private Long id;

    @DatabaseField(foreign = true, columnName = "VISTORIA_ID")
    private Vistoria vistorias;

    @DatabaseField(columnName = "descricao", canBeNull = true)
    private String descricao;

    @DatabaseField(columnName = "imagem_grande", canBeNull = true, dataType = DataType.BYTE_ARRAY)
    private byte[] imagemGrande;

    @DatabaseField(columnName = "imagem_media", canBeNull = true, dataType = DataType.BYTE_ARRAY)
    private byte[] imagemMedia;

    @DatabaseField(columnName = "imagem_pequena", canBeNull = true, dataType = DataType.BYTE_ARRAY)
    private byte[] imagemPequena;

    public FotoVistoria(){ }

    public FotoVistoria(String descricao, byte[] imagemGrande, byte[] imagemMedia, byte[] imagemPequena) {
        this.descricao = descricao;
        this.imagemGrande = imagemGrande;
        this.imagemMedia = imagemMedia;
        this.imagemPequena = imagemPequena;
    }

    public FotoVistoria(Vistoria vistorias, String descricao, byte[] imagemGrande, byte[] imagemMedia,
                        byte[] imagemPequena) {
        this.vistorias = vistorias;
        this.descricao = descricao;
        this.imagemGrande = imagemGrande;
        this.imagemMedia = imagemMedia;
        this.imagemPequena = imagemPequena;
    }

    protected FotoVistoria(Parcel in) {
        descricao = in.readString();
        imagemGrande = in.createByteArray();
        imagemMedia = in.createByteArray();
        imagemPequena = in.createByteArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descricao);
        dest.writeByteArray(imagemGrande);
        dest.writeByteArray(imagemMedia);
        dest.writeByteArray(imagemPequena);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FotoVistoria> CREATOR = new Creator<FotoVistoria>() {
        @Override
        public FotoVistoria createFromParcel(Parcel in) {
            return new FotoVistoria(in);
        }

        @Override
        public FotoVistoria[] newArray(int size) {
            return new FotoVistoria[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vistoria getVistorias() {
        return vistorias;
    }

    public void setVistorias(Vistoria vistorias) {
        this.vistorias = vistorias;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagemGrande() {
        return imagemGrande;
    }

    public void setImagemGrande(byte[] imagemGrande) {
        this.imagemGrande = imagemGrande;
    }

    public byte[] getImagemMedia() {
        return imagemMedia;
    }

    public void setImagemMedia(byte[] imagemMedia) {
        this.imagemMedia = imagemMedia;
    }

    public byte[] getImagemPequena() {
        return imagemPequena;
    }

    public void setImagemPequena(byte[] imagemPequena) {
        this.imagemPequena = imagemPequena;
    }
}
