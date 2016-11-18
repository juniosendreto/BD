package bancodados.vistoria.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

import bancodados.vistoria.R;
import bancodados.vistoria.Util.FileUtil;
import bancodados.vistoria.core.service.dao.AdapterCamera;
import bancodados.vistoria.core.service.dao.UsuarioDaoImpl;
import bancodados.vistoria.model.Usuario;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FileUtil.mainDirectory();
        FileUtil.vistoriasDirectory();
        FileUtil.bDDirectory();
        if(new File(FileUtil.PATH + "vistoria/vistorias/temp").exists())
            FileUtil.removeAllFile(new File(FileUtil.PATH + "vistoria/vistorias/temp"));

        final UsuarioDaoImpl usuarioImpl = new UsuarioDaoImpl(this);
        ImageButton imageButton =  (ImageButton) findViewById(R.id.imageButton);
        Button button = (Button) findViewById(R.id.logarButton);
        final EditText loginE = (EditText) findViewById(R.id.loginEditText);
        final EditText passwordE = (EditText) findViewById(R.id.passwordEditText);
        final TextView report =  (TextView) findViewById(R.id.report);
        final Intent intent = new Intent(this, MainActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Usuario usuario = usuarioImpl.findByLoginAndPassword(loginE.getText().toString().toLowerCase(),
                            passwordE.getText().toString());

                    if(usuario != null){
                        report.setVisibility(View.GONE);
                        loginE.setText("");
                        passwordE.setText("");
                        Usuario.uniqueUsuario = usuario;
                        startActivity(intent);
                        finish();
                    }else{
                        report.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("ERRO CHAMADA TELA", e.getMessage());
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intentCadastro = new Intent(getApplicationContext(), CadastroActivity.class);
                    startActivity(intentCadastro);
                    report.setVisibility(View.INVISIBLE);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("ERRO CHAMADA CADASTRO", e.getMessage());
                }
            }
        });

    }

    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Usuario.uniqueUsuario = null;
        startActivity(intent);
        finish();
    }
}
