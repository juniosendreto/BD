package bancodados.test.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import bancodados.test.R;
import bancodados.test.core.service.dao.UsuarioDaoImpl;

public class LoginActivity extends AppCompatActivity {

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


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (usuarioImpl.findByLoginAndPassword(loginE.getText().toString(),
                            passwordE.getText().toString()) != null) {
                        report.setVisibility(View.INVISIBLE);
                        loginE.setText("");
                        passwordE.setText("");
                        chamarActivity(Class.forName("br.com.inpe.Activitys.TelaInicialActivity"));

                    } else {
                        report.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    Log.d("ERRO CHAMADA TELA", e.getMessage());
                }
            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chamarActivity(Class.forName("br.com.inpe.Activitys.CadastroActivity"));
                    report.setVisibility(View.INVISIBLE);

                } catch (ClassNotFoundException e) {
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
