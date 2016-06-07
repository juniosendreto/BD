package bancodados.test.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import bancodados.test.R;
import bancodados.test.core.service.dao.UsuarioDaoImpl;
import bancodados.test.model.Usuario;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

                    Usuario usuario = usuarioImpl.findByLoginAndPassword(loginE.getText().toString(),
                            passwordE.getText().toString());

                    if(usuario != null){
                        report.setVisibility(View.GONE);
                        loginE.setText("");
                        passwordE.setText("");
                        Usuario.uniqueUsuario = usuario;
                        startActivity(intent);
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
                    chamarActivity(Class.forName("bancodados.test.view.CadastroActivity"));
                    report.setVisibility(View.INVISIBLE);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Log.d("ERRO CHAMADA CADASTRO", e.getMessage());
                }
            }
        });

    }


    public void chamarActivity(Class novaActivity) {
        Intent abrirActivity = new Intent(this, novaActivity);
        startActivity(abrirActivity);

    }



}
