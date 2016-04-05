package bancodados.myapplication.model;

import android.util.Log;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by junio on 26/02/16.
 */
@DatabaseTable(tableName = "usuario")
public class Usuario {

    public static final String TABLE_NAME = "USUARIO";

    public static final String DATABASE_NAME = "sys_vistoria_db";

    @DatabaseField(columnName = "id", generatedId = true)
    private Long id;

    //@DatabaseField(foreign = true)
    //private UsuarioVistoria usuarioVistoria;

    @DatabaseField(columnName = "nome", canBeNull = false)
    private String nome;

    @DatabaseField(columnName = "login", canBeNull = false)
    private String login;

    @DatabaseField(columnName = "password", canBeNull = false)
    private String password;

    @DatabaseField(columnName = "cpf", canBeNull = false)
    private String cpf;

    @DatabaseField(columnName = "email", canBeNull = false)
    private String email;

    @DatabaseField(columnName = "municipio", canBeNull = true)
    private String municipio;

    @DatabaseField(columnName = "endereco", canBeNull = true)
    private String endereco;

    @DatabaseField(columnName = "telefone", canBeNull = true)
    private String telefone;

    @DatabaseField(columnName = "celular", canBeNull = true)
    private String celular;

    @DatabaseField(columnName = "nivel", canBeNull = false)
    private Integer nivel;


    /*
        - Criar Resttrições CPF, Senha, Email(OK), '''''telefone e celular'''''.
    */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;

    }

    public Usuario(String nome, String cpf, String login, String password, String email, String municipio,
                   String endereco, String telefone, String celular, Integer nivel) {
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
        this.email = email;
        this.municipio = municipio;
        this.endereco = endereco;
        this.telefone = telefone;
        this.celular = celular;
        this.nivel = nivel;
    }

    public Usuario() {
    }

    //Construtor para update de informações no banco

    public Usuario(String nome, String password, String email, String municipio,
                   String endereco, String telefone, String celular) {

        this.nome = nome;
        this.password = password;
        this.email = email;
        this.municipio = municipio;
        this.endereco = endereco;
        this.telefone = telefone;
        this.celular = celular;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
        if (nome != null ? !nome.equals(usuario.nome) : usuario.nome != null) return false;
        if (login != null ? !login.equals(usuario.login) : usuario.login != null) return false;
        if (password != null ? !password.equals(usuario.password) : usuario.password != null)
            return false;
        if (cpf != null ? !cpf.equals(usuario.cpf) : usuario.cpf != null) return false;
        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        if (municipio != null ? !municipio.equals(usuario.municipio) : usuario.municipio != null)
            return false;
        if (endereco != null ? !endereco.equals(usuario.endereco) : usuario.endereco != null)
            return false;
        if (telefone != null ? !telefone.equals(usuario.telefone) : usuario.telefone != null)
            return false;
        if (celular != null ? !celular.equals(usuario.celular) : usuario.celular != null)
            return false;
        return !(nivel != null ? !nivel.equals(usuario.nivel) : usuario.nivel != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (municipio != null ? municipio.hashCode() : 0);
        result = 31 * result + (endereco != null ? endereco.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        result = 31 * result + (celular != null ? celular.hashCode() : 0);
        result = 31 * result + (nivel != null ? nivel.hashCode() : 0);
        return result;
    }
}