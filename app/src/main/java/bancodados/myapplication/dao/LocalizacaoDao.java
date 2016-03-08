package bancodados.myapplication.dao;

import java.util.List;

import bancodados.myapplication.model.Localizacao;

/**
 * Created by junio on 08/03/16.
 */
public interface LocalizacaoDao {

    public Localizacao save(Localizacao usuario);

    public Localizacao upgrade(Localizacao usuario);

    public Boolean delete(Localizacao usuario);

    public List<Localizacao> listAll();

    public Localizacao findById(Long id);

}
