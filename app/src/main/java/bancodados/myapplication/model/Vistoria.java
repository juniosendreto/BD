package bancodados.myapplication.model;

import android.support.annotation.ColorRes;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by junio on 26/02/16.
 */

@DatabaseTable(tableName = "VISTORIA")
public class Vistoria {

    @DatabaseField(columnName = "id", generatedId = true)
    private Long id;

    @DatabaseField(foreign = true, columnName = "LOCALIZACAO_ID")
    private Localizacao localizacao;


    /*
        2 PASSO - CARACTERIZAÇÃO DO LOCAL (14)
     */

    @DatabaseField(columnName = "ENCOSTA_NATURAL", canBeNull = false)
    private Boolean encostaNatural;

    @DatabaseField(columnName = "TALUDE_CORTE", canBeNull = false)
    private Boolean taludeCorte;

    @DatabaseField(columnName = "ATERRO_LANCADO", canBeNull = false)
    private Boolean aterroLancado;

    @DatabaseField(columnName = "PAREDE_ROCHOSA", canBeNull = false)
    private Boolean paredeRochosa;

    @DatabaseField(columnName = "ALTURA_N", canBeNull = false)
    private Double alturaN;

    @DatabaseField(columnName = "ALTURA_C", canBeNull = true)
    private Double alturaC;

    @DatabaseField(columnName = "ALTURA_L", canBeNull = true)
    private Double alturaL;

    @DatabaseField(columnName = "ALTURA_R", canBeNull = true)
    private Double alturaR;

    @DatabaseField(columnName = "DISTANCIA_MORADA_C", canBeNull = true)
    private Double distanciaMoradaC;

    @DatabaseField(columnName = "DISTANCIA_MORADA_L", canBeNull = false)
    private Double DistanciaMoradaL;

    @DatabaseField(columnName = "TOPO_C", canBeNull = false)
    private Double topoC;

    @DatabaseField(columnName = "TOPO_L", canBeNull = false)
    private Double topoL;

    @DatabaseField(columnName = "BLOCOS_ROCHA_MATACOES", canBeNull = false)
    private Boolean blocosRochasMatacoes;

    @DatabaseField(columnName = "LIXO_ENTULHO", canBeNull = false)
    private Boolean lixoEntulho;

     /*
        3 PASSO - ÁGUA (6)
     */

    @DatabaseField(columnName = "CONCENTRA_AGUA_CHUVA", canBeNull = false)
    private Boolean concentraAguaChuva;

    @DatabaseField(columnName = "CONCENTRA_AGUA_SERVIDA",  canBeNull = false)
    private Boolean concentraAguaServida;

    @DatabaseField(columnName = "DRENAGE_SUPERFICIAL", canBeNull = false)
    private String drenageSuperficial;

    @DatabaseField(columnName = "ESGOTO", canBeNull = true)
    private String esgoto;

    @DatabaseField(columnName = "AGUA_MORADIA_VAZAMENTO", canBeNull = true)
    private String aguaMoradiaVazamento;

    @DatabaseField(columnName = "MINAS_DAGUA", canBeNull = true)
    private String minasDagua;

     /*
        4 PASSO - VEGETÇÃO NO TALUDE OU PROXIMIDADES (4)
     */

    @DatabaseField(columnName = "ARVORES", canBeNull = true)
    private Boolean arvores;

    @DatabaseField(columnName = "VEGETACAO_RASTEIRA", canBeNull = false)
    private Boolean vegetacaoRasteira;

    @DatabaseField(columnName = "AREA_DESMATADA", canBeNull = false)
    private Boolean areaDesmatada;

    @DatabaseField(columnName = "AREA_CULTIVO", canBeNull = false)
    private String areaCultivo;

     /*
        5 PASSO - SINAIS DE MOVIMENTAÇÕES (5)
     */

    @DatabaseField(columnName = "TRINCA", canBeNull = false)
    private String trinca;

    @DatabaseField(columnName = "DEGRAUS_ABATIMENTO", canBeNull = false)
    private Boolean degrausAbatimento;

    @DatabaseField(columnName = "INCLINACAO", canBeNull = true)
    private String inclinacao;

    @DatabaseField(columnName = "MURO_PAREDE_EMBARRIGADO", canBeNull = true)
    private Boolean muroParedeEmbarrigado;

    @DatabaseField(columnName = "CICATRIZ_ESCORREGAMENTO", canBeNull = true)
    private Boolean cicatrizEscorregamento;

