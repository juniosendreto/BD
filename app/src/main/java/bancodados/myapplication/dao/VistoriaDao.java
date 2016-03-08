package bancodados.myapplication.dao;

import java.util.List;

import bancodados.myapplication.model.Vistoria;

/**
 * Created by junio on 08/03/16.
 */
public interface VistoriaDao {

    public Vistoria save(Vistoria usuario);

    public Vistoria upgrade(Vistoria usuario);

    public Boolean delete(Vistoria usuario);

    public List<Vistoria> listAll();

    public Vistoria findById(Long id);
}
