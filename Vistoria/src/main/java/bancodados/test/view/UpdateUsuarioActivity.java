package bancodados.test.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodados.test.R;
import bancodados.test.core.service.dao.Adapter;
import bancodados.test.core.service.dao.AdapterVistoria;
import bancodados.test.core.service.dao.UsuarioDaoImpl;
import bancodados.test.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.test.model.Usuario;

public class UpdateUsuarioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_usuario);

        final Adapter adapter= new Adapter(this);
        final AdapterVistoria adapterVistoria = new AdapterVistoria(this);
        final Intent intentMainActivity = new Intent(this, MainActivity.class);
        final Usuario usuario  =  Usuario.getInstance();
        List<RadioButton> radioButtonList = new ArrayList<>();
        Integer itemChecado;


        final Button updateButton = (Button) findViewById(R.id.updateButton);
        final EditText nomeUET = (EditText) findViewById(R.id.nomeUET);
        final EditText cpfET = (EditText) findViewById(R.id.cpfUET);
        final EditText loginUET = (EditText) findViewById(R.id.loginUET);
        final EditText passwordUET = (EditText) findViewById(R.id.passwordUET);
        final EditText password2UET = (EditText) findViewById(R.id.password2UET);
        final EditText emailUET = (EditText) findViewById(R.id.emailUET);
        final EditText municipioUET = (EditText) findViewById(R.id.municipioUET);
        final EditText enderecoUET = (EditText) findViewById(R.id.enderecoUET);
        final EditText telefoneUET = (EditText) findViewById(R.id.telefoneUET);
        final EditText celularUET = (EditText) findViewById(R.id.celularUET);
        final RadioButton tecnicoDCURB = (RadioButton) findViewById(R.id.tecnicoDCURB);
        final RadioButton geologoURB = (RadioButton) findViewById(R.id.geologoURB);

        final TextView nomeReportUTV = (TextView) findViewById(R.id.nomeReportUTV);
        final TextView loginReportUTV = (TextView) findViewById(R.id.loginReportUTV);
        final TextView passwordReportUTV = (TextView) findViewById(R.id.passwordReportUTV);
        final TextView passwordReport2UTV = (TextView) findViewById(R.id.password2ReportUTV);
        final TextView cpfReportUTV = (TextView) findViewById(R.id.cpfReportUTV);
        cpfReportUTV.setText("CPF Inválido");
        final TextView emailReportTVU = (TextView) findViewById(R.id.emailReportUTV);
        emailReportTVU.setText("E-mail inválido!");
        //final TextView nivelReportUTV = (TextView) findViewById(R.id.nivelReportUTV);

        nomeUET.setText(usuario.getNome());
        cpfET.setText(usuario.getCpf());
        cpfET.setEnabled(false);
        loginUET.setText(usuario.getLogin());
        loginUET.setEnabled(false);
        passwordUET.setText(usuario.getPassword());
        password2UET.setText(usuario.getPassword());
        emailUET.setText(usuario.getEmail());
        municipioUET.setText(usuario.getMunicipio());
        enderecoUET.setText(usuario.getEndereco());
        telefoneUET.setText(usuario.getTelefone());
        celularUET.setText(usuario.getCelular());

        radioButtonList.add(tecnicoDCURB);
        radioButtonList.add(geologoURB);
        itemChecado = adapterVistoria.whichButtonIsChacked(radioButtonList);
        if(itemChecado == 1)
            tecnicoDCURB.setChecked(true);
        else
            geologoURB.setChecked(true);

        tecnicoDCURB.setEnabled(false);
        geologoURB.setEnabled(false);
        radioButtonList.removeAll(radioButtonList);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setNome(nomeUET.getText().toString());
                usuario.setEmail(emailUET.getText().toString());
                usuario.setMunicipio(municipioUET.getText().toString());
                usuario.setEndereco(enderecoUET.getText().toString());
                usuario.setTelefone(telefoneUET.getText().toString());
                usuario.setCelular(celularUET.getText().toString());
                AlertDialog.Builder alerta = new AlertDialog.Builder(UpdateUsuarioActivity.this);
                alerta.setTitle("Alerta");

                try {
                    if(adapter.validarCamposUsuarioUpdate(usuario) == true &&
                            (adapter.validarPassword(passwordUET.getText().toString(), password2UET.getText().toString()) == true)){
                        alerta.setMessage("Tem certeza que deseja alterar suas informações?");
                        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getApplicationContext());
                                usuarioDao.update(Usuario.class, usuario);
                                startActivity(intentMainActivity);
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                    }else{

                        alerta.setMessage("NOME, PASSWORD E EMAIL são obrigatórios verifique se as alterações estão corretas!");
                        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    Log.d("-----------", e.getMessage());
                }
            }
        });


    }
}