     /*
        6 PASSOO - TIPO DE PROCESSOS DE INSTABILIZAÇÃO ESPERADOS OU JÁ OCORRIDOS (3)
     */

    @DatabaseField(columnName = "ESCORREGAMENTO", canBeNull = true)
    private String Escorregamento;

    @DatabaseField(columnName = "QUEDA_BLOCOS", canBeNull = false)
    private Boolean quedaBlocos;

    @DatabaseField(columnName = "ROLAMENTO_BLOCOS", canBeNull = false)
    private Boolean rolamentoBlocos;

     /*
        7 PASSO - DETERMINAÇÃO DO GRAU DE RISCO
     */

    @DatabaseField(columnName = "RISCO", canBeNull = false)
    private String risco;

    /*
        8 PASSO - NECESSIDADE DE REMOÇÃO (2)
     */

    @DatabaseField(columnName = "QUANTIDADE_MORADIAS", canBeNull = false)
    private Integer quantidadeMoradias;

    @DatabaseField(columnName = "QUANTIDADE_PESSOAS", canBeNull = false)
    private Integer quantidadePessoas;

     /*
        8 PASSO - OUTRAS INFORMAÇÕES
     */

    @DatabaseField(columnName = "INFORMAÇÕES", canBeNull = false)
    private String informacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public UsuarioVistoria getUsuarioVistoria() {
        return usuarioVistoria;
    }

    public void setUsuarioVistoria(UsuarioVistoria usuarioVistoria) {
        this.usuarioVistoria = usuarioVistoria;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }*/

    public Boolean getEncostaNatural() {
        return encostaNatural;
    }

    public void setEncostaNatural(Boolean encostaNatural) {
        this.encostaNatural = encostaNatural;
    }

    public Boolean getTaludeCorte() {
        return taludeCorte;
    }

    public void setTaludeCorte(Boolean taludeCorte) {
        this.taludeCorte = taludeCorte;
    }

    public Boolean getAterroLancado() {
        return aterroLancado;
    }

    public void setAterroLancado(Boolean aterroLancado) {
        this.aterroLancado = aterroLancado;
    }

    public Boolean getParedeRochosa() {
        return paredeRochosa;
    }

    public void setParedeRochosa(Boolean paredeRochosa) {
        this.paredeRochosa = paredeRochosa;
    }

    public Double getAlturaN() {
        return alturaN;
    }

    public void setAlturaN(Double alturaN) {
        this.alturaN = alturaN;
    }

    public Double getAlturaC() {
        return alturaC;
    }

    public void setAlturaC(Double alturaC) {
        this.alturaC = alturaC;
    }

    public Double getAlturaL() {
        return alturaL;
    }

    public void setAlturaL(Double alturaL) {
        this.alturaL = alturaL;
    }

    public Double getAlturaR() {
        return alturaR;
    }

    public void setAlturaR(Double alturaR) {
        this.alturaR = alturaR;
    }

    public Double getDistanciaMoradaC() {
        return distanciaMoradaC;
    }

    public void setDistanciaMoradaC(Double distanciaMoradaC) {
        this.distanciaMoradaC = distanciaMoradaC;
    }

    public Double getDistanciaMoradaL() {
        return DistanciaMoradaL;
    }

    public void setDistanciaMoradaL(Double distanciaMoradaL) {
        DistanciaMoradaL = distanciaMoradaL;
    }

    public Double getTopoC() {
        return topoC;
    }

    public void setTopoC(Double topoC) {
        this.topoC = topoC;
    }

    public Double getTopoL() {
        return topoL;
    }

    public void setTopoL(Double topoL) {
        this.topoL = topoL;
    }

    public Boolean getBlocosRochasMatacoes() {
        return blocosRochasMatacoes;
    }

    public void setBlocosRochasMatacoes(Boolean blocosRochasMatacoes) {
        this.blocosRochasMatacoes = blocosRochasMatacoes;
    }

    public Boolean getLixoEntulho() {
        return lixoEntulho;
    }

    public void setLixoEntulho(Boolean lixoEntulho) {
        this.lixoEntulho = lixoEntulho;
    }

    public Boolean getConcentraAguaChuva() {
        return concentraAguaChuva;
    }

    public void setConcentraAguaChuva(Boolean concentraAguaChuva) {
        this.concentraAguaChuva = concentraAguaChuva;
    }

    public Boolean getConcentraAguaServida() {
        return concentraAguaServida;
    }

    public void setConcentraAguaServida(Boolean concentraAguaServida) {
        this.concentraAguaServida = concentraAguaServida;
    }

