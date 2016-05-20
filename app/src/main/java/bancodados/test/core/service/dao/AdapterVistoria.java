package bancodados.test.core.service.dao;

import android.app.Activity;
import android.content.Context;
import android.widget.RadioButton;

import java.util.List;

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
