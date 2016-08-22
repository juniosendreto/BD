package bancodados.vistoria.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.core.service.dao.Adapter;
import bancodados.vistoria.core.service.dao.AdapterCamera;
import bancodados.vistoria.core.service.dao.AdapterVistoria;
import bancodados.vistoria.core.service.dao.LocalizacaoDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.VistoriaDaoImpl;
import bancodados.vistoria.model.Localizacao;
import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.model.UsuarioVistoria;
import bancodados.vistoria.model.Vistoria;

public class VistoriaActivity extends Activity {

    public static final int PICK_PHOTO_REQUEST = 100;
    Localizacao localizacao;
    Usuario usuario;
    private ImageView imageView;
    private List<Bitmap> vistoriaImagens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoria);

        //imageView = (ImageView) findViewById(R.id.imagemView);
        vistoriaImagens = new ArrayList<>();
        final Adapter adapter = new Adapter(getApplicationContext());
        final AdapterVistoria adapterVistoria = new AdapterVistoria(getApplicationContext());
        final Intent intent = new Intent(this, MainActivity.class);
        final UsuarioVistoriaDaoImpl usuarioVistoriaDao = new UsuarioVistoriaDaoImpl(getApplicationContext());
        final VistoriaDaoImpl vistoriaDao = new VistoriaDaoImpl(getApplicationContext());
        final LocalizacaoDaoImpl localizacaoDao = new LocalizacaoDaoImpl(getApplicationContext());
        localizacao = (Localizacao) getIntent().getSerializableExtra("localizacao");
        final Vistoria vistoriaCriada = (Vistoria) getIntent().getSerializableExtra("vistoria");
        usuario = Usuario.getInstance();

        final List<Vistoria> vistoriaList = new ArrayList<Vistoria>();
        final UsuarioVistoria usuarioVistoria = new UsuarioVistoria(getApplicationContext());
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        final Date date = new Date();


        /* PASSO 1 */

        final TextView passo1TV = (TextView) findViewById(R.id.passo1TV);
        final LinearLayout passo1LL = (LinearLayout) findViewById(R.id.passo1LL);
        final Vistoria vistoria = new Vistoria();

        final EditText bairroET = (EditText) findViewById(R.id.bairroET);
        final EditText municipioET = (EditText) findViewById(R.id.municipioET);
        final EditText nomeMoradorET = (EditText) findViewById(R.id.nomeMoradorET);
        final EditText condicoesAreaET = (EditText) findViewById(R.id.condicoesAreaET);
        final TextView condicoesAreaReportTV = (TextView) findViewById(R.id.condicoesAreaReportTV);
        final RadioButton alvenariaRB = (RadioButton) findViewById(R.id.alvenariaRB);
        final RadioButton madeiraRB = (RadioButton) findViewById(R.id.madeiraRB);
        final RadioButton mistoRB = (RadioButton) findViewById(R.id.mistoRB);
        final TextView tiposMoradiasReportTV = (TextView) findViewById(R.id.tiposMoradiasReportTV);


        /* PASSO 2 */


        final TextView passo2TV = (TextView) findViewById(R.id.passo2TV);
        final LinearLayout passo2LL = (LinearLayout) findViewById(R.id.passo2LL);

        final CheckBox encostaCB = (CheckBox) findViewById(R.id.encostaCB);
        final LinearLayout encostaLL = (LinearLayout) findViewById(R.id.encostaLL);
        final EditText alturaEncostaET = (EditText) findViewById(R.id.alturaEncostaET);
        final TextView alturaEncostaReportTV = (TextView) findViewById(R.id.alturaEncostaReportTV);

        final CheckBox taludeCB = (CheckBox) findViewById(R.id.taludeCB);
        final LinearLayout taludeLL = (LinearLayout) findViewById(R.id.taludeLL);
        final EditText alturaTaludeET = (EditText) findViewById(R.id.alturaTaludeET);
        final EditText distanciaBaseTaludeET = (EditText) findViewById(R.id.distanciaBaseTaludeET);
        final EditText alturaTopoTaludeET = (EditText) findViewById(R.id.alturaTopoTaludeET);
        final TextView alturaTaludeReportTV = (TextView) findViewById(R.id.alturaTaludeReportTV);
        final TextView distanciaBaseTaludeReportTV = (TextView) findViewById(R.id.distanciaBaseTaludeReportTV);
        final TextView alturaTopoEncostaReportTV = (TextView) findViewById(R.id.alturaTopoEncostaReportTV);

        final CheckBox aterroCB = (CheckBox) findViewById(R.id.aterroCB);
        final LinearLayout aterroLL = (LinearLayout) findViewById(R.id.aterroLL);
        final EditText alturaAterroET = (EditText) findViewById(R.id.alturaAterroET);
        final EditText distanciaBaseAterroET = (EditText) findViewById(R.id.distanciaBaseAterroET);
        final EditText alturaTopoAterroET = (EditText) findViewById(R.id.alturaTopoAterroET);
        final TextView alturaAterroReportTV = (TextView) findViewById(R.id.alturaAterroReportTV);
        final TextView distanciaBaseAterroReportTV = (TextView) findViewById(R.id.distanciaBaseAterroReportTV);
        final TextView alturaTopoAterroReportTV = (TextView) findViewById(R.id.alturaTopoAterroReportTV);

        final CheckBox paredeCB = (CheckBox) findViewById(R.id.paredeCB);
        final LinearLayout paredeLL = (LinearLayout) findViewById(R.id.paredeLL);
        final EditText alturaParedeET = (EditText) findViewById(R.id.alturaParedeET);
        final TextView alturaParedeReportTV = (TextView) findViewById(R.id.alturaParedeReportTV);

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
        final TextView drenagemsuperficialReportTV = (TextView) findViewById(R.id.drenagemSuperficialReportTV);


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

        /* PASSO 1 */

        passo1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo1LL);
            }
        });

        condicoesAreaET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValue(condicoesAreaET, condicoesAreaReportTV);
                }
            }
        });

        alvenariaRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityRadioButton(alvenariaRB, tiposMoradiasReportTV);
            }
        });
        madeiraRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityRadioButton(madeiraRB, tiposMoradiasReportTV);
            }
        });
        mistoRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityRadioButton(mistoRB, tiposMoradiasReportTV);
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
        alturaEncostaET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValueByCheckBox(encostaCB, alturaEncostaET, alturaEncostaReportTV);
                }
            }
        });

        taludeCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityCheckBox(taludeCB, taludeLL);
            }
        });

        alturaTaludeET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValueByCheckBox(taludeCB, alturaTaludeET, alturaTaludeReportTV);
                }
            }
        });

        distanciaBaseTaludeET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValueByCheckBox(taludeCB, distanciaBaseTaludeET, distanciaBaseTaludeReportTV);
                }
            }
        });

        alturaTopoTaludeET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValueByCheckBox(taludeCB, alturaTopoTaludeET, alturaTopoEncostaReportTV);
                }
            }
        });

        aterroCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityCheckBox(aterroCB, aterroLL);
            }
        });

        alturaAterroET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValueByCheckBox(aterroCB, alturaAterroET, alturaAterroReportTV);
                }
            }
        });

        distanciaBaseAterroET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValueByCheckBox(aterroCB, distanciaBaseAterroET, distanciaBaseAterroReportTV);
                }
            }
        });

        alturaTopoAterroET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValueByCheckBox(aterroCB, alturaTopoAterroET, alturaTopoAterroReportTV);
                }
            }
        });

        paredeCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityCheckBox(paredeCB, paredeLL);
            }
        });

        alturaParedeET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValueByCheckBox(paredeCB, alturaParedeET, alturaParedeReportTV);
                }
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

        if(vistoriaCriada != null){


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
            radioButtonList.removeAll(radioButtonList);

            /* PASSO 2 */

            validarCheckBox = adapterVistoria.recuperaCheck(encostaCB, vistoriaCriada.getEncostaNatural());
            if(validarCheckBox == true){
                alturaEncostaET.setText(vistoriaCriada.getAlturaEncosta().toString());
                adapter.visibilityCheckBox(encostaCB, encostaLL);
            }

            validarCheckBox = adapterVistoria.recuperaCheck(taludeCB, vistoriaCriada.getTaludeCorte());
            if(validarCheckBox == true){
                alturaTaludeET.setText(vistoriaCriada.getAlturaTalude().toString());
                distanciaBaseTaludeET.setText(vistoriaCriada.getDistanciaBaseTalude().toString());
                alturaTopoTaludeET.setText(vistoriaCriada.getAlturaTopoTalude().toString());
                adapter.visibilityCheckBox(taludeCB, taludeLL);

            }

            validarCheckBox = adapterVistoria.recuperaCheck(aterroCB, vistoriaCriada.getAterroLancado());
            if(validarCheckBox == true){
                alturaAterroET.setText(vistoriaCriada.getAlturaAterro().toString());
                distanciaBaseAterroET.setText(vistoriaCriada.getDistanciaBaseAterro().toString());
                alturaTopoAterroET.setText(vistoriaCriada.getAlturaTopoAterro().toString());
                adapter.visibilityCheckBox(aterroCB, aterroLL);

            }

            validarCheckBox = adapterVistoria.recuperaCheck(paredeCB, vistoriaCriada.getParedeRochosa());
            if(validarCheckBox == true){
                alturaParedeET.setText(vistoriaCriada.getAlturaParede().toString());
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
            radioButtonList.removeAll(radioButtonList);

            radioButtonList.add(fossaRB);
            radioButtonList.add(canalizadoRB);
            radioButtonList.add(superficieRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getEsgoto());
            radioButtonList.removeAll(radioButtonList);

            radioButtonList.add(prefeituraCRB);
            radioButtonList.add(mangueiraRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getUsoAguaMoradia());
            radioButtonList.removeAll(radioButtonList);

            radioButtonList.add(vazamentoSRB);
            radioButtonList.add(vazamentoNRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getAguaMoradiaVazamento());
            radioButtonList.removeAll(radioButtonList);
            radioButtonList.add(vazamentoEsgotoRB);
            radioButtonList.add(vazamentoAguaRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getTipoVazamento());
            adapter.visibility(vazamentoSNRG);
            radioButtonList.removeAll(radioButtonList);

            radioButtonList.add(peRB);
            radioButtonList.add(meioRB);
            radioButtonList.add(topoTaludeRB);
            adapterVistoria.recuperarCheckRadionButton(radioButtonList, vistoriaCriada.getMinasDagua());
            radioButtonList.removeAll(radioButtonList);

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
            radioButtonList.removeAll(radioButtonList);

            /* PASSO 8 */

            numeroMoradiasRiscoET.setText(String.valueOf(vistoriaCriada.getQuantidadeMoradias()));
            quantidadeRemocaoET.setText(String.valueOf(vistoriaCriada.getQuantidadePessoas()));

            /* PASSO 9 */

            outrasInformacoesET.setText(vistoriaCriada.getInformacoes());

        }

        salvarVistoriaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* PASSO 1 */

                List<RadioButton> radioButtonList = new ArrayList<RadioButton>();
                Double doubleNull = null;

                localizacao.setMunicipio(municipioET.getText().toString());
                localizacao.setBairro(bairroET.getText().toString());
                localizacao.setAcessoLocal(condicoesAreaET.getText().toString());
                vistoria.setNomeMorador(nomeMoradorET.getText().toString());

                radioButtonList.add(alvenariaRB);
                radioButtonList.add(madeiraRB);
                radioButtonList.add(mistoRB);
                vistoria.setTipoMoradia(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.removeAll(radioButtonList);

                /* PASSO 2 */
                if(encostaCB.isChecked() == true){
                    if(!(alturaEncostaET.getText().toString().equals(""))){
                        vistoria.setEncostaNatural(encostaCB.isChecked());
                        vistoria.setAlturaEncosta(Double.valueOf(alturaEncostaET.getText().toString()));
                    }
                }else{
                    vistoria.setEncostaNatural(encostaCB.isChecked());
                    vistoria.setAlturaEncosta(doubleNull);
                }
                if(taludeCB.isChecked() == true){
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
                if(aterroCB.isChecked() == true){
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
                if(paredeCB.isChecked() == true){
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
                radioButtonList.removeAll(radioButtonList);

                radioButtonList.add(fossaRB);
                radioButtonList.add(canalizadoRB);
                radioButtonList.add(superficieRB);
                vistoria.setEsgoto(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.removeAll(radioButtonList);

                radioButtonList.add(prefeituraCRB);
                radioButtonList.add(mangueiraRB);
                vistoria.setUsoAguaMoradia(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.removeAll(radioButtonList);

                radioButtonList.add(vazamentoSRB);
                radioButtonList.add(vazamentoNRB);
                vistoria.setAguaMoradiaVazamento(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.removeAll(radioButtonList);
                radioButtonList.add(vazamentoEsgotoRB);
                radioButtonList.add(vazamentoAguaRB);
                vistoria.setTipoVazamento(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.removeAll(radioButtonList);

                radioButtonList.add(peRB);
                radioButtonList.add(meioRB);
                radioButtonList.add(topoTaludeRB);
                vistoria.setMinasDagua(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.removeAll(radioButtonList);

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
                radioButtonList.removeAll(radioButtonList);

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


                vistoriaList.add(vistoria);
                localizacao.setVistorias((Collection) vistoriaList);
                vistoria.setLocalizacao(localizacao);
                usuarioVistoria.setData(dateFormat.format(date));


                localizacaoDao.save(Localizacao.class, localizacao);
                vistoriaDao.save(Vistoria.class, vistoria);
                usuarioVistoria.setUsuario(usuario);
                usuarioVistoria.setVistoria(vistoria);
                usuarioVistoriaDao.save(UsuarioVistoria.class, usuarioVistoria);

                AlertDialog.Builder alert = new AlertDialog.Builder(VistoriaActivity.this);
                alert.setMessage("Você realmente deseja salvar a vistoria?");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            startActivity(intent);

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
        AdapterCamera adapterCamera = new AdapterCamera(getApplicationContext());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_PHOTO_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_PHOTO_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            try {
                Uri uri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                vistoriaImagens.add(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("-------", e.getMessage());
            }
        }
       /* Log.d("-------", "OI");
        if(data != null){
            Log.d("-------", "OI2");

            Bundle bundle = data.getExtras();
            if(bundle != null){
                Log.d("-------", "OI3");

                Bitmap bitmap = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bitmap);


            }
        }*/

    }


    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
    }

}
