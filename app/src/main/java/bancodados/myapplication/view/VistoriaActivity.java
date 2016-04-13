package bancodados.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import bancodados.myapplication.R;
import bancodados.myapplication.core.service.dao.Validator;

public class VistoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoria);

        final  Validator validator = new Validator();
        final TextView passo1TV = (TextView) findViewById(R.id.passo1TV);
        final LinearLayout passo1LL = (LinearLayout) findViewById(R.id.passo1LL);

        passo1LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.visibility(passo1LL);

            }
        });


    }
}
