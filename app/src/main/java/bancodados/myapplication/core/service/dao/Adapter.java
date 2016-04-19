package bancodados.myapplication.core.service.dao;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by junio on 13/04/16.
 */
public class Adapter {

    public void visibility(View view){
        if(view.getVisibility() == View.GONE){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);

        }
    }

    public void visibilityCheckBox(CheckBox checkBox, View view){
        if(checkBox.isChecked()){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }

    public void visibilityRadioButton(RadioButton radioButton, View view){
        if(radioButton.isChecked()){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }

    public void checkedOnOff(RadioButton radioButton){
        if(radioButton.isChecked()){
            radioButton.setChecked(true);
            Log.d("---------", "checked");
        }else{
            radioButton.setChecked(false);
            Log.d("---------", "not checked");

        }
    }

    public void nullValue(EditText editText, TextView textView){
        if(String.valueOf(editText.getText()).equals("")){
            textView.setVisibility(View.VISIBLE);
        }else{
            textView.setVisibility(View.GONE);
        }
    }



}
