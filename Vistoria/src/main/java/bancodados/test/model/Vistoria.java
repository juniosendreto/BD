package bancodados.test.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by junio on 26/02/16.
 */

@DatabaseTable(tableName = "VISTORIA")
public class Vistoria implements Serializable{

    @DatabaseField(columnName = "id", generatedId = true)
    private Long id;

    @DatabaseField(foreign = true, columnName = "LOCALIZACAO_ID")
    private Localizacao localizacao;

    @DatabaseField(foreign = true, columnName = "FOTO_VISTORIA_ID")
    private FotoVistoria fotoVistoria;

    /*
        1 PASSO - DADOS GERAIS SOBRE AS MORADIAS
     */

    @DatabaseField(columnName = "NOME_MORADOR", canBeNull = true)
    private String nomeMorador;

    @DatabaseField(columnName = "TIPO_MORADIA", canBeNull = true)
    private Integer tipoMoradia;
    //

    /*
        2 PASSO - CARACTERIZAÇÃO DO LOCAL (14)
     */

    @DatabaseField(columnName = "ENCOSTA_NATURAL", canBeNull = true)
    private Boolean encostaNatural;

    @DatabaseField(columnName = "TALUDE_CORTE", canBeNull = true)
    private Boolean taludeCorte;

    @DatabaseField(columnName = "ATERRO_LANCADO", canBeNull = true)
    private Boolean aterroLancado;

    @DatabaseField(columnName = "PAREDE_ROCHOSA", canBeNull = true)
    private Boolean paredeRochosa;

    @DatabaseField(columnName = "ALTURA_ENCOSTA", canBeNull = true)
    private Double alturaEncosta;

    @DatabaseField(columnName = "ALTURA_TALUDE", canBeNull = true)
    private Double alturaTalude;

    @DatabaseField(columnName = "ALTURA_ATERRO", canBeNull = true)
    private Double alturaAterro;

    @DatabaseField(columnName = "ALTURA_PAREDE", canBeNull = true)
    private Double alturaParede;

    @DatabaseField(columnName = "DISTANCIA_BASE_TALUDE", canBeNull = true)
    private Double distanciaBaseTalude;

    @DatabaseField(columnName = "DISTANCIA_BASE_ATERRO", canBeNull = true)
    private Double distanciaBaseAterro;

    @DatabaseField(columnName = "ALTURA_TOPO_TALUDE", canBeNull = true)
    private Double alturaTopoTalude;

    @DatabaseField(columnName = "ALTURA_TOPO_ATERRO", canBeNull = true)
    private Double alturaTopoAterro;

    @DatabaseField(columnName = "BLOCOS_ROCHA_MATACOES", canBeNull = true)
    private Boolean blocosRochasMatacoes;

    @DatabaseField(columnName = "LIXO_ENTULHO", canBeNull = true)
    private Boolean lixoEntulho;

     /*
        3 PASSO - ÁGUA (6)
     */

    @DatabaseField(columnName = "CONCENTRA_AGUA_CHUVA", canBeNull = true)
    private Boolean concentraAguaChuva;

    @DatabaseField(columnName = "CONCENTRA_AGUA_SERVIDA", canBeNull = true)
    private Boolean concentraAguaServida;

    @DatabaseField(columnName = "DRENAGE_SUPERFICIAL", canBeNull = true)
    private Integer drenageSuperficial;


    @DatabaseField(columnName = "ESGOTO", canBeNull = true)
    private Integer esgoto;


    @DatabaseField(columnName = "USO_AGUA_MORADIA", canBeNull = true)
    private Integer usoAguaMoradia;


    @DatabaseField(columnName = "AGUA_MORADIA_VAZAMENTO", canBeNull = true)
    private Integer aguaMoradiaVazamento;


    @DatabaseField(columnName = "TIPO_VAZAMENTO", canBeNull = true)
    private Integer tipoVazamento;

    @DatabaseField(columnName = "MINAS_DAGUA", canBeNull = true)
    private Integer minasDagua;
    //
     /*
        4 PASSO - VEGETÇÃO NO TALUDE OU PROXIMIDADES (4)
     */

