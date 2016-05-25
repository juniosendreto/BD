package bancodados.test.core.service.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bancodados.test.R;
import bancodados.test.model.Localizacao;
import bancodados.test.model.Usuario;
import bancodados.test.model.UsuarioVistoria;
import bancodados.test.model.Vistoria;

/**
 * Created by junio on 25/05/16.
 */
public class AdapterListView extends ArrayAdapter {

    private static class ViewHolder{
        TextView autor;
        TextView municipio;
        TextView bairro;
        TextView data;
    }

    public AdapterListView(Context context, ArrayList<Vistoria> vistorias) {
        super(context, R.layout.vistoria_item, vistorias);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        //Usuario usuario = getPosition(position);
        if(convertView == null){
            viewHolder =  new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.vistoria_item, parent, false);

            viewHolder.autor = (TextView) convertView.findViewById(R.id.autorItem2TV);
            viewHolder.municipio = (TextView) convertView.findViewById(R.id.municipioItem2TV);
            viewHolder.bairro = (TextView) convertView.findViewById(R.id.bairroItem2TV);
            viewHolder.data = (TextView) convertView.findViewById(R.id.dataItem2TV);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
