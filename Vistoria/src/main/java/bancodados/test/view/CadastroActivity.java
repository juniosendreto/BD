package bancodados.test.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bancodados.test.R;
import bancodados.test.core.service.dao.Adapter;
import bancodados.test.model.Usuario;

public class CadastroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final AlertDialog alert = new AlertDialog.Builder(this).create();
        final Adapter adapter= new Adapter(CadastroActivity.this);

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

        /*nomeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(nomeET.getText().length() <= 0)
                    nomeET.setError("O Campo nome não pode ser nulo");

            }
        });
        loginET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
*/
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
                Usuario usuario = new Usuario();
                usuario.setNome(nomeET.getText().toString());
                usuario.setLogin(loginET.getText().toString());
                usuario.setPassword(passwordET.getText().toString());
                usuario.setCpf(cpfET.getText().toString());
                usuario.setEmail(emailET.getText().toString());
                usuario.setEndereco(enderecoET.getText().toString());
                usuario.setMunicipio(municipioET.getText().toString());
                usuario.setTelefone(telefoneET.getText().toString());
                usuario.setCelular(celularET.getText().toString());
                if(adapter.isNumber(nivelET) == true){
                    usuario.setNivel(Integer.parseInt(nivelET.getText().toString()));
                    try {
                        if(adapter.salvarUsuario(usuario, adapter.validarCamposUsuario(usuario)) == true)
                            chamarActivity(Class.forName("bancodados.test.view.LoginActivity"));
                    }catch (Exception e){
                        Log.d("------", e.getMessage());
                    }
                }else{
                    alert.setTitle("Alerta");
                    alert.setMessage("Os campos: NOME, LOGIN, PASSWORD, CPF E EMAIL, são obrigatórios verifique se estão corretos!");
                    alert.setButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                }
            }
        });
    }

    public void chamarActivity(Class novaActivity) {
        Intent abrirActivity = new Intent(this, novaActivity);
        startActivity(abrirActivity);

    }
}