    @DatabaseField(columnName = "ARVORES", canBeNull = true)
    private Boolean arvores;

    @DatabaseField(columnName = "VEGETACAO_RASTEIRA", canBeNull = true)
    private Boolean vegetacaoRasteira;

    @DatabaseField(columnName = "AREA_DESMATADA", canBeNull = true)
    private Boolean areaDesmatada;

    @DatabaseField(columnName = "AREA_CULTIVO", canBeNull = true)
    private String areaCultivo;

     /*
        5 PASSO - SINAIS DE MOVIMENTAÇÕES (5)
     */

    @DatabaseField(columnName = "TRINCA_TERRENO", canBeNull = true)
    private Boolean trincaTerreno;

    @DatabaseField(columnName = "TRINCA_MORADIA", canBeNull = true)
    private Boolean trincaMoradia;

    @DatabaseField(columnName = "DEGRAUS_ABATIMENTO", canBeNull = true)
    private Boolean degrausAbatimento;

    @DatabaseField(columnName = "INCLINACAO_ARVORES", canBeNull = true)
    private Boolean arvoresInclinacao;

    @DatabaseField(columnName = "INCLINACAO_POSTES", canBeNull = true)
    private Boolean postesInclinacao;

    @DatabaseField(columnName = "INCLINACAO_MUROS", canBeNull = true)
    private Boolean murosInclinacao;

    @DatabaseField(columnName = "MURO_PAREDE_EMBARRIGADO", canBeNull = true)
    private Boolean muroParedeEmbarrigado;

    @DatabaseField(columnName = "CICATRIZ_ESCORREGAMENTO", canBeNull = true)
    private Boolean cicatrizEscorregamento;

     /*
        6 PASSOO - TIPO DE PROCESSOS DE INSTABILIZAÇÃO ESPERADOS OU JÁ OCORRIDOS (3)
     */

    @DatabaseField(columnName = "ESCORREGAMENTO_NATURAL", canBeNull = true)
    private Boolean escorregamentoNatural;

    @DatabaseField(columnName = "ESCORREGAMENTO_CORTE", canBeNull = true)
    private Boolean escorregamentoCorte;

    @DatabaseField(columnName = "ESCORREGAMENTO_ATERRO", canBeNull = true)
    private Boolean escorregamentoAterro;

    @DatabaseField(columnName = "QUEDA_BLOCOS", canBeNull = true)
    private Boolean quedaBlocos;

    @DatabaseField(columnName = "ROLAMENTO_BLOCOS", canBeNull = true)
    private Boolean rolamentoBlocos;

     /*
        7 PASSO - DETERMINAÇÃO DO GRAU DE RISCO
     */

    @DatabaseField(columnName = "RISCO", canBeNull = true)
    private Integer risco;

    /*
        8 PASSO - NECESSIDADE DE REMOÇÃO (2)
     */

    @DatabaseField(columnName = "QUANTIDADE_MORADIAS", canBeNull = true)
    private Integer quantidadeMoradias;

    @DatabaseField(columnName = "QUANTIDADE_PESSOAS", canBeNull = true)
    private Integer quantidadePessoas;

     /*
        8 PASSO - OUTRAS INFORMAÇÕES
     */

    @DatabaseField(columnName = "INFORMAÇÕES", canBeNull = true)
    private String informacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public Integer getTipoMoradia() {
        return tipoMoradia;
    }

    public void setTipoMoradia(Integer tipoMoradia) {
        this.tipoMoradia = tipoMoradia;
    }

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

    public Double getAlturaEncosta() {
        return alturaEncosta;
    }

    public void setAlturaEncosta(Double alturaEncosta) {
        this.alturaEncosta = alturaEncosta;
    }

    public Double getAlturaTalude() {
        return alturaTalude;
    }

    public void setAlturaTalude(Double alturaTalude) {
        this.alturaTalude = alturaTalude;
    }

    public Double getAlturaAterro() {
        return alturaAterro;
    }

    public void setAlturaAterro(Double alturaAterro) {
        this.alturaAterro = alturaAterro;
    }

