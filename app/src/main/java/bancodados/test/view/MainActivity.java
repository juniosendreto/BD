package bancodados.test.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bancodados.test.R;
import bancodados.test.core.service.dao.LocalizacaoDaoImpl;
import bancodados.test.core.service.dao.UsuarioDaoImpl;
import bancodados.test.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.test.core.service.dao.VistoriaDaoImpl;
import bancodados.test.model.Localizacao;
import bancodados.test.model.Usuario;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button novaVistoria = (Button) findViewById(R.id.novaVistoriaB);
        final EditText latitudeET = (EditText) findViewById(R.id.latitudeET);
        final EditText longitudeET = (EditText)findViewById(R.id.longitudeET);
        final Localizacao localizacao =  new Localizacao();
        Intent intent = getIntent();

        //localizacao.setLatitude(Double.valueOf(latitudeET.getText().toString()));
        //localizacao.setLongitude(Double.valueOf(longitudeET.getText().toString()));
        try {
            Usuario u = (Usuario) intent.getSerializableExtra("usuario");
            Log.d("------", u.getLogin());
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--------- ", e.getMessage());
        }
        novaVistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chamarActivity(Class.forName("bancodados.test.view.VistoriaActivity"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void chamarActivity(Class novaActivity) {
        Intent abrirActivity = new Intent(this, novaActivity);
        ///abrirActivity.putExtra("Localização", (Parcelable) localizacao);
        startActivity(abrirActivity);

    }
}
