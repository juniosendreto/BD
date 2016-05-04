package bancodados.test.core.service.dao;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import bancodados.test.model.Usuario;

/**
 * Created by junio on 13/04/16.
 */
public class Adapter{

    private Context context;
    private String campoObrigatorio = "*Campo Obrigatório";

    public Adapter(Context context) {
        this.context = context;
    }

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
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
        }
    }

    public void radionButtonChecked(RadioButton radioButton, View view){
        if(radioButton.isChecked()){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }

    public void nullValue(EditText editText, TextView textView){
        if(String.valueOf(editText.getText()).equals("")){
            textView.setVisibility(View.VISIBLE);
        }else{
            textView.setVisibility(View.GONE);
        }
    }

    public void nullValueByCheckBox(CheckBox checkBox, EditText editText, TextView textView) {
        if (checkBox.isChecked()) {
            if (String.valueOf(editText.getText()).equals("")) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
    }

    /*public void validarCampo(EditText editText, TextView textView){
        if(editText.getText().toString().isEmpty()){
            textView.setText(campoObrigatorio);
            textView.setVisibility(View.VISIBLE);
        }else{
            textView.setVisibility(View.GONE);
        }
    }*/
    public void validarLoginECampo(EditText editText, TextView textView){

        try {
            if (!(campoNull(editText) == true)) {
                UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(context);
                List<Usuario> usuarioList = usuarioDao.findByLogin(editText.getText().toString());

                if(usuarioList == null){
                    textView.setVisibility(View.GONE);
                }
                else if (!usuarioList.isEmpty()) {
                    textView.setText("*Usuário Já Existe");
                    textView.setVisibility(View.VISIBLE);
                }
            } else {
                textView.setText(campoObrigatorio);
                textView.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            Log.d("----------", e.getMessage());
        }
    }

    public void validarSenha(EditText editText, TextView textView){

        if(!(campoNull(editText))) {
            if (editText.getText().toString().length() < 5) {
                textView.setText("Sua senha deve ter no mínimo 5 caracteres");
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
    }

    public Boolean campoNull(EditText editText){
        if(editText.getText().toString().isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
