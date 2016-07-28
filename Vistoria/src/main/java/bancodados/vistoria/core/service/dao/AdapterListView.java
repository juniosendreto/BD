package bancodados.vistoria.core.service.dao;

/**
 * Created by junio on 25/05/16.
 */
public class AdapterListView {




    /*
           * View de itens
               * Autor
               * Data
               * Bairro
               * Municio

               ** Todas as vistoria listadas
               ** Com o usuário, data, municipio e bairro

                Eu tenho uma vistoria, através dos relacionamentos eu preciso pegar o usuario
                data e partes da localização.

                Essa alternativa seria fazer querry com join, para acessar esse atributos




    */












    /*private static class ViewHolder{
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
    }*/
}
