package bancodados.test.core.service.dao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bancodados.test.model.Usuario;
import bancodados.test.view.CadastroActivity;

/**
 * Created by junio on 13/04/16.
 */
public class Adapter{

    private Context context;
    private String campoObrigatorio = "*Campo Obrigatório";
    private Activity activity = new Activity();

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
        String cpf = editText.getText().toString();

        if (campoNull(editText) == true) {
            textView.setText(campoObrigatorio);
            textView.setVisibility(View.VISIBLE);
        }else {
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

    public void validarEmail(EditText editText, TextView textView){

        if(campoNull(editText)){
            textView.setText(campoObrigatorio);
            textView.setVisibility(View.VISIBLE);
        }else{
            String email = editText.getText().toString();
            if (email != null && email.length() > 0) {
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);
                if (!(matcher.matches())) {
                    textView.setText("Email Inválido");
                    textView.setVisibility(View.VISIBLE);
                }else{
                    textView.setVisibility(View.GONE);
                }
            }
        }
    }

    public Boolean validarCamposUsuario(Usuario usuario) {
        Integer contador = 0;
        UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(context);
        List<Usuario> u = usuarioDao.findByLogin(usuario.getLogin());

        if (usuario.getNome().equals("")) {
            contador++;
        }
        if (usuario.getNome().equals("")) {
            contador++;
        }
        if (usuario.getLogin().equals("")) {
            contador++;
        }
        if(!(u.isEmpty() == true)){
                if(u.get(0).getLogin().equals(usuario.getLogin())) {
                    contador++;
                }
        }else{
            contador++;
        }
        if (usuario.getPassword().equals("")) {
            contador++;
        }
        if (usuario.getCpf().equals("")) {
            contador++;
        }
        if (usuario.getEmail().equals("")) {
            contador++;
        }
        if (contador == 0) {
            return true;
        } else{
            return false;
        }
    }

    public Boolean salvarUsuario(Usuario usuario, Boolean validacao) {
        try {
            AlertDialog alert = new AlertDialog.Builder(context).create();
            if (validacao == true) {
                UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(context);
                usuarioDao.save(usuario.getClass(), usuario);
                alert.setTitle("Alerta");
                alert.setMessage("Usuário Criado com Sucesso!");
                alert.setButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.show();
                return true;
            } else {
                alert.setTitle("Alerta");
                alert.setMessage("Os campos: NOME, LOGIN, PASSWORD, CPF E EMAIL, são obrigatórios verifique se estão corretos!");
                alert.setButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
                return false;
            }
        } catch (Exception e) {
            Log.d("--------", e.getMessage());
            return false;
        }

    }

    public void validarNivel(EditText editText, TextView textView){
        String nivel = editText.getText().toString();
        if(campoNull(editText)){
            textView.setText(campoObrigatorio);
            textView.setVisibility(View.VISIBLE);
        }else{
            if(!(nivel.equals("1") || nivel.equals("2"))){
                textView.setText("Você só pode se definir como nível 1 ou 2");
                textView.setVisibility(View.VISIBLE);
            }else{
                textView.setVisibility(View.GONE);
            }
        }
    }

    public Boolean isNumber(EditText editText){
        try{
            Integer.parseInt(editText.getText().toString());
            return true;
        }catch(Exception e){
            return false;
        }
    }


}
