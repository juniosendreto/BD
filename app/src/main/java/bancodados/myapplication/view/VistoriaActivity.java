package bancodados.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import bancodados.myapplication.R;
import bancodados.myapplication.core.service.dao.Adapter;

public class VistoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoria);

        final Adapter adapter = new Adapter();

        /* PASSO 1 */

        final TextView passo1TV = (TextView) findViewById(R.id.passo1TV);
        final LinearLayout passo1LL = (LinearLayout) findViewById(R.id.passo1LL);

        final EditText localizacaoET = (EditText) findViewById(R.id.localizacaoET);
        final TextView localizacaoReportTV = (TextView) findViewById(R.id.localizacaoReportTV);
        final EditText nomeMoradorET = (EditText) findViewById(R.id.nomeMoradorET);
        final EditText condicoesAreaET = (EditText) findViewById(R.id.condicoesAreaET);
        final TextView condicoesAreaReportTV = (TextView) findViewById(R.id.condicoesAreaReportTV);
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


        final CheckBox pareceCB = (CheckBox) findViewById(R.id.paredeCB);
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

        /* PASSO 1 */

        passo1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo1LL);
            }
        });

        localizacaoET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.nullValue(localizacaoET, localizacaoReportTV);
                }
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

        pareceCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityCheckBox(pareceCB, paredeLL);
            }
        });

        passo3TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibility(passo3LL);

            }
        });

        vazamentoSNRG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.visibilityRadioButton(vazamentoSRB, vazamentoSNRG);
            }
        });

        inexistenteRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter.checkedOnOff(inexistenteRB);
            }
        });

        precarioRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter.checkedOnOff(precarioRB);
            }
        });

        satisfatorioRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter.checkedOnOff(satisfatorioRB);
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

    }
}
