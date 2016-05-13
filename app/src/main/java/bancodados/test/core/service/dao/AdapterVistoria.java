package bancodados.test.core.service.dao;

import android.widget.RadioButton;

import java.util.List;

/**
 * Created by junio on 13/05/16.
 */
public class AdapterVistoria {

    public String whichButtonIsChacked(List<RadioButton> radioButtons){

        for(RadioButton r: radioButtons){
            if(r.isChecked()){
                return r.getText().toString();
            }
        }
        return null;
    }

}
