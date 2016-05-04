package bancodados.test.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import bancodados.test.R;
import bancodados.test.core.service.dao.Adapter;
import bancodados.test.core.service.dao.UsuarioDaoImpl;
import bancodados.test.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(this);
        final Adapter adapter= new Adapter(getApplicationContext());
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
        final EditText nivelET = (EditText) findViewById(R.id.nivelET);

        final TextView nomeReportTV = (TextView) findViewById(R.id.nomeReportTV);
        final TextView loginReportTV = (TextView) findViewById(R.id.loginReportView);
        final TextView passwordReportTV = (TextView) findViewById(R.id.passwordReportTV);
        final TextView passwordReport2TV = (TextView) findViewById(R.id.password2ReportTV);
        final TextView cpfReportTV = (TextView) findViewById(R.id.cpfReportTV);
        final TextView emailReportTV = (TextView) findViewById(R.id.emailReportTV);
        final TextView nivelReportTV = (TextView) findViewById(R.id.nivelReportTV);

        nomeET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    adapter.validarCampo(nomeET, nomeReportTV);
                }
            }
        });


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
                    if (String.valueOf(passwordE.getText()).equals("")) {
                        passwordReport.setText("*Campo Obrigatório");
                        passwordReport.setVisibility(View.VISIBLE);
                    }else if(passwordE.getText().toString().length() < 5){
                        passwordReport.setText("*Sua senha deve conter 5 ou mais caracteres");
                        passwordReport.setVisibility(View.VISIBLE);
                    }else{
                        passwordReport.setVisibility(View.GONE);
                    }
                }
            }
        });
/*
        passwordE2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!(passwordE.getText().toString().equals(passwordE2.getText().toString()))){
                        passwordReport2.setText("*Senhas não coincidem");
                        passwordReport2.setVisibility(View.VISIBLE);
                    }else{
                        passwordReport2.setVisibility(View.GONE);
                    }
                }
            }
        });

        cpfE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (String.valueOf(cpfE.getText()).equals("")) {
                        cpfReport.setText("*Campo obrigatório");
                        cpfReport.setVisibility(View.VISIBLE);

                    }else if(validador.validarCfp(cpfE.getText().toString()) == false){
                        cpfReport.setText("*CPF Inválido");
                        cpfReport.setVisibility(View.VISIBLE);

                    }else{
                        cpfReport.setVisibility(View.GONE);
                    }
                }
            }
        });

        emailE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (emailE.getText().toString().equals("")) {
                        emailReport.setText("*Campo obrigatório");
                        emailReport.setVisibility(View.VISIBLE);

                    }else if(validador.validarEmail(emailE.getText().toString())== false){
                        emailReport.setText("*Email Inválido");
                        emailReport.setVisibility(View.VISIBLE);

                    }else{
                        emailReport.setVisibility(View.GONE);
                    }
                }
            }
        });*/
    }
}
