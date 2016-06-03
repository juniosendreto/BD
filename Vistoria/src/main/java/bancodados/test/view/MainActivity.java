package bancodados.test.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bancodados.test.R;
import bancodados.test.model.Localizacao;


public class MainActivity extends Activity {

    Localizacao localizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button novaVistoria = (Button) findViewById(R.id.novaVistoriaB);
        final Button listarVistorias = (Button) findViewById(R.id.listarVistoriasB);
        final EditText latitudeET = (EditText) findViewById(R.id.latitudeET);
        final EditText longitudeET = (EditText)findViewById(R.id.longitudeET);
        localizacao =  new Localizacao();
        final Intent intent = new Intent(this, VistoriaActivity.class);
        final Intent intentAuxiliar = getIntent();
        intent.putExtra("usuario", intentAuxiliar.getSerializableExtra("usuario"));
        intent.putExtra("localizacao", localizacao);

        novaVistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    localizacao.setLatitude(Double.valueOf(latitudeET.getText().toString()));
                    localizacao.setLongitude(Double.valueOf(longitudeET.getText().toString()));

                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        listarVistorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chamarActivity(Class.forName("bancodados.test.view.ListViewVistoriaActivity"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void chamarActivity(Class novaActivity) {
        Intent abrirActivity = new Intent(this, novaActivity);
        startActivity(abrirActivity);

    }

}