    public String getDrenageSuperficial() {
        return drenageSuperficial;
    }

    public void setDrenageSuperficial(String drenageSuperficial) {
        this.drenageSuperficial = drenageSuperficial;
    }

    public String getEsgoto() {
        return esgoto;
    }

    public void setEsgoto(String esgoto) {
        this.esgoto = esgoto;
    }

    public String getAguaMoradiaVazamento() {
        return aguaMoradiaVazamento;
    }

    public void setAguaMoradiaVazamento(String aguaMoradiaVazamento) {
        this.aguaMoradiaVazamento = aguaMoradiaVazamento;
    }

    public String getMinasDagua() {
        return minasDagua;
    }

    public void setMinasDagua(String minasDagua) {
        this.minasDagua = minasDagua;
    }

    public Boolean getArvores() {
        return arvores;
    }

    public void setArvores(Boolean arvores) {
        this.arvores = arvores;
    }

    public Boolean getVegetacaoRasteira() {
        return vegetacaoRasteira;
    }

    public void setVegetacaoRasteira(Boolean vegetacaoRasteira) {
        this.vegetacaoRasteira = vegetacaoRasteira;
    }

    public Boolean getAreaDesmatada() {
        return areaDesmatada;
    }

    public void setAreaDesmatada(Boolean areaDesmatada) {
        this.areaDesmatada = areaDesmatada;
    }

    public String getAreaCultivo() {
        return areaCultivo;
    }

    public void setAreaCultivo(String areaCultivo) {
        this.areaCultivo = areaCultivo;
    }

    public String getTrinca() {
        return trinca;
    }

    public void setTrinca(String trinca) {
        this.trinca = trinca;
    }

    public Boolean getDegrausAbatimento() {
        return degrausAbatimento;
    }

    public void setDegrausAbatimento(Boolean degrausAbatimento) {
        this.degrausAbatimento = degrausAbatimento;
    }

    public String getInclinacao() {
        return inclinacao;
    }

    public void setInclinacao(String inclinacao) {
        this.inclinacao = inclinacao;
    }

    public Boolean getMuroParedeEmbarrigado() {
        return muroParedeEmbarrigado;
    }

    public void setMuroParedeEmbarrigado(Boolean muroParedeEmbarrigado) {
        this.muroParedeEmbarrigado = muroParedeEmbarrigado;
    }

    public Boolean getCicatrizEscorregamento() {
        return cicatrizEscorregamento;
    }

    public void setCicatrizEscorregamento(Boolean cicatrizEscorregamento) {
        this.cicatrizEscorregamento = cicatrizEscorregamento;
    }

    public String getEscorregamento() {
        return Escorregamento;
    }

    public void setEscorregamento(String escorregamento) {
        Escorregamento = escorregamento;
    }

    public Boolean getQuedaBlocos() {
        return quedaBlocos;
    }

    public void setQuedaBlocos(Boolean quedaBlocos) {
        this.quedaBlocos = quedaBlocos;
    }

    public Boolean getRolamentoBlocos() {
        return rolamentoBlocos;
    }

