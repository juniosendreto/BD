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

    public Integer whichButtonIsChacked(List<RadioButton> radioButtons){

        for(int i = 0; i < radioButtons.size(); i++){
            if(radioButtons.get(i).isChecked())
                return i + 1;
        }
        return null;
    }


}
