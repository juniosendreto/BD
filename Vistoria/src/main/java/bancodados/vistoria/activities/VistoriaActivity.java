package bancodados.vistoria.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bancodados.vistoria.R;
import bancodados.vistoria.Util.BDFacade;
import bancodados.vistoria.Util.FileUtil;
import bancodados.vistoria.Util.FragmentDialog;
import bancodados.vistoria.core.service.dao.Adapter;
import bancodados.vistoria.core.service.dao.AdapterCamera;
import bancodados.vistoria.core.service.dao.AdapterVistoria;
import bancodados.vistoria.core.service.dao.FotoVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.LocalizacaoDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.VistoriaDaoImpl;
import bancodados.vistoria.model.FotoVistoria;
import bancodados.vistoria.model.Localizacao;
import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.model.UsuarioVistoria;
import bancodados.vistoria.model.Vistoria;

public class VistoriaActivity extends AppCompatActivity {

    public static final int PICK_PHOTO_REQUEST = 100;

    private Localizacao localizacao;
    private Usuario usuario;
    private BDFacade bdFacade;
    //private List<FotoVistoria> fotoVistorias;
    private List<Bitmap> bitmaps;
    private File mTempFile;
    private Map<Integer, List<String>> mPathPhotos;
    private Integer mHashmapKey;

    private ImageButton mCamera1;
    private ImageButton mCamera2;
    private ImageButton mCamera3;
    private ImageButton mCamera4;
    private ImageButton mCamera5;
    private ImageButton mCamera6;
    private ImageButton mCamera7;
    private ImageButton mCamera8;
    private ImageButton mCamera9;
    private ImageButton mCamera10;
    private ImageButton mCamera11;
    private ImageButton mCamera12;
    private ImageButton mCamera13;
    private ImageButton mCamera14;
    private ImageButton mCamera15;

