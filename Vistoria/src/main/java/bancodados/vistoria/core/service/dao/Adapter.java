package bancodados.vistoria.core.service.dao;

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

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.activities.LoginActivity;

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

    public void validarVisibilidade(Boolean validacao, View view){
        if(validacao){
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
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

    public void validarLoginECampo(EditText editText, TextView textView){

        try {
            if (!(campoNull(editText) == true)) {
                UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(context);
                Usuario usuario = usuarioDao.findByLogin(editText.getText().toString());

                if (usuario == null) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setText("*Usuário Já Existe");
                    textView.setVisibility(View.VISIBLE);
                }
            } else {
                textView.setText(campoObrigatorio);
                textView.setVisibility(View.VISIBLE);
            }
        }catch (java.sql.SQLException e) {
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
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
        }else{
            textView.setText(campoObrigatorio);
            textView.setVisibility(View.VISIBLE);
        }
    }

    public void validarConfirmacaoSenha(EditText editText, EditText editText2, TextView textView){
        if(campoNull(editText2)){
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

    public Boolean validarCfp(String cpf) {

        Integer numeroMultiplicacao;
        String digito = "";
        String cpfAux = "";

        if (cpf == null) {
            return false;
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
                if(digito.charAt(0) == cpfAux.charAt(9) && digito.charAt(1) == cpfAux.charAt(10)) {
                    return true;
                } else {
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    public Boolean validarEmail(String email){

        if(email == null){
            return false;
        }else{
            if (email != null && email.length() > 0) {
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);
                if (!(matcher.matches())) {
                    return false;
                }else{
                    return true;
                }
            }
            return false;
        }
    }

    public Boolean validarCamposUsuario(Usuario usuario) throws SQLException {
        Integer contador = 0;
        UsuarioDaoImpl usuarioDao =  new UsuarioDaoImpl(context);
        Usuario u = usuarioDao.findByLogin(usuario.getLogin());

        if (usuario.getNome().equals("")) {
            contador++;
        }
        if (usuario.getLogin().equals("")) {
            contador++;
        }
        if(!(u == null)){
                if(u.getLogin().equals(usuario.getLogin())) {
                    contador++;
                }
        }
        if (usuario.getPassword().length() < 5) {
            contador++;
        }
        if (usuario.getCpf().equals("") || !validarCfp(usuario.getCpf())) {
            contador++;
        }
        if (!validarEmail(usuario.getEmail())) {
            contador++;
        }
        if (contador == 0) {
            return true;
        } else{
            return false;
        }
    }

    public Boolean validarCamposUsuarioUpdate(Usuario usuario) throws SQLException {
        Integer contador = 0;

        if(usuario.getNome().equals("")) {
            contador++;
        }
        if(usuario.getCpf().equals("") || !validarCfp(usuario.getCpf())) {
            contador++;
        }
        if(!validarEmail(usuario.getEmail())) {
            contador++;
        }
        if(contador == 0) {
            return true;
        } else{
            return false;
        }
    }

    public Boolean validarPassword(String password, String passwordAuxiliar){
        if(password.equals("") && passwordAuxiliar.equals(""))
            return true;
        else if(password.equals(passwordAuxiliar)){
            return true;
        }else{
            return false;
        }
    }

    public Boolean salvarUsuario(final Usuario usuario, Boolean validacao) {
        try {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            if (validacao) {
                final UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(context);

                alertBuilder.setTitle("Alerta")
                        .setMessage("Deseja realmente criar usuário?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                usuarioDao.save(Usuario.class, usuario);
                                Intent intent = new Intent(context, LoginActivity.class);
                                context.startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

                return true;
            } else {
                alertBuilder.setTitle("Alerta")
                    .setMessage("Os campos: NOME, LOGIN, PASSWORD, CPF E EMAIL, são obrigatórios verifique se estão corretos!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("--------", e.getMessage());
        }
        return null;
    }

    public String conversaoEditTextParaString(EditText editText){
        return editText.getText().toString();
    }
}