    public Double getAlturaParede() {
        return alturaParede;
    }

    public void setAlturaParede(Double alturaParede) {
        this.alturaParede = alturaParede;
    }

    public Double getDistanciaBaseTalude() {
        return distanciaBaseTalude;
    }

    public void setDistanciaBaseTalude(Double distanciaBaseTalude) {
        this.distanciaBaseTalude = distanciaBaseTalude;
    }

    public Double getDistanciaBaseAterro() {
        return distanciaBaseAterro;
    }

    public void setDistanciaBaseAterro(Double distanciaBaseAterro) {
        this.distanciaBaseAterro = distanciaBaseAterro;
    }

    public Double getAlturaTopoTalude() {
        return alturaTopoTalude;
    }

    public void setAlturaTopoTalude(Double alturaTopoTalude) {
        this.alturaTopoTalude = alturaTopoTalude;
    }

    public Double getAlturaTopoAterro() {
        return alturaTopoAterro;
    }

    public void setAlturaTopoAterro(Double alturaTopoAterro) {
        this.alturaTopoAterro = alturaTopoAterro;
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

    public Integer getDrenageSuperficial() {
        return drenageSuperficial;
    }

    public void setDrenageSuperficial(Integer drenageSuperficial) {
        this.drenageSuperficial = drenageSuperficial;
    }

    public Integer getEsgoto() {
        return esgoto;
    }

    public void setEsgoto(Integer esgoto) {
        this.esgoto = esgoto;
    }

    public Integer getUsoAguaMoradia() {
        return usoAguaMoradia;
    }

    public void setUsoAguaMoradia(Integer usoAguaMoradia) {
        this.usoAguaMoradia = usoAguaMoradia;
    }

    public Integer getAguaMoradiaVazamento() {
        return aguaMoradiaVazamento;
    }

    public void setAguaMoradiaVazamento(Integer aguaMoradiaVazamento) {
        this.aguaMoradiaVazamento = aguaMoradiaVazamento;
    }

    public Integer getTipoVazamento() {
        return tipoVazamento;
    }

    public void setTipoVazamento(Integer tipoVazamento) {
        this.tipoVazamento = tipoVazamento;
    }

    public Integer getMinasDagua() {
        return minasDagua;
    }

    public void setMinasDagua(Integer minasDagua) {
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

    public Boolean getTrincaTerreno() {
        return trincaTerreno;
    }

    public void setTrincaTerreno(Boolean trincaTerreno) {
        this.trincaTerreno = trincaTerreno;
    }

    public Boolean getTrincaMoradia() {
        return trincaMoradia;
    }

    public void setTrincaMoradia(Boolean trincaMoradia) {
        this.trincaMoradia = trincaMoradia;
    }

    public Boolean getDegrausAbatimento() {
        return degrausAbatimento;
    }

    public void setDegrausAbatimento(Boolean degrausAbatimento) {
        this.degrausAbatimento = degrausAbatimento;
    }

    public Boolean getArvoresInclinacao() {
        return arvoresInclinacao;
    }

    public void setArvoresInclinacao(Boolean arvoresInclinacao) {
        this.arvoresInclinacao = arvoresInclinacao;
    }

    public Boolean getPostesInclinacao() {
        return postesInclinacao;
    }

    public void setPostesInclinacao(Boolean postesInclinacao) {
        this.postesInclinacao = postesInclinacao;
    }

    public Boolean getMurosInclinacao() {
        return murosInclinacao;
    }

    public void setMurosInclinacao(Boolean murosInclinacao) {
        this.murosInclinacao = murosInclinacao;
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

    public Boolean getEscorregamentoNatural() {
        return escorregamentoNatural;
    }

    public void setEscorregamentoNatural(Boolean escorregamentoNatural) {
        this.escorregamentoNatural = escorregamentoNatural;
    }

    public Boolean getEscorregamentoCorte() {
        return escorregamentoCorte;
    }

    public void setEscorregamentoCorte(Boolean escorregamentoCorte) {
        this.escorregamentoCorte = escorregamentoCorte;
    }

    public Boolean getEscorregamentoAterro() {
        return escorregamentoAterro;
    }

    public void setEscorregamentoAterro(Boolean escorregamentoAterro) {
        this.escorregamentoAterro = escorregamentoAterro;
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

    public Integer getRisco() {
        return risco;
    }

    public void setRisco(Integer risco) {
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

    public Vistoria(){}

    public Vistoria(Long id, Localizacao localizacao, String nomeMorador, Integer tipoMoradia,
                    Boolean encostaNatural, Boolean taludeCorte, Boolean aterroLancado,
                    Boolean paredeRochosa, Double alturaEncosta, Double alturaTalude,
                    Double alturaAterro, Double alturaParede, Double distanciaBaseTalude,
                    Double distanciaBaseAterro, Double alturaTopoTalude, Double alturaTopoAterro,
                    Boolean blocosRochasMatacoes, Boolean lixoEntulho, Boolean concentraAguaChuva,
                    Boolean concentraAguaServida, Integer drenageSuperficial, Integer esgoto,
                    Integer usoAguaMoradia, Integer aguaMoradiaVazamento, Integer tipoVazamento,
                    Integer minasDagua, Boolean arvores, Boolean vegetacaoRasteira,
                    Boolean areaDesmatada, String areaCultivo, Boolean trincaTerreno,
                    Boolean trincaMoradia, Boolean degrausAbatimento, Boolean arvoresInclinacao,
                    Boolean postesInclinacao, Boolean murosInclinacao, Boolean muroParedeEmbarrigado,
                    Boolean cicatrizEscorregamento, Boolean escorregamentoNatural,
                    Boolean escorregamentoCorte, Boolean escorregamentoAterro, Boolean quedaBlocos,
                    Boolean rolamentoBlocos, Integer risco, Integer quantidadeMoradias,
                    Integer quantidadePessoas, String informacoes) {
        this.id = id;
        this.localizacao = localizacao;
        this.nomeMorador = nomeMorador;
        this.tipoMoradia = tipoMoradia;
        this.encostaNatural = encostaNatural;
        this.taludeCorte = taludeCorte;
        this.aterroLancado = aterroLancado;
        this.paredeRochosa = paredeRochosa;
        this.alturaEncosta = alturaEncosta;
        this.alturaTalude = alturaTalude;
        this.alturaAterro = alturaAterro;
        this.alturaParede = alturaParede;
        this.distanciaBaseTalude = distanciaBaseTalude;
        this.distanciaBaseAterro = distanciaBaseAterro;
        this.alturaTopoTalude = alturaTopoTalude;
        this.alturaTopoAterro = alturaTopoAterro;
        this.blocosRochasMatacoes = blocosRochasMatacoes;
        this.lixoEntulho = lixoEntulho;
        this.concentraAguaChuva = concentraAguaChuva;
        this.concentraAguaServida = concentraAguaServida;
        this.drenageSuperficial = drenageSuperficial;
        this.esgoto = esgoto;
        this.usoAguaMoradia = usoAguaMoradia;
        this.aguaMoradiaVazamento = aguaMoradiaVazamento;
        this.tipoVazamento = tipoVazamento;
        this.minasDagua = minasDagua;
        this.arvores = arvores;
        this.vegetacaoRasteira = vegetacaoRasteira;
        this.areaDesmatada = areaDesmatada;
        this.areaCultivo = areaCultivo;
        this.trincaTerreno = trincaTerreno;
        this.trincaMoradia = trincaMoradia;
        this.degrausAbatimento = degrausAbatimento;
        this.arvoresInclinacao = arvoresInclinacao;
        this.postesInclinacao = postesInclinacao;
        this.murosInclinacao = murosInclinacao;
        this.muroParedeEmbarrigado = muroParedeEmbarrigado;
        this.cicatrizEscorregamento = cicatrizEscorregamento;
        this.escorregamentoNatural = escorregamentoNatural;
        this.escorregamentoCorte = escorregamentoCorte;
        this.escorregamentoAterro = escorregamentoAterro;
        this.quedaBlocos = quedaBlocos;
        this.rolamentoBlocos = rolamentoBlocos;
        this.risco = risco;
        this.quantidadeMoradias = quantidadeMoradias;
        this.quantidadePessoas = quantidadePessoas;
        this.informacoes = informacoes;
    }

    public Vistoria(String nomeMorador, Integer tipoMoradia, Boolean encostaNatural,
                    Boolean taludeCorte, Boolean aterroLancado, Boolean paredeRochosa,
                    Double alturaEncosta, Double alturaTalude, Double alturaAterro,
                    Double alturaParede, Double distanciaBaseTalude, Double distanciaBaseAterro,
                    Double alturaTopoTalude, Double alturaTopoAterro, Boolean blocosRochasMatacoes,
                    Boolean lixoEntulho, Boolean concentraAguaChuva, Boolean concentraAguaServida,
                    Integer drenageSuperficial, Integer esgoto, Integer usoAguaMoradia,
                    Integer aguaMoradiaVazamento, Integer tipoVazamento, Integer minasDagua,
                    Boolean arvores, Boolean vegetacaoRasteira, Boolean areaDesmatada,
                    String areaCultivo, Boolean trincaTerreno, Boolean trincaMoradia,
                    Boolean degrausAbatimento, Boolean arvoresInclinacao, Boolean postesInclinacao,
                    Boolean murosInclinacao, Boolean muroParedeEmbarrigado,
                    Boolean cicatrizEscorregamento, Boolean escorregamentoNatural,
                    Boolean escorregamentoCorte, Boolean escorregamentoAterro, Boolean quedaBlocos,
                    Boolean rolamentoBlocos, Integer risco, Integer quantidadeMoradias,
                    Integer quantidadePessoas, String informacoes) {
        this.nomeMorador = nomeMorador;
        this.tipoMoradia = tipoMoradia;
        this.encostaNatural = encostaNatural;
        this.taludeCorte = taludeCorte;
        this.aterroLancado = aterroLancado;
        this.paredeRochosa = paredeRochosa;
        this.alturaEncosta = alturaEncosta;
        this.alturaTalude = alturaTalude;
        this.alturaAterro = alturaAterro;
        this.alturaParede = alturaParede;
        this.distanciaBaseTalude = distanciaBaseTalude;
        this.distanciaBaseAterro = distanciaBaseAterro;
        this.alturaTopoTalude = alturaTopoTalude;
        this.alturaTopoAterro = alturaTopoAterro;
        this.blocosRochasMatacoes = blocosRochasMatacoes;
        this.lixoEntulho = lixoEntulho;
        this.concentraAguaChuva = concentraAguaChuva;
        this.concentraAguaServida = concentraAguaServida;
        this.drenageSuperficial = drenageSuperficial;
        this.esgoto = esgoto;
        this.usoAguaMoradia = usoAguaMoradia;
        this.aguaMoradiaVazamento = aguaMoradiaVazamento;
        this.tipoVazamento = tipoVazamento;
        this.minasDagua = minasDagua;
        this.arvores = arvores;
        this.vegetacaoRasteira = vegetacaoRasteira;
        this.areaDesmatada = areaDesmatada;
        this.areaCultivo = areaCultivo;
        this.trincaTerreno = trincaTerreno;
        this.trincaMoradia = trincaMoradia;
        this.degrausAbatimento = degrausAbatimento;
        this.arvoresInclinacao = arvoresInclinacao;
        this.postesInclinacao = postesInclinacao;
        this.murosInclinacao = murosInclinacao;
        this.muroParedeEmbarrigado = muroParedeEmbarrigado;
        this.cicatrizEscorregamento = cicatrizEscorregamento;
        this.escorregamentoNatural = escorregamentoNatural;
        this.escorregamentoCorte = escorregamentoCorte;
        this.escorregamentoAterro = escorregamentoAterro;
        this.quedaBlocos = quedaBlocos;
        this.rolamentoBlocos = rolamentoBlocos;
        this.risco = risco;
        this.quantidadeMoradias = quantidadeMoradias;
        this.quantidadePessoas = quantidadePessoas;
        this.informacoes = informacoes;
    }
}