    private ImageButton listarFotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoria);
        bdFacade = new BDFacade(getApplicationContext());
        bitmaps = new ArrayList<>();

        mPathPhotos = new HashMap<>();

        mCamera1 = (ImageButton) findViewById(R.id.camera1);
        mCamera2 = (ImageButton) findViewById(R.id.camera2);
        mCamera3 = (ImageButton) findViewById(R.id.camera3);
        mCamera4 = (ImageButton) findViewById(R.id.camera4);
        mCamera5 = (ImageButton) findViewById(R.id.camera5);
        mCamera6 = (ImageButton) findViewById(R.id.camera6);
        mCamera7 = (ImageButton) findViewById(R.id.camera7);
        mCamera8 = (ImageButton) findViewById(R.id.camera8);
        mCamera9 = (ImageButton) findViewById(R.id.camera9);
        mCamera10 = (ImageButton) findViewById(R.id.camera10);
        mCamera11 = (ImageButton) findViewById(R.id.camera11);
        mCamera12 = (ImageButton) findViewById(R.id.camera12);
        mCamera13 = (ImageButton) findViewById(R.id.camera13);
        mCamera14 = (ImageButton) findViewById(R.id.camera14);
        mCamera15 = (ImageButton) findViewById(R.id.camera15);

        mCamera1.setId(new Integer(0));
        mCamera2.setId(new Integer(1));
        mCamera3.setId(new Integer(2));
        mCamera4.setId(new Integer(3));
        mCamera5.setId(new Integer(4));
        mCamera6.setId(new Integer(5));
        mCamera7.setId(new Integer(6));
        mCamera8.setId(new Integer(7));
        mCamera9.setId(new Integer(8));
        mCamera10.setId(new Integer(9));
        mCamera11.setId(new Integer(10));
        mCamera12.setId(new Integer(11));
        mCamera13.setId(new Integer(12));
        mCamera14.setId(new Integer(13));
        mCamera15.setId(new Integer(14));

        /*
            0 - Nova Vistoria
            1 - Adicionar Ao ponto
            3 - Ver Vistoria (Recuperar tudo da vistoria)
         */

        final Integer tipoVistoria = (Integer) getIntent().getSerializableExtra("tipoVistoria");

        final Adapter adapter = new Adapter(getApplicationContext());
        final AdapterVistoria adapterVistoria = new AdapterVistoria(getApplicationContext());
        final Intent intent = new Intent(this, MainActivity.class);

        localizacao = (Localizacao) getIntent().getSerializableExtra("localizacao");
        final Vistoria vistoriaCriada = (Vistoria) getIntent().getSerializableExtra("vistoria");
        usuario = Usuario.getInstance();

        final List<Vistoria> vistoriaList = new ArrayList<>();
        final UsuarioVistoria usuarioVistoria = new UsuarioVistoria(getApplicationContext());
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        final Date date = new Date();

        /* PASSO 1 */

        final TextView passo1TV = (TextView) findViewById(R.id.passo1TV);
        final LinearLayout passo1LL = (LinearLayout) findViewById(R.id.passo1LL);
        final Vistoria vistoria = new Vistoria(getApplicationContext());

        final EditText bairroET = (EditText) findViewById(R.id.bairroET);
        final EditText municipioET = (EditText) findViewById(R.id.municipioET);
        final EditText nomeMoradorET = (EditText) findViewById(R.id.nomeMoradorET);
        final EditText condicoesAreaET = (EditText) findViewById(R.id.condicoesAreaET);
        final RadioButton alvenariaRB = (RadioButton) findViewById(R.id.alvenariaRB);
        final RadioButton madeiraRB = (RadioButton) findViewById(R.id.madeiraRB);
        final RadioButton mistoRB = (RadioButton) findViewById(R.id.mistoRB);

        /* PASSO 2 */

        final TextView passo2TV = (TextView) findViewById(R.id.passo2TV);
        final LinearLayout passo2LL = (LinearLayout) findViewById(R.id.passo2LL);

        final CheckBox encostaCB = (CheckBox) findViewById(R.id.encostaCB);
        final LinearLayout encostaLL = (LinearLayout) findViewById(R.id.encostaLL);
        final EditText alturaEncostaET = (EditText) findViewById(R.id.alturaEncostaET);

        final CheckBox taludeCB = (CheckBox) findViewById(R.id.taludeCB);
        final LinearLayout taludeLL = (LinearLayout) findViewById(R.id.taludeLL);
        final EditText alturaTaludeET = (EditText) findViewById(R.id.alturaTaludeET);
        final EditText distanciaBaseTaludeET = (EditText) findViewById(R.id.distanciaBaseTaludeET);
        final EditText alturaTopoTaludeET = (EditText) findViewById(R.id.alturaTopoTaludeET);

        final CheckBox aterroCB = (CheckBox) findViewById(R.id.aterroCB);
        final LinearLayout aterroLL = (LinearLayout) findViewById(R.id.aterroLL);
        final EditText alturaAterroET = (EditText) findViewById(R.id.alturaAterroET);
        final EditText distanciaBaseAterroET = (EditText) findViewById(R.id.distanciaBaseAterroET);
        final EditText alturaTopoAterroET = (EditText) findViewById(R.id.alturaTopoAterroET);

        final CheckBox paredeCB = (CheckBox) findViewById(R.id.paredeCB);
        final LinearLayout paredeLL = (LinearLayout) findViewById(R.id.paredeLL);
        final EditText alturaParedeET = (EditText) findViewById(R.id.alturaParedeET);

        final CheckBox blocosRochasCB = (CheckBox) findViewById(R.id.blocosRochasCB);
        final CheckBox lixoEntulhoCB = (CheckBox) findViewById(R.id.lixoEntulhoCB);

        /* PASSO 3 */

        final TextView passo3TV = (TextView) findViewById(R.id.passo3TV);
        final LinearLayout passo3LL = (LinearLayout) findViewById(R.id.passo3LL);

        final CheckBox concentracaoAguaCB = (CheckBox) findViewById(R.id.concentracaoAguaCB);
        final CheckBox lancamentoAguaCB = (CheckBox) findViewById(R.id.lancamentoAguaCB);

        final RadioButton inexistenteRB = (RadioButton) findViewById(R.id.inexistenteRB);
        final RadioButton precarioRB = (RadioButton) findViewById(R.id.precarioRB);
        final RadioButton satisfatorioRB = (RadioButton) findViewById(R.id.satisfatorioRB);

        final RadioButton fossaRB = (RadioButton) findViewById(R.id.fossaRB);
        final RadioButton canalizadoRB = (RadioButton) findViewById(R.id.canalizadoRB);
        final RadioButton superficieRB = (RadioButton) findViewById(R.id.superficieRB);

        final RadioButton prefeituraCRB = (RadioButton) findViewById(R.id.prefeituraCRB);
        final RadioButton mangueiraRB = (RadioButton) findViewById(R.id.mangueiraRB);

        final RadioGroup vazamentoSNRG = (RadioGroup) findViewById(R.id.vazamentoSNRG);
        final RadioButton vazamentoSRB = (RadioButton) findViewById(R.id.vazamentoSRB);
        final RadioButton vazamentoEsgotoRB = (RadioButton) findViewById(R.id.vazamentoEsgotoRB);
        final RadioButton vazamentoAguaRB = (RadioButton) findViewById(R.id.vazamentoAguaRB);
        final RadioButton vazamentoNRB = (RadioButton) findViewById(R.id.vazamentoNRB);

        final RadioButton peRB = (RadioButton) findViewById(R.id.peRB);
        final RadioButton meioRB = (RadioButton) findViewById(R.id.meioRB);
        final RadioButton topoTaludeRB = (RadioButton) findViewById(R.id.topoTaludeRB);


         /* PASSO 4 */

        final TextView passo4TV = (TextView) findViewById(R.id.passo4TV);
        final LinearLayout passo4LL = (LinearLayout) findViewById(R.id.passo4LL);

        final CheckBox presencaArvoresCB = (CheckBox) findViewById(R.id.presencaArvoresCB);
        final CheckBox vegetacaoRasteiraCB = (CheckBox) findViewById(R.id.vegetacaoRasteiraCB);
        final CheckBox areaDesmatadaCB = (CheckBox) findViewById(R.id.areaDesmatadaCB);
        final EditText areaCultivoET = (EditText) findViewById(R.id.areaCultivoET);

         /* PASSO 5 */

        final TextView passo5TV = (TextView) findViewById(R.id.passo5TV);
        final LinearLayout passo5LL = (LinearLayout) findViewById(R.id.passo5LL);

        final CheckBox trincasTerrenoCB = (CheckBox) findViewById(R.id.trincasTerrenoCB);
        final CheckBox trincasMoradiaCB = (CheckBox) findViewById(R.id.trincasMoradiaCB);
        final CheckBox degrausAbatimentoCB = (CheckBox) findViewById(R.id.degrausAbatimentoCB);

        final CheckBox arvoreInclinacaoCB = (CheckBox) findViewById(R.id.arvoreInclinacaoCB);
        final CheckBox postesInclinacaoCB = (CheckBox) findViewById(R.id.postesInclinacaoCB);
        final CheckBox murosInclinacaoCB = (CheckBox) findViewById(R.id.murosInclinacaoCB);

        final CheckBox paredesEmbarrigadosCB = (CheckBox) findViewById(R.id.paredesEmbarrigadosCB);
        final CheckBox cicatrizEscorregamentoCB = (CheckBox) findViewById(R.id.cicatrizEscorregamentoCB);

         /* PASSO 6 */

        final TextView passo6TV = (TextView) findViewById(R.id.passo6TV);
        final LinearLayout passo6LL = (LinearLayout) findViewById(R.id.passo6LL);

        final CheckBox escorregamentoTaludeNaturalCB = (CheckBox) findViewById(R.id.escorregamentoTaludeNaturalCB);
        final CheckBox escorregamentoTaludeCorteCB = (CheckBox) findViewById(R.id.escorregamentoTaludeCorteCB);
        final CheckBox escorregamentoAterroCB = (CheckBox) findViewById(R.id.escorregamentoAterroCB);

        final CheckBox quedaBlocosCB = (CheckBox) findViewById(R.id.quedaBlocosCB);
        final CheckBox rolamentoBlocosCB = (CheckBox) findViewById(R.id.rolamentoBlocosCB);

         /* PASSO 7 */

        final TextView passo7TV = (TextView) findViewById(R.id.passo7TV);
        final LinearLayout passo7LL = (LinearLayout) findViewById(R.id.passo7LL);

        final RadioButton riscoMuitoAltoRB = (RadioButton) findViewById(R.id.riscoMuitoAltoRB);
        final RadioButton riscoAltoB = (RadioButton) findViewById(R.id.riscoAltoB);
        final RadioButton riscoMedioRB = (RadioButton) findViewById(R.id.riscoMedioRB);
        final RadioButton riscoBaixoRB = (RadioButton) findViewById(R.id.riscoBaixoRB);

         /* PASSO 8 */

        final TextView passo8TV = (TextView) findViewById(R.id.passo8TV);
        final LinearLayout passo8LL = (LinearLayout) findViewById(R.id.passo8LL);

        final EditText numeroMoradiasRiscoET = (EditText) findViewById(R.id.numeroMoradiasRiscoET);
        final EditText quantidadeRemocaoET = (EditText) findViewById(R.id.quantidadeRemocaoET);

         /* PASSO 9 */

        final TextView passo9TV = (TextView) findViewById(R.id.passo9TV);
        final LinearLayout passo9LL = (LinearLayout) findViewById(R.id.passo9LL);

        final EditText outrasInformacoesET = (EditText) findViewById(R.id.outrasInformacoesET);

        final Button salvarVistoriaB = (Button) findViewById(R.id.salvarVistoriaB);
        final Button alterarVistoriaB = (Button) findViewById(R.id.alterarVistoriaB);
        listarFotos = (ImageButton) findViewById(R.id.listarFotos);


        /* PASSO 1 */

        passo1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo1LL);
            }
        });

         /* PASSO 2 */

        passo2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo2LL);

            }
        });

        encostaCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityCheckBox(encostaCB, encostaLL);

            }
        });

        taludeCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityCheckBox(taludeCB, taludeLL);
            }
        });


        aterroCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityCheckBox(aterroCB, aterroLL);
            }
        });

        paredeCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityCheckBox(paredeCB, paredeLL);
            }
        });

        passo3TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo3LL);

            }
        });

        vazamentoSRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.radionButtonChecked(vazamentoSRB, vazamentoSNRG);
            }
        });

        vazamentoNRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityRadioButton(vazamentoNRB, vazamentoSNRG);
            }
        });

        passo4TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo4LL);

            }
        });

        passo5TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo5LL);
            }
        });

        passo6TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo6LL);

            }
        });

        passo7TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo7LL);

            }
        });

        passo8TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo8LL);

            }
        });

        passo9TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo9LL);

            }
        });

        if(tipoVistoria == 1){
            municipioET.setText(localizacao.getMunicipio());
            bairroET.setText(localizacao.getBairro());
            condicoesAreaET.setText(localizacao.getAcessoLocal());

        }else if(tipoVistoria == 2){

             /* PASSO 1 */

            List<RadioButton> radioButtonList =  new ArrayList<>();
            Boolean validarCheckBox;

            municipioET.setText(localizacao.getMunicipio());
            bairroET.setText(localizacao.getBairro());
            condicoesAreaET.setText(localizacao.getAcessoLocal());
            nomeMoradorET.setText(vistoriaCriada.getNomeMorador());

            radioButtonList.add(alvenariaRB);
            radioButtonList.add(madeiraRB);
            radioButtonList.add(mistoRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getTipoMoradia());
            radioButtonList.clear();

            /* PASSO 2 */

            validarCheckBox = adapterVistoria.recuperaCheck(encostaCB, vistoriaCriada.getEncostaNatural());
            if(validarCheckBox){
                alturaEncostaET.setText(String.valueOf(vistoriaCriada.getAlturaEncosta()));
                adapter.visibilityCheckBox(encostaCB, encostaLL);
            }

            validarCheckBox = adapterVistoria.recuperaCheck(taludeCB, vistoriaCriada.getTaludeCorte());
            if(validarCheckBox){
                alturaTaludeET.setText(String.valueOf(vistoriaCriada.getAlturaTalude()));
                distanciaBaseTaludeET.setText(String.valueOf(vistoriaCriada.getDistanciaBaseTalude()));
                alturaTopoTaludeET.setText(String.valueOf(vistoriaCriada.getAlturaTopoTalude()));
                adapter.visibilityCheckBox(taludeCB, taludeLL);
            }

            validarCheckBox = adapterVistoria.recuperaCheck(aterroCB, vistoriaCriada.getAterroLancado());
            if(validarCheckBox){
                alturaAterroET.setText(String.valueOf(vistoriaCriada.getAlturaAterro()));
                distanciaBaseAterroET.setText(String.valueOf(vistoriaCriada.getDistanciaBaseAterro()));
                alturaTopoAterroET.setText(String.valueOf(vistoriaCriada.getAlturaTopoAterro()));
                adapter.visibilityCheckBox(aterroCB, aterroLL);
            }

            validarCheckBox = adapterVistoria.recuperaCheck(paredeCB, vistoriaCriada.getParedeRochosa());
            if(validarCheckBox){
                alturaParedeET.setText(String.valueOf(vistoriaCriada.getAlturaParede()));
                adapter.visibilityCheckBox(paredeCB, paredeLL);
            }
            adapterVistoria.recuperaCheck(blocosRochasCB, vistoriaCriada.getBlocosRochasMatacoes());
            adapterVistoria.recuperaCheck(lixoEntulhoCB, vistoriaCriada.getLixoEntulho());

             /* PASSO 3 */

            adapterVistoria.recuperaCheck(concentracaoAguaCB, vistoriaCriada.getConcentraAguaChuva());
            adapterVistoria.recuperaCheck(lancamentoAguaCB, vistoriaCriada.getConcentraAguaServida());

            radioButtonList.add(inexistenteRB);
            radioButtonList.add(precarioRB);
            radioButtonList.add(satisfatorioRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getDrenageSuperficial());
            radioButtonList.clear();

            radioButtonList.add(fossaRB);
            radioButtonList.add(canalizadoRB);
            radioButtonList.add(superficieRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getEsgoto());
            radioButtonList.clear();

            radioButtonList.add(prefeituraCRB);
            radioButtonList.add(mangueiraRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getUsoAguaMoradia());
            radioButtonList.clear();

            radioButtonList.add(vazamentoSRB);
            radioButtonList.add(vazamentoNRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getAguaMoradiaVazamento());
            radioButtonList.clear();
            radioButtonList.add(vazamentoEsgotoRB);
            radioButtonList.add(vazamentoAguaRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getTipoVazamento());
            adapter.visibility(vazamentoSNRG);
            radioButtonList.clear();

            radioButtonList.add(peRB);
            radioButtonList.add(meioRB);
            radioButtonList.add(topoTaludeRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getMinasDagua());
            radioButtonList.clear();


            /* PASSO 4 */

            adapterVistoria.recuperaCheck(presencaArvoresCB, vistoriaCriada.getArvores());
            adapterVistoria.recuperaCheck(vegetacaoRasteiraCB, vistoriaCriada.getVegetacaoRasteira());
            adapterVistoria.recuperaCheck(areaDesmatadaCB, vistoriaCriada.getAreaDesmatada());
            areaCultivoET.setText(vistoriaCriada.getAreaCultivo());

            /* PASSO 5 */

            adapterVistoria.recuperaCheck(trincasMoradiaCB, vistoriaCriada.getTrincaMoradia());
            adapterVistoria.recuperaCheck(trincasTerrenoCB, vistoriaCriada.getTrincaTerreno());
            adapterVistoria.recuperaCheck(degrausAbatimentoCB, vistoriaCriada.getDegrausAbatimento());
            adapterVistoria.recuperaCheck(arvoreInclinacaoCB, vistoriaCriada.getArvoresInclinacao());
            adapterVistoria.recuperaCheck(postesInclinacaoCB, vistoriaCriada.getPostesInclinacao());
            adapterVistoria.recuperaCheck(murosInclinacaoCB, vistoriaCriada.getMurosInclinacao());
            adapterVistoria.recuperaCheck(paredesEmbarrigadosCB, vistoriaCriada.getMuroParedeEmbarrigado());
            adapterVistoria.recuperaCheck(cicatrizEscorregamentoCB, vistoriaCriada.getCicatrizEscorregamento());

            /* PASSO 6 */

            adapterVistoria.recuperaCheck(escorregamentoTaludeNaturalCB, vistoriaCriada.getEscorregamentoNatural());
            adapterVistoria.recuperaCheck(escorregamentoTaludeCorteCB, vistoriaCriada.getEscorregamentoCorte());
            adapterVistoria.recuperaCheck(escorregamentoAterroCB, vistoriaCriada.getEscorregamentoAterro());
            adapterVistoria.recuperaCheck(quedaBlocosCB, vistoriaCriada.getQuedaBlocos());
            adapterVistoria.recuperaCheck(rolamentoBlocosCB, vistoriaCriada.getRolamentoBlocos());

            /* PASSO 7 */

            radioButtonList.add(riscoMuitoAltoRB);
            radioButtonList.add(riscoAltoB);
            radioButtonList.add(riscoMedioRB);
            radioButtonList.add(riscoBaixoRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getRisco());
            radioButtonList.clear();


            /* PASSO 8 */

            numeroMoradiasRiscoET.setText(String.valueOf(vistoriaCriada.getQuantidadeMoradias()));
            quantidadeRemocaoET.setText(String.valueOf(vistoriaCriada.getQuantidadePessoas()));

            /* PASSO 9 */

            outrasInformacoesET.setText(vistoriaCriada.getInformacoes());

            UsuarioVistoria usuarioVistoriaAux = bdFacade.getUsuarioVistoriaDao().findByIdVistoria(vistoriaCriada);
            Usuario usuarioAux = (Usuario) bdFacade.getUsuarioDao().findById(Usuario.class, usuarioVistoriaAux.getUsuario().getId());
            if(usuarioAux.getId().equals(Usuario.getInstance().getId())){
                alterarVistoriaB.setVisibility(View.VISIBLE);
                salvarVistoriaB.setText("Salvar como uma nova vistoria");
            }else{
                alterarVistoriaB.setVisibility(View.GONE);
                salvarVistoriaB.setText("Salvar Vistoria");
            }

            if(new File(FileUtil.vistoriasDirectory().getPath() + "/V_" + vistoriaCriada.getId()).exists()){
                mTempFile = new File(FileUtil.tempDirectory().getAbsolutePath());
                FileUtil.copy(new File(FileUtil.vistoriasDirectory().getAbsolutePath() + "/V_" + vistoriaCriada.getId()), mTempFile);
                listarFotos.setVisibility(View.VISIBLE);
            }else{
                listarFotos.setVisibility(View.GONE);
            }
        }

        alterarVistoriaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<RadioButton> radioButtonList = new ArrayList<>();
                Double doubleNull = null;

                localizacao.setMunicipio(municipioET.getText().toString());
                localizacao.setBairro(bairroET.getText().toString());
                localizacao.setAcessoLocal(condicoesAreaET.getText().toString());
                vistoria.setNomeMorador(nomeMoradorET.getText().toString());

                radioButtonList.add(alvenariaRB);
                radioButtonList.add(madeiraRB);
                radioButtonList.add(mistoRB);
                vistoria.setTipoMoradia(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();


                /* PASSO 2 */
                if(encostaCB.isChecked()){
                    if(!(alturaEncostaET.getText().toString().equals(""))){
                        vistoria.setEncostaNatural(encostaCB.isChecked());
                        vistoria.setAlturaEncosta(Double.valueOf(alturaEncostaET.getText().toString()));
                    }
                }else{
                    vistoria.setEncostaNatural(encostaCB.isChecked());
                    vistoria.setAlturaEncosta(doubleNull);
                }
                if(taludeCB.isChecked()){
                    if(!(alturaTaludeET.getText().toString().equals("") ||
                            distanciaBaseTaludeET.getText().toString().equals("") ||
                            alturaTopoTaludeET.getText().toString().equals(""))){

                        vistoria.setTaludeCorte(taludeCB.isChecked());
                        vistoria.setAlturaTalude(Double.valueOf(alturaTaludeET.getText().toString()));
                        vistoria.setDistanciaBaseTalude(Double.valueOf(distanciaBaseTaludeET.getText().toString()));
                        vistoria.setAlturaTopoTalude(Double.valueOf(alturaTopoTaludeET.getText().toString()));
                    }
                }else{
                    vistoria.setTaludeCorte(taludeCB.isChecked());
                    vistoria.setAlturaTalude(doubleNull);
                    vistoria.setDistanciaBaseTalude(doubleNull);
                    vistoria.setAlturaTopoTalude(doubleNull);
                }
                if(aterroCB.isChecked()){
                    if(!(alturaAterroET.getText().toString().equals("") ||
                            distanciaBaseAterroET.getText().toString().equals("") ||
                            alturaTopoAterroET.getText().toString().equals(""))){

                        vistoria.setAterroLancado(aterroCB.isChecked());
                        vistoria.setAlturaAterro(Double.valueOf(alturaAterroET.getText().toString()));
                        vistoria.setDistanciaBaseAterro(Double.valueOf(distanciaBaseAterroET.getText().toString()));
                        vistoria.setAlturaTopoAterro(Double.valueOf(alturaTopoAterroET.getText().toString()));

                    }
                }else{
                    vistoria.setAterroLancado(aterroCB.isChecked());
                    vistoria.setAlturaAterro(doubleNull);
                    vistoria.setDistanciaBaseAterro(doubleNull);
                    vistoria.setAlturaTopoAterro(doubleNull);
                }
                if(paredeCB.isChecked()){
                    if(!(alturaParedeET.getText().toString().equals(""))){
                        vistoria.setParedeRochosa(paredeCB.isChecked());
                        vistoria.setAlturaParede(Double.valueOf(alturaParedeET.getText().toString()));
                    }

                }else{
                    vistoria.setParedeRochosa(paredeCB.isChecked());
                    vistoria.setAlturaParede(doubleNull);
                }

                vistoria.setBlocosRochasMatacoes(blocosRochasCB.isChecked());
                vistoria.setLixoEntulho(lixoEntulhoCB.isChecked());

                /* PASSO 3 */
                vistoria.setConcentraAguaChuva(concentracaoAguaCB.isChecked());
                vistoria.setConcentraAguaServida(lancamentoAguaCB.isChecked());


                radioButtonList.add(inexistenteRB);
                radioButtonList.add(precarioRB);
                radioButtonList.add(satisfatorioRB);
                vistoria.setDrenageSuperficial(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                radioButtonList.add(fossaRB);
                radioButtonList.add(canalizadoRB);
                radioButtonList.add(superficieRB);
                vistoria.setEsgoto(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                radioButtonList.add(prefeituraCRB);
                radioButtonList.add(mangueiraRB);
                vistoria.setUsoAguaMoradia(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                radioButtonList.add(vazamentoSRB);
                radioButtonList.add(vazamentoNRB);
                vistoria.setAguaMoradiaVazamento(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();
                radioButtonList.add(vazamentoEsgotoRB);
                radioButtonList.add(vazamentoAguaRB);
                vistoria.setTipoVazamento(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();


                radioButtonList.add(peRB);
                radioButtonList.add(meioRB);
                radioButtonList.add(topoTaludeRB);
                vistoria.setMinasDagua(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                /* PASSO 4 */

                vistoria.setArvores(presencaArvoresCB.isChecked());
                vistoria.setVegetacaoRasteira(vegetacaoRasteiraCB.isChecked());
                vistoria.setAreaDesmatada(areaDesmatadaCB.isChecked());
                vistoria.setAreaCultivo(areaCultivoET.getText().toString());


                /* PASSO 5 */

                vistoria.setTrincaMoradia(trincasMoradiaCB.isChecked());
                vistoria.setTrincaTerreno(trincasTerrenoCB.isChecked());
                vistoria.setDegrausAbatimento(degrausAbatimentoCB.isChecked());
                vistoria.setArvoresInclinacao(arvoreInclinacaoCB.isChecked());
                vistoria.setPostesInclinacao(postesInclinacaoCB.isChecked());
                vistoria.setMurosInclinacao(murosInclinacaoCB.isChecked());
                vistoria.setMuroParedeEmbarrigado(paredesEmbarrigadosCB.isChecked());
                vistoria.setCicatrizEscorregamento(cicatrizEscorregamentoCB.isChecked());

                /* PASSO 6 */

                vistoria.setEscorregamentoNatural(escorregamentoTaludeNaturalCB.isChecked());
                vistoria.setEscorregamentoCorte(escorregamentoTaludeCorteCB.isChecked());
                vistoria.setEscorregamentoAterro(escorregamentoAterroCB.isChecked());
                vistoria.setQuedaBlocos(quedaBlocosCB.isChecked());
                vistoria.setRolamentoBlocos(rolamentoBlocosCB.isChecked());

                /* PASSO 7 */

                radioButtonList.add(riscoMuitoAltoRB);
                radioButtonList.add(riscoAltoB);
                radioButtonList.add(riscoMedioRB);
                radioButtonList.add(riscoBaixoRB);
                vistoria.setRisco(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                /* PASSO 8 */
                if(numeroMoradiasRiscoET.getText().toString().length() > 0)
                    vistoria.setQuantidadeMoradias(Integer.valueOf(numeroMoradiasRiscoET.getText().toString()));
                else
                    vistoria.setQuantidadeMoradias(0);
                if(quantidadeRemocaoET.getText().toString().length() > 0)
                    vistoria.setQuantidadePessoas(Integer.valueOf(quantidadeRemocaoET.getText().toString()));
                else
                    vistoria.setQuantidadePessoas(0);
                /* PASSO 9 */

                vistoria.setInformacoes(outrasInformacoesET.getText().toString());

                AlertDialog.Builder alert = new AlertDialog.Builder(VistoriaActivity.this);
                alert.setMessage("Você realmente deseja alterar essa vistoria?");
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            bdFacade.getLocalizacaoDao().update(Localizacao.class, localizacao);
                            bdFacade.getFotoVistoriaDao().update(Vistoria.class, vistoria);

                            if(!(mPathPhotos.isEmpty()))
                                FileUtil.saveAllPhotosDB(getApplicationContext(), mPathPhotos, vistoria);
                            if(!(mTempFile == null))
                                if(mTempFile.exists())
                                    FileUtil.tempToVistoria(mTempFile, vistoria.getId());
                            startActivity(intent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });


        listarFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialog overlay = new FragmentDialog();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                Bundle args;
                if(tipoVistoria == 2){
                    args = new Bundle();
                    args.putBoolean("vistoriaNova", false);
                    args.putLong("chaveVistoria", vistoriaCriada.getId());
                    overlay.setArguments(args);
                }else{
                    args = new Bundle();
                    args.putBoolean("vistoriaNova", true);
                    overlay.setArguments(args);
                }
                fragmentTransaction.add(overlay, "fragment");
                fragmentTransaction.commit();
                //overlay.show(fm, "FragmentDialog");

            }
        });

        salvarVistoriaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* PASSO 1 */

                List<RadioButton> radioButtonList = new ArrayList<>();
                Double doubleNull = null;

                localizacao.setMunicipio(municipioET.getText().toString());
                localizacao.setBairro(bairroET.getText().toString());
                localizacao.setAcessoLocal(condicoesAreaET.getText().toString());
                vistoria.setNomeMorador(nomeMoradorET.getText().toString());

                radioButtonList.add(alvenariaRB);
                radioButtonList.add(madeiraRB);
                radioButtonList.add(mistoRB);
                vistoria.setTipoMoradia(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                /* PASSO 2 */
                if(encostaCB.isChecked()){
                    if(!(alturaEncostaET.getText().toString().equals(""))){
                        vistoria.setEncostaNatural(encostaCB.isChecked());
                        vistoria.setAlturaEncosta(Double.valueOf(alturaEncostaET.getText().toString()));
                    }
                }else{
                    vistoria.setEncostaNatural(encostaCB.isChecked());
                    vistoria.setAlturaEncosta(doubleNull);
                }
                if(taludeCB.isChecked()){
                    if(!(alturaTaludeET.getText().toString().equals("") ||
                            distanciaBaseTaludeET.getText().toString().equals("") ||
                            alturaTopoTaludeET.getText().toString().equals(""))){

                        vistoria.setTaludeCorte(taludeCB.isChecked());
                        vistoria.setAlturaTalude(Double.valueOf(alturaTaludeET.getText().toString()));
                        vistoria.setDistanciaBaseTalude(Double.valueOf(distanciaBaseTaludeET.getText().toString()));
                        vistoria.setAlturaTopoTalude(Double.valueOf(alturaTopoTaludeET.getText().toString()));
                    }
                }else{
                    vistoria.setTaludeCorte(taludeCB.isChecked());
                    vistoria.setAlturaTalude(doubleNull);
                    vistoria.setDistanciaBaseTalude(doubleNull);
                    vistoria.setAlturaTopoTalude(doubleNull);
                }
                if(aterroCB.isChecked()){
                    if(!(alturaAterroET.getText().toString().equals("") ||
                            distanciaBaseAterroET.getText().toString().equals("") ||
                            alturaTopoAterroET.getText().toString().equals(""))){

                        vistoria.setAterroLancado(aterroCB.isChecked());
                        vistoria.setAlturaAterro(Double.valueOf(alturaAterroET.getText().toString()));
                        vistoria.setDistanciaBaseAterro(Double.valueOf(distanciaBaseAterroET.getText().toString()));
                        vistoria.setAlturaTopoAterro(Double.valueOf(alturaTopoAterroET.getText().toString()));

                    }
                }else{
                    vistoria.setAterroLancado(aterroCB.isChecked());
                    vistoria.setAlturaAterro(doubleNull);
                    vistoria.setDistanciaBaseAterro(doubleNull);
                    vistoria.setAlturaTopoAterro(doubleNull);
                }
                if(paredeCB.isChecked()){
                    if(!(alturaParedeET.getText().toString().equals(""))){
                        vistoria.setParedeRochosa(paredeCB.isChecked());
                        vistoria.setAlturaParede(Double.valueOf(alturaParedeET.getText().toString()));
                    }

                }else{
                    vistoria.setParedeRochosa(paredeCB.isChecked());
                    vistoria.setAlturaParede(doubleNull);
                }

                vistoria.setBlocosRochasMatacoes(blocosRochasCB.isChecked());
                vistoria.setLixoEntulho(lixoEntulhoCB.isChecked());

                /* PASSO 3 */
                vistoria.setConcentraAguaChuva(concentracaoAguaCB.isChecked());
                vistoria.setConcentraAguaServida(lancamentoAguaCB.isChecked());

                radioButtonList.add(inexistenteRB);
                radioButtonList.add(precarioRB);
                radioButtonList.add(satisfatorioRB);
                vistoria.setDrenageSuperficial(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                radioButtonList.add(fossaRB);
                radioButtonList.add(canalizadoRB);
                radioButtonList.add(superficieRB);
                vistoria.setEsgoto(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();


                radioButtonList.add(prefeituraCRB);
                radioButtonList.add(mangueiraRB);
                vistoria.setUsoAguaMoradia(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();


                radioButtonList.add(vazamentoSRB);
                radioButtonList.add(vazamentoNRB);
                vistoria.setAguaMoradiaVazamento(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                radioButtonList.add(vazamentoEsgotoRB);
                radioButtonList.add(vazamentoAguaRB);
                vistoria.setTipoVazamento(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();


                radioButtonList.add(peRB);
                radioButtonList.add(meioRB);
                radioButtonList.add(topoTaludeRB);
                vistoria.setMinasDagua(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                /* PASSO 4 */

                vistoria.setArvores(presencaArvoresCB.isChecked());
                vistoria.setVegetacaoRasteira(vegetacaoRasteiraCB.isChecked());
                vistoria.setAreaDesmatada(areaDesmatadaCB.isChecked());
                vistoria.setAreaCultivo(areaCultivoET.getText().toString());

                /* PASSO 5 */

                vistoria.setTrincaMoradia(trincasMoradiaCB.isChecked());
                vistoria.setTrincaTerreno(trincasTerrenoCB.isChecked());
                vistoria.setDegrausAbatimento(degrausAbatimentoCB.isChecked());
                vistoria.setArvoresInclinacao(arvoreInclinacaoCB.isChecked());
                vistoria.setPostesInclinacao(postesInclinacaoCB.isChecked());
                vistoria.setMurosInclinacao(murosInclinacaoCB.isChecked());
                vistoria.setMuroParedeEmbarrigado(paredesEmbarrigadosCB.isChecked());
                vistoria.setCicatrizEscorregamento(cicatrizEscorregamentoCB.isChecked());

                /* PASSO 6 */

                vistoria.setEscorregamentoNatural(escorregamentoTaludeNaturalCB.isChecked());
                vistoria.setEscorregamentoCorte(escorregamentoTaludeCorteCB.isChecked());
                vistoria.setEscorregamentoAterro(escorregamentoAterroCB.isChecked());
                vistoria.setQuedaBlocos(quedaBlocosCB.isChecked());
                vistoria.setRolamentoBlocos(rolamentoBlocosCB.isChecked());

                /* PASSO 7 */

                radioButtonList.add(riscoMuitoAltoRB);
                radioButtonList.add(riscoAltoB);
                radioButtonList.add(riscoMedioRB);
                radioButtonList.add(riscoBaixoRB);
                vistoria.setRisco(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.clear();

                /* PASSO 8 */
                if(numeroMoradiasRiscoET.getText().toString().length() > 0)
                    vistoria.setQuantidadeMoradias(Integer.valueOf(numeroMoradiasRiscoET.getText().toString()));
                else
                    vistoria.setQuantidadeMoradias(0);
                if(quantidadeRemocaoET.getText().toString().length() > 0)
                    vistoria.setQuantidadePessoas(Integer.valueOf(quantidadeRemocaoET.getText().toString()));
                else
                    vistoria.setQuantidadePessoas(0);
                /* PASSO 9 */

                vistoria.setInformacoes(outrasInformacoesET.getText().toString());

                AlertDialog.Builder alert = new AlertDialog.Builder(VistoriaActivity.this);
                alert.setMessage("Você realmente deseja salvar a vistoria?");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            if(tipoVistoria == 0){
                                ///
                                vistoriaList.add(vistoria);
                                localizacao.setVistorias((Collection) vistoriaList);
                                vistoria.setLocalizacao(localizacao);
                                usuarioVistoria.setData(dateFormat.format(date));

                                bdFacade.getLocalizacaoDao().save(Localizacao.class, localizacao);
                                bdFacade.getVistoriaDao().save(Vistoria.class, vistoria);
                                usuarioVistoria.setUsuario(usuario);
                                usuarioVistoria.setVistoria(vistoria);
                                bdFacade.getUsuarioVistoriaDao().save(UsuarioVistoria.class, usuarioVistoria);
                            }else if(tipoVistoria == 1){
                                vistoria.setLocalizacao(localizacao);
                                usuarioVistoria.setData(dateFormat.format(date));
                                bdFacade.getVistoriaDao().save(Vistoria.class, vistoria);
                                usuarioVistoria.setUsuario(usuario);
                                usuarioVistoria.setVistoria(vistoria);
                                bdFacade.getUsuarioVistoriaDao().save(UsuarioVistoria.class, usuarioVistoria);
                            }
                            if(!mPathPhotos.isEmpty())
                                FileUtil.saveAllPhotosDB(getApplicationContext(), mPathPhotos, vistoria);
                            if(!(mTempFile == null))
                                if(mTempFile.exists()) {
                                    FileUtil.tempToVistoria(mTempFile, vistoria.getId());
                                }
                            startActivity(intent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }

    public void callCamera(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mHashmapKey = v.getId();
        if(!(mPathPhotos.containsKey(v.getId())))
            mPathPhotos.put(v.getId(), new ArrayList<String>());
        startActivityForResult(intent, PICK_PHOTO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = null;
        File file;
        OutputStream os;

        if(mTempFile == null)
            mTempFile = FileUtil.tempDirectory();

        String photo = mTempFile.getPath() + "/" + "IMG_" +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        if (requestCode == PICK_PHOTO_REQUEST && resultCode == Activity.RESULT_OK && data != null &&
                data.getData() != null) {
            try {
                uri = data.getData();
                file = new File(photo + FileUtil.JPEG);
                Bitmap imagemReal = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap imagemRealAux = FileUtil.getResizeBitmap(imagemReal, 1024, 768);
                os = new BufferedOutputStream(new FileOutputStream(file));
                imagemRealAux.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.close();

                uri = data.getData();
                file = new File(photo + FileUtil.IMAGEM_MEDIA_SIGLA + FileUtil.JPEG);
                Bitmap imagemMedia = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap imagemMediaAux = FileUtil.getResizeBitmap(imagemMedia, 800, 600);
                os = new BufferedOutputStream(new FileOutputStream(file));
                imagemMediaAux.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.close();

                uri = data.getData();
                file = new File(photo + FileUtil.THUMBNAIL_SIGLA  + FileUtil.JPEG);
                Bitmap imagemPequena = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap imagemPequenaAux = FileUtil.getResizeBitmap(imagemPequena, 100, 80);
                os = new BufferedOutputStream(new FileOutputStream(file));
                imagemPequenaAux.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.close();

                mPathPhotos.get(mHashmapKey).add(photo);
                if(listarFotos.getVisibility() == View.GONE)
                    listarFotos.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("-------", e.getMessage());
            }finally {
                getContentResolver().delete(uri, null, null);
            }
        }
    }

    public void onBackPressed(){
        if(FileUtil.tempDirectory().exists()){
            FileUtil.removeAllFile(FileUtil.tempDirectory());
        }
        finish();
    }
}