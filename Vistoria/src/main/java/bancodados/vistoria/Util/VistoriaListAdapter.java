package bancodados.vistoria.Util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.model.ViewHolderVistoria;

/**
 * Created by junio on 19/10/16.
 */

public  class VistoriaListAdapter extends ArrayAdapter<ViewHolderVistoria> {

    private List<ViewHolderVistoria> items;
    private int layoutResourceId;
    private Context context;

    public VistoriaListAdapter(Context context, int layoutResourceId, List<ViewHolderVistoria> items) {
        super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        VistoriaHolder vistoriaHolder = null;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);


        vistoriaHolder = new VistoriaHolder();

        vistoriaHolder.viewHolderVistoria = items.get(position);
        vistoriaHolder.autor = (TextView) row.findViewById(R.id.autorVistoriaItem);
        vistoriaHolder.data = (TextView) row.findViewById(R.id.dataVistoriaItem);
        vistoriaHolder.bairro = (TextView) row.findViewById(R.id.bairroVistoriaItem);
        vistoriaHolder.municipio = (TextView) row.findViewById(R.id.municipoVistoriaItem);

        vistoriaHolder.verVistoriaItem = (Button) row.findViewById(R.id.verVistoriaItem);
        vistoriaHolder.verVistoriaItem.setTag(vistoriaHolder.viewHolderVistoria);
        vistoriaHolder.removerVistoria = (Button) row.findViewById(R.id.removerVistoriaItem);
        vistoriaHolder.removerVistoria.setTag(vistoriaHolder.viewHolderVistoria);

        if(vistoriaHolder.viewHolderVistoria.getIdUsuario() == Usuario.getInstance().getId()) {
            vistoriaHolder.removerVistoria.setVisibility(View.VISIBLE);
        }

        row.setTag(vistoriaHolder);
        setupItem(vistoriaHolder);

        return row;
    }

    private void setupItem(VistoriaHolder holder){
        holder.autor.setText(holder.viewHolderVistoria.getAutor());
        holder.data.setText(holder.viewHolderVistoria.getData());
        holder.municipio.setText(holder.viewHolderVistoria.getMunicipio());
        holder.bairro.setText(holder.viewHolderVistoria.getBairro());
    }

    public static class VistoriaHolder {
        ViewHolderVistoria viewHolderVistoria;
        TextView autor;
        TextView data;
        TextView municipio;
        TextView bairro;
        Button verVistoriaItem;
        Button removerVistoria;
    }
}