    public void setRolamentoBlocos(Boolean rolamentoBlocos) {
        this.rolamentoBlocos = rolamentoBlocos;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    public Integer getQuantidadeMoradias() {
        return quantidadeMoradias;
    }

    public void setQuantidadeMoradias(Integer quantidadeMoradias) {
        this.quantidadeMoradias = quantidadeMoradias;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }


    public Vistoria(){    }

    public Vistoria(Localizacao localizacao, Boolean encostaNatural, Boolean taludeCorte,
                    Boolean aterroLancado, Boolean paredeRochosa, Double alturaN, Double alturaC,
                    Double alturaL, Double alturaR, Double distanciaMoradaC, Double distanciaMoradaL,
                    Double topoC, Double topoL, Boolean blocosRochasMatacoes, Boolean lixoEntulho,
                    Boolean concentraAguaChuva, Boolean concentraAguaServida, String drenageSuperficial,
                    String esgoto, String aguaMoradiaVazamento, String minasDagua, Boolean arvores,
                    Boolean vegetacaoRasteira, Boolean areaDesmatada, String areaCultivo, String trinca,
                    Boolean degrausAbatimento, String inclinacao, Boolean muroParedeEmbarrigado,
                    Boolean cicatrizEscorregamento, String escorregamento, Boolean quedaBlocos,
                    Boolean rolamentoBlocos, String risco, Integer quantidadeMoradias,
                    Integer quantidadePessoas, String informacoes) {

        this.localizacao = localizacao;
        this.encostaNatural = encostaNatural;
        this.taludeCorte = taludeCorte;
        this.aterroLancado = aterroLancado;
        this.paredeRochosa = paredeRochosa;
        this.alturaN = alturaN;
        this.alturaC = alturaC;
        this.alturaL = alturaL;
        this.alturaR = alturaR;
        this.distanciaMoradaC = distanciaMoradaC;
        this.DistanciaMoradaL = distanciaMoradaL;
        this.topoC = topoC;
        this.topoL = topoL;
        this.blocosRochasMatacoes = blocosRochasMatacoes;
        this.lixoEntulho = lixoEntulho;
        this.concentraAguaChuva = concentraAguaChuva;
        this.concentraAguaServida = concentraAguaServida;
        this.drenageSuperficial = drenageSuperficial;
        this.esgoto = esgoto;
        this.aguaMoradiaVazamento = aguaMoradiaVazamento;
        this.minasDagua = minasDagua;
        this.arvores = arvores;
        this.vegetacaoRasteira = vegetacaoRasteira;
        this.areaDesmatada = areaDesmatada;
        this.areaCultivo = areaCultivo;
        this.trinca = trinca;
        this.degrausAbatimento = degrausAbatimento;
        this.inclinacao = inclinacao;
        this.muroParedeEmbarrigado = muroParedeEmbarrigado;
        this.cicatrizEscorregamento = cicatrizEscorregamento;
        this.Escorregamento = escorregamento;
        this.quedaBlocos = quedaBlocos;
        this.rolamentoBlocos = rolamentoBlocos;
        this.risco = risco;
        this.quantidadeMoradias = quantidadeMoradias;
        this.quantidadePessoas = quantidadePessoas;
        this.informacoes = informacoes;
    }

    public Vistoria(Boolean encostaNatural, Boolean taludeCorte,
                    Boolean aterroLancado, Boolean paredeRochosa, Double alturaN, Double alturaC,
                    Double alturaL, Double alturaR, Double distanciaMoradaC, Double distanciaMoradaL,
                    Double topoC, Double topoL, Boolean blocosRochasMatacoes, Boolean lixoEntulho,
                    Boolean concentraAguaChuva, Boolean concentraAguaServida, String drenageSuperficial,
                    String esgoto, String aguaMoradiaVazamento, String minasDagua, Boolean arvores,
                    Boolean vegetacaoRasteira, Boolean areaDesmatada, String areaCultivo, String trinca,
                    Boolean degrausAbatimento, String inclinacao, Boolean muroParedeEmbarrigado,
                    Boolean cicatrizEscorregamento, String escorregamento, Boolean quedaBlocos,
                    Boolean rolamentoBlocos, String risco, Integer quantidadeMoradias,
                    Integer quantidadePessoas, String informacoes) {

        this.encostaNatural = encostaNatural;
        this.taludeCorte = taludeCorte;
        this.aterroLancado = aterroLancado;
        this.paredeRochosa = paredeRochosa;
        this.alturaN = alturaN;
        this.alturaC = alturaC;
        this.alturaL = alturaL;
        this.alturaR = alturaR;
        this.distanciaMoradaC = distanciaMoradaC;
        this.DistanciaMoradaL = distanciaMoradaL;
        this.topoC = topoC;
        this.topoL = topoL;
        this.blocosRochasMatacoes = blocosRochasMatacoes;
        this.lixoEntulho = lixoEntulho;
        this.concentraAguaChuva = concentraAguaChuva;
        this.concentraAguaServida = concentraAguaServida;
        this.drenageSuperficial = drenageSuperficial;
        this.esgoto = esgoto;
        this.aguaMoradiaVazamento = aguaMoradiaVazamento;
        this.minasDagua = minasDagua;
        this.arvores = arvores;
        this.vegetacaoRasteira = vegetacaoRasteira;
        this.areaDesmatada = areaDesmatada;
        this.areaCultivo = areaCultivo;
        this.trinca = trinca;
        this.degrausAbatimento = degrausAbatimento;
        this.inclinacao = inclinacao;
        this.muroParedeEmbarrigado = muroParedeEmbarrigado;
        this.cicatrizEscorregamento = cicatrizEscorregamento;
        this.Escorregamento = escorregamento;
        this.quedaBlocos = quedaBlocos;
        this.rolamentoBlocos = rolamentoBlocos;
        this.risco = risco;
        this.quantidadeMoradias = quantidadeMoradias;
        this.quantidadePessoas = quantidadePessoas;
        this.informacoes = informacoes;
    }
}
