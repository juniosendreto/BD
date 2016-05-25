package bancodados.test.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import bancodados.test.R;
import bancodados.test.core.service.dao.Adapter;
import bancodados.test.core.service.dao.AdapterVistoria;
import bancodados.test.core.service.dao.LocalizacaoDaoImpl;
import bancodados.test.core.service.dao.UsuarioDaoImpl;
import bancodados.test.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.test.core.service.dao.VistoriaDaoImpl;
import bancodados.test.model.Localizacao;
import bancodados.test.model.Usuario;
import bancodados.test.model.UsuarioVistoria;
import bancodados.test.model.Vistoria;

public class VistoriaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoria);


        final Adapter adapter = new Adapter(getApplicationContext());
        final AdapterVistoria adapterVistoria = new AdapterVistoria(getApplicationContext());
        final Intent intent = getIntent();
        final Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
        final UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(getApplicationContext());
        final UsuarioVistoriaDaoImpl usuarioVistoriaDao = new UsuarioVistoriaDaoImpl(getApplicationContext());
        final VistoriaDaoImpl vistoriaDao = new VistoriaDaoImpl(getApplicationContext());
        final LocalizacaoDaoImpl localizacaoDao = new LocalizacaoDaoImpl(getApplicationContext());
        final Localizacao localizacao = (Localizacao) intent.getSerializableExtra("localizacao");
        final List<Vistoria> vistoriaList = new ArrayList<Vistoria>();
        final UsuarioVistoria usuarioVistoria = new UsuarioVistoria(getApplicationContext());
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        final Date date = new Date();


        /* PASSO 1 */

        final TextView passo1TV = (TextView) findViewById(R.id.passo1TV);
        final LinearLayout passo1LL = (LinearLayout) findViewById(R.id.passo1LL);
        final Vistoria vistoria = new Vistoria();
       // Localizacao localizacao =  new Localizacao();


        //final EditText localizacaoET = (EditText) findViewById(R.id.localizacaoET);
        //final TextView localizacaoReportTV = (TextView) findViewById(R.id.localizacaoReportTV);
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
        final RadioButton topoTaludeARB = (RadioButton) findViewById(R.id.topoTaludeARB);


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

       ////////

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

        inexistenteRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        precarioRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        satisfatorioRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        salvarVistoriaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* PASSO 1 */
                List<RadioButton> radioButtonList = new ArrayList<RadioButton>();

                localizacao.setMunicipio(municipioET.getText().toString());
                localizacao.setBairro(bairroET.getText().toString());
                vistoria.setNomeMorador(nomeMoradorET.getText().toString());

                radioButtonList.add(alvenariaRB);
                radioButtonList.add(madeiraRB);
                radioButtonList.add(mistoRB);
                vistoria.setTipoMoradia(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.removeAll(radioButtonList);

                /* PASSO 2 */
                vistoria.setEncostaNatural(encostaCB.isChecked());
                vistoria.setTaludeCorte(taludeCB.isChecked());
                vistoria.setAterroLancado(aterroCB.isChecked());
                vistoria.setParedeRochosa(paredeCB.isChecked());
                vistoria.setAlturaN(Double.valueOf(alturaEncostaET.getText().toString()));
                vistoria.setAlturaC(Double.valueOf(alturaTaludeET.getText().toString()));
                vistoria.setAlturaL(Double.valueOf(alturaAterroET.getText().toString()));
                vistoria.setAlturaR(Double.valueOf(alturaParedeET.getText().toString()));
                vistoria.setDistanciaMoradaC(Double.valueOf(distanciaBaseTaludeET.getText().toString()));
                vistoria.setDistanciaMoradaL(Double.valueOf(distanciaBaseAterroET.getText().toString()));
                vistoria.setTopoC(Double.valueOf(alturaTopoTaludeET.getText().toString()));
                vistoria.setTopoL(Double.valueOf(alturaTopoAterroET.getText().toString()));
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
                radioButtonList.add(vazamentoAguaRB);
                radioButtonList.add(vazamentoEsgotoRB);
                vistoria.setTipoVazamento(adapterVistoria.whichButtonIsChacked(radioButtonList));
                radioButtonList.removeAll(radioButtonList);

                radioButtonList.add(peRB);
                radioButtonList.add(meioRB);
                radioButtonList.add(topoTaludeARB);
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

                vistoria.setQuantidadeMoradias(Integer.valueOf(numeroMoradiasRiscoET.getText().toString()));
                vistoria.setQuantidadePessoas(Integer.valueOf(quantidadeRemocaoET.getText().toString()));

                /* PASSO 9 */

                vistoria.setInformacoes(outrasInformacoesET.getText().toString());


                vistoriaList.add(vistoria);
                localizacao.setVistorias((Collection) vistoriaList);
                vistoria.setLocalizacao(localizacao);
                usuarioVistoria.setData(dateFormat.format(date));
                usuarioVistoria.setUsuario(usuario);
                usuarioVistoria.setVistoria(vistoria);

                usuarioDao.save(Usuario.class, usuario);
                localizacaoDao.save(Localizacao.class, localizacao);
                vistoriaDao.save(Vistoria.class, vistoria);
                usuarioVistoriaDao.save(UsuarioVistoria.class, usuarioVistoria);


            }
        });

    }
}