package bancodados.vistoria.model;

/**
 * Created by junio on 19/10/16.
 */

public class ViewHolderVistoria {

    private Long idUsuario;
    private Long idUsuarioVistoria;
    private Long idVistoria;
    private Long idLocalizacao;
    private String autor;
    private String data;
    private String municipio;
    private String bairro;

    public ViewHolderVistoria(){}

    public ViewHolderVistoria(Long idUsuario, Long idUsuarioVistoria, Long idVistoria, Long idLocalizacao, String autor, String data, String municipio, String bairro) {
        this.idUsuario = idUsuario;
        this.idUsuarioVistoria = idUsuarioVistoria;
        this.idVistoria = idVistoria;
        this.idLocalizacao = idLocalizacao;
        this.autor = autor;
        this.data = data;
        this.municipio = municipio;
        this.bairro = bairro;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuarioVistoria() {
        return idUsuarioVistoria;
    }

    public void setIdUsuarioVistoria(Long idUsuarioVistoria) {
        this.idUsuarioVistoria = idUsuarioVistoria;
    }

    public Long getIdVistoria() {
        return idVistoria;
    }

    public void setIdVistoria(Long idVistoria) {
        this.idVistoria = idVistoria;
    }

    public Long getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(Long idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
