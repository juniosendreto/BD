package bancodados.myapplication.dao;

import java.util.List;

import bancodados.myapplication.model.UsuarioVistoria;

/**
 * Created by junio on 08/03/16.
 */
public interface UsuarioVistoriaDao {

    public UsuarioVistoria save(UsuarioVistoria usuario);

    public UsuarioVistoria upgrade(UsuarioVistoria usuario);

    public Boolean delete(UsuarioVistoria usuario);

    public List<UsuarioVistoria> listAll();

    public UsuarioVistoria findById(Long id);
}
