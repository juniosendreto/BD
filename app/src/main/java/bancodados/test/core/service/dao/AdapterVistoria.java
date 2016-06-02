package bancodados.test.core.service.dao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import bancodados.test.model.Localizacao;
import bancodados.test.model.Usuario;
import bancodados.test.model.UsuarioVistoria;
import bancodados.test.model.ViewHolder;
import bancodados.test.model.Vistoria;
import bancodados.test.view.VistoriaActivity;

/**
 * Created by junio on 13/05/16.
 */
public class AdapterVistoria {

    private Context context;


    public AdapterVistoria(Context context) {
        this.context = context;
    }

    public String whichButtonIsChacked(List<RadioButton> radioButtons){

        for(RadioButton r: radioButtons){
            if(r.isChecked()){

                return r.getText().toString();
            }
        }
        return null;
    }


}
