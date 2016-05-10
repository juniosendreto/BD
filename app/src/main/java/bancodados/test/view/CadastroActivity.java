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

        final Usuario usuario;
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
                    adapter.validarCfp(cpfET, cpfReportTV);
                }
            }
        });

        emailET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    adapter.validarEmail(emailET, emailReportTV);
                }
            }
        });

        nivelET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adapter.validarNivel(nivelET, nivelReportTV);
                }
            }
        });

        salvarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*usuario.setNome();
                usuario.setLogin();
                usuario.setPassword();
                usuario.setCpf();
                usuario.setEmail();
                usuario.setEndereco();
                usuario.set();
                usuario.setNome();
                usuario.setNome();
                adapter.validarCamposUsuario()*/
            }
        });

    }
}
