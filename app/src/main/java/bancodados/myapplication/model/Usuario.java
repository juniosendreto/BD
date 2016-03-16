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

    public static final String COL_ID = "ID";

    public static final String COL_LOGIN = "LOGIN";

    public static final String COL_PASSWORD = "PASSWORD";

    public static final String COL_NOME = "NOME";

    public static final String COL_CPF = "CPF";

    public static final String COL_EMAIL = "EMAIL";

    public static final String COL_MUNICIPIO = "MUNICIPIO";

    public static final String COL_ENDERECO = "ENDERECO";

    public static final String COL_TELEFONE = "TELEFONE";

    public static final String COL_CELULAR = "CELULAR";

    public static final String COL_NIVEL = "NIVEL";

    public static final String TABLE_NAME = "USUARIO";

    public static final String DATABASE_NAME = "sys_vistoria_db";

    @DatabaseField(columnName = "id", generatedId = true)
    private Long id;

    @DatabaseField(foreign = true)
    private UsuarioVistoria usuarioVistoria;

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
}