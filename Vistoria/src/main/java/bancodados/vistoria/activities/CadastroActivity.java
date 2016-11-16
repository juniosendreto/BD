package bancodados.vistoria.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.core.service.dao.Adapter;
import bancodados.vistoria.core.service.dao.AdapterVistoria;
import bancodados.vistoria.model.Usuario;

public class CadastroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final AlertDialog alert = new AlertDialog.Builder(this).create();
        final Adapter adapter= new Adapter(CadastroActivity.this);
        final AdapterVistoria adapterVistoria = new AdapterVistoria(CadastroActivity.this);


        final Button salvarB = (Button) findViewById(R.id.salvarButton);
        final EditText nomeET = (EditText) findViewById(R.id.nomeET);
        final EditText cpfET = (EditText) findViewById(R.id.cpfET);
        final EditText loginET = (EditText) findViewById(R.id.loginET);
        final EditText passwordET = (EditText) findViewById(R.id.passwordET);
        final EditText password2ET = (EditText) findViewById(R.id.password2ET);
        final EditText emailET = (EditText) findViewById(R.id.emailET);
        final EditText municipioET = (EditText) findViewById(R.id.municipioET);
        final EditText enderecoET = (EditText) findViewById(R.id.enderecoET);
        final EditText telefoneET = (EditText) findViewById(R.id.telefoneET);
        final EditText celularET = (EditText) findViewById(R.id.celularET);
        final RadioButton tecnicoDCRB = (RadioButton) findViewById(R.id.tecnicoDCRB);
        final RadioButton geologoRB = (RadioButton) findViewById(R.id.geologoRB);

        final TextView nomeReportTV = (TextView) findViewById(R.id.nomeReportTV);
        final TextView loginReportTV = (TextView) findViewById(R.id.loginReportView);
        final TextView passwordReportTV = (TextView) findViewById(R.id.passwordReportTV);
        final TextView passwordReport2TV = (TextView) findViewById(R.id.password2ReportTV);
        final TextView cpfReportTV = (TextView) findViewById(R.id.cpfReportTV);
        cpfReportTV.setText("CPF Inválido");
        final TextView emailReportTV = (TextView) findViewById(R.id.emailReportTV);
        emailReportTV.setText("E-mail inválido!");
        final TextView nivelReportTV = (TextView) findViewById(R.id.nivelReportTV);

        loginET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    adapter.validarLoginECampo(loginET, loginReportTV);
                }
            }
        });

        passwordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    adapter.validarSenha(passwordET, passwordReportTV);
                }
            }
        });

        password2ET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    adapter.validarConfirmacaoSenha(passwordET, password2ET, passwordReport2TV);
                }
            }
        });

        cpfET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    adapter.validarVisibilidade(adapter.validarCfp(adapter.conversaoEditTextParaString(cpfET)), cpfReportTV);
                }
            }
        });

        emailET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    adapter.validarVisibilidade(adapter.validarEmail(adapter.conversaoEditTextParaString(emailET)), emailReportTV);
                }
            }
        });


        salvarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<RadioButton> radioButtons =  new ArrayList<RadioButton>();
                Usuario usuario = new Usuario();
                usuario.setNome(nomeET.getText().toString());
                usuario.setLogin(loginET.getText().toString().toLowerCase());
                usuario.setPassword(passwordET.getText().toString());
                usuario.setCpf(cpfET.getText().toString());
                usuario.setEmail(emailET.getText().toString());
                usuario.setEndereco(enderecoET.getText().toString());
                usuario.setMunicipio(municipioET.getText().toString());
                usuario.setTelefone(telefoneET.getText().toString());
                usuario.setCelular(celularET.getText().toString());

                radioButtons.add(tecnicoDCRB);
                radioButtons.add(geologoRB);
                if((adapterVistoria.whichButtonIsChacked(radioButtons) == 1 ||
                        adapterVistoria.whichButtonIsChacked(radioButtons) == 2) &&
                        passwordET.getText().toString().equals(password2ET.getText().toString())){

                    usuario.setNivel(adapterVistoria.whichButtonIsChacked(radioButtons));
                    try {
                        adapter.salvarUsuario(usuario, adapter.validarCamposUsuario(usuario));
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.d("------", e.getMessage());
                    }
                }else{
                    alert.setTitle("Alerta");
                    alert.setMessage("Os campos: NOME, LOGIN, PASSWORD, CPF E EMAIL, são obrigatórios verifique se estão corretos!");
                    alert.setButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alert.show();
                }
            }
        });
    }
}
