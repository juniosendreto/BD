package bancodados.myapplication.core.service.dao;

import android.view.View;

/**
 * Created by junio on 13/04/16.
 */
public class Validator {

    public void visibility(View view){
        if(view.getVisibility() == View.GONE){
            view.setVisibility(View.INVISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }

}
