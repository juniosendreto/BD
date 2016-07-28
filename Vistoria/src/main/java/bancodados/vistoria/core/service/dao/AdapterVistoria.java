package bancodados.vistoria.core.service.dao;

import android.content.Context;
import android.widget.CheckBox;
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

    public Integer whichButtonIsChacked(List<RadioButton> radioButtons){

        for(int i = 0; i < radioButtons.size(); i++){
            if(radioButtons.get(i).isChecked())
                return i + 1;
        }
        return 0;
    }

    public void recuperarCheckRadionButton(List<RadioButton> radioButtons, Integer numeroCheck){
        if(numeroCheck != null) {
            for (int i = 0; i < radioButtons.size(); i++) {
                if ((i + 1) == numeroCheck)
                    radioButtons.get(i).setChecked(true);
            }
        }
    }

    public Boolean recuperaCheck(CheckBox checkBox, Boolean valorCheck){
        if(valorCheck == null){
            checkBox.setChecked(false);
            return false;
        } else if(valorCheck == true){
            checkBox.setChecked(true);
            return true;
        }
        return false;
    }


}
