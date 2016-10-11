package bancodados.vistoria.core.service.dao;

import android.content.Context;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.model.Localizacao;
import bancodados.vistoria.model.Vistoria;

/**
 * Created by junio on 08/03/16.
 */
public class VistoriaDaoImpl extends AbstractDaoImpl {

    public VistoriaDaoImpl(Context context) {
        super(context);
    }

    public Integer countVistoriasWithTheFK(Context context, Long id){
        Integer contador = 0;
        VistoriaDaoImpl vistoriaDao = new VistoriaDaoImpl(context);
        List<Vistoria> vistorias = (ArrayList) vistoriaDao.listAll(Vistoria.class);

        for(Vistoria v: vistorias){
            if(v.getLocalizacao().getId() == id)
                contador++;
        }
        return contador;
    }
}
