package bancodados.myapplication.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by junio on 26/02/16.
 */
public class Vistoria {

    public static final String COL_ENCOSTA_NATURAL = "ENCOSTA_NATURAL";

    public static final String COL_TALUDE_CORTE = "TALUDE_CORTE";

    public static final String COL_ATERRO_LANCADO = "ATERRO_LANCADO";

    public static final String COL_PAREDE_ROCHOSA = "PAREDE_ROCHOSA";

    public static final String COL_ALTURA_N = "ALTURA_N";

    public static final String COL_ALTURA_C = "ALTURA_C";

    public static final String COL_ALTURA_L = "ALTURA_L";

    public static final String COL_ALTURA_R = "ALTURA_R";

    public static final String COL_DISTANCIA_MORADA_C = "DISTANCIA_MORADA_C";

    public static final String COL_DISTANCIA_MORADA_L = "DISTANCIA_MORADA_L";

    public static final String COL_TOPO_C = "TOPO_C";

    public static final String COL_TOPO_L = "TOPO_L";

    public static final String COL_BLOCOS_ROCHA_MATACOES = "BLOCOS_ROCHA_MATACOES";

    public static final String COL_LIXO_ENTULHO = "LIXO_ENTULHO";

    public static final String COL_CONCENTRA_AGUA_SERVIDA = "CONCENTRA_AGUA_SERVIDA";

    public static final String COL_DRENAGE_SUPERFICIAL = "DRENAGE_SUPERFICIAL";

    public static final String COL_ESGOTO = "ESGOTO";

    public static final String COL_AGUA_MORADIA_VAZAMENTO = "AGUA_MORADIA_VAZAMENTO";

    public static final String COL_MINAS_DAGUA = "MINAS_DAGUA";

    public static final String COL_ARVORES = "ARVORES";

    public static final String COL_VEGETACAO_RASTEIRA = "VEGETACAO_RASTEIRA";

    public static final String COL_AREA_DESMATADA = "AREA_DESMATADA";

    public static final String COL_AREA_CULTIVO = "AREA_CULTIVO";

    public static final String COL_TRINCA = "TRINCA";

    public static final String COL_DEGRAUS_ABATIMENTO = "DEGRAUS_ABATIMENTO";

    public static final String COL_INCLINACAO = "INCLINACAO";

    public static final String COL_MURO_PAREDE_EMBARRIGADO = "MURO_PAREDE_EMBARRIGADO";

    public static final String COL_CICATRIZ_ESCORREGAMENTO = "CICATRIZ_ESCORREGAMENTO";

    public static final String COL_ESCORREGAMENTO = "ESCORREGAMENTO";

    public static final String COL_QUEDA_BLOCOS = "QUEDA_BLOCOS";


    public static final String TABLE_NAME = "VISTORIA";


    public static final String DATABASE_NAME = "project_db";


    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "ENCOSTA_NATURAL", dataType = DataType.STRING, canBeNull = false)
    private String encostaNatural;

    @DatabaseField(columnName = "TALUDE_CORTE", dataType = DataType.STRING, canBeNull = false)
    private String taludeCorte;

    @DatabaseField(columnName = "ATERRO_LANCADO", dataType = DataType.STRING, canBeNull = false)
    private String aterroLancado;

    @DatabaseField(columnName = "PAREDE_ROCHOSA", dataType = DataType.STRING, canBeNull = false)
    private String paredeRochosa;

    @DatabaseField(columnName = "ALTURA_N", dataType = DataType.STRING, canBeNull = false)
    private String alturaN;

    @DatabaseField(columnName = "ALTURA_C", dataType = DataType.STRING, canBeNull = true)
    private String alturaC;

    @DatabaseField(columnName = "ALTURA_L", dataType = DataType.STRING, canBeNull = true)
    private String alturaL;

    @DatabaseField(columnName = "ALTURA_R", dataType = DataType.STRING, canBeNull = true)
    private String alturaR;

    @DatabaseField(columnName = "DISTANCIA_MORADA_C", dataType = DataType.STRING, canBeNull = true)
    private String distanciaMoradaC;

    @DatabaseField(columnName = "DISTANCIA_MORADA_L", dataType = DataType.STRING, canBeNull = false)
    private Integer DistanciaMoradaL;

    @DatabaseField(columnName = "TOPO_C", dataType = DataType.STRING, canBeNull = false)
    private String topoC;

    @DatabaseField(columnName = "TOPO_L", dataType = DataType.STRING, canBeNull = false)
    private String topoL;

    @DatabaseField(columnName = "LIXO_ENTULHO", dataType = DataType.STRING, canBeNull = false)
    private String lixoEntulho;

    @DatabaseField(columnName = "CONCENTRA_AGUA_SERVIDA", dataType = DataType.STRING, canBeNull = false)
    private String concentraAguaServida;

    @DatabaseField(columnName = "DRENAGE_SUPERFICIAL", dataType = DataType.STRING, canBeNull = false)
    private String drenageSuperficial;

    @DatabaseField(columnName = "ESGOTO", dataType = DataType.STRING, canBeNull = true)
    private String esgoto;

    @DatabaseField(columnName = "AGUA_MORADIA_VAZAMENTO", dataType = DataType.STRING, canBeNull = true)
    private String aguaMoradiaVazamento;

    @DatabaseField(columnName = "MINAS_DAGUA", dataType = DataType.STRING, canBeNull = true)
    private String minasDagua;

    @DatabaseField(columnName = "ARVORES", dataType = DataType.STRING, canBeNull = true)
    private String arvores;

    @DatabaseField(columnName = "VEGETACAO_RASTEIRA", dataType = DataType.STRING, canBeNull = false)
    private Integer vegetacaoRasteira;

    @DatabaseField(columnName = "AREA_DESMATADA", dataType = DataType.STRING, canBeNull = false)
    private String areaDesmatada;

    @DatabaseField(columnName = "AREA_CULTIVO", dataType = DataType.STRING, canBeNull = false)
    private String areaCultivo;

    @DatabaseField(columnName = "TRINCA", dataType = DataType.STRING, canBeNull = false)
    private String trinca;

    @DatabaseField(columnName = "DEGRAUS_ABATIMENTO", dataType = DataType.STRING, canBeNull = false)
    private String degrausAbatimento;

    @DatabaseField(columnName = "INCLINACAO", dataType = DataType.STRING, canBeNull = true)
    private String inclinacao;

    @DatabaseField(columnName = "MURO_PAREDE_EMBARRIGADO", dataType = DataType.STRING, canBeNull = true)
    private String muroParedeEmbarrigado;

    @DatabaseField(columnName = "CICATRIZ_ESCORREGAMENTO", dataType = DataType.STRING, canBeNull = true)
    private String cicatrizEscorregamento;

    @DatabaseField(columnName = "ESCORREGAMENTO", dataType = DataType.STRING, canBeNull = true)
    private String Escorregamento;

    @DatabaseField(columnName = "QUEDA_BLOCOS", dataType = DataType.STRING, canBeNull = false)
    private Integer quedaBlocos;



}
