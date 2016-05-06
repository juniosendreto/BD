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

    public void validarCampo(EditText editText, TextView textView){
        if(campoNull(editText) == true){
            textView.setText(campoObrigatorio);
            textView.setVisibility(View.VISIBLE);
        }else{
            textView.setVisibility(View.GONE);
        }
    }
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
        if(!(campoNull(editText) == true)) {
            if (editText.getText().toString().length() < 5) {
                textView.setText("Sua senha deve ter no mínimo 5 caracteres");
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        }else{
            textView.setText(campoObrigatorio);
            textView.setVisibility(View.VISIBLE);
        }
    }

    public void validarConfirmacaoSenha(EditText editText, EditText editText2, TextView textView){
        if(campoNull(editText2) == true){
            textView.setText(campoObrigatorio);
            textView.setVisibility(View.VISIBLE);

        }else{
            if(editText.getText().toString().equals(editText2.getText().toString())) {
                textView.setVisibility(View.GONE);
            }else{
                textView.setText("Confirmação da Senha Incompatível");
                textView.setVisibility(View.VISIBLE);
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

    public void validarCfp(EditText editText, TextView textView) {

        Integer numeroMultiplicacao;
        String digito = "";
        String cpfAux = "";
        String cpf = editText.toString();

        if (campoNull(editText) == true) {
            textView.setText(campoObrigatorio);
            textView.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < cpf.length(); i++) {
                if (Character.isDigit(cpf.charAt(i))) {
                    cpfAux = cpfAux + cpf.charAt(i);
                }
            }
            if (cpfAux.length() == 11) {

                for (int i = 0; i < 2; i++) {
                    Integer calculo = 0;
                    Integer calculoAux = 0;

                    if (i == 0) {
                        numeroMultiplicacao = 10;

                        for (int j = 0; j < 9; j++) {
                            calculo = calculo + Integer.parseInt(String.valueOf(cpf.charAt(j))) * numeroMultiplicacao;

                            numeroMultiplicacao--;
                        }

                        calculoAux = 11 - (calculo % 11);

                        if (calculoAux == 10 || calculoAux == 11) {
                            digito = "0";
                        } else {
                            digito = Integer.toString(calculoAux);
                        }

                    } else if (i == 1) {
                        numeroMultiplicacao = 11;

                        for (int j = 0; j < 10; j++) {
                            calculo = calculo + (Integer.parseInt(String.valueOf(cpf.charAt(j))) * numeroMultiplicacao);
                            numeroMultiplicacao--;
                        }
                        calculoAux = 11 - (calculo % 11);

                        if (calculoAux == 10 || calculoAux == 11) {
                            digito = digito + "0";
                        } else {
                            digito = digito + Integer.toString(calculoAux);
                        }
                    }
                }
                if (digito.charAt(0) == cpfAux.charAt(9) && digito.charAt(1) == cpfAux.charAt(10)) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setText("CPF Inválido");
                    textView.setVisibility(View.VISIBLE);
                }
            }else{
                textView.setText("CPF Inválido");
                textView.setVisibility(View.VISIBLE);
            }
        }
    }

}
