package bancodados.vistoria;

import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.core.service.dao.LocalizacaoDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.VistoriaDaoImpl;
import bancodados.vistoria.model.Usuario;
import bancodados.vistoria.model.UsuarioVistoria;
import bancodados.vistoria.model.Vistoria;

/**
 * Created by junio on 17/05/16.
 */
public class VistoriaDaoImplTest extends ConfigBDTestCase{
    public void testSave() throws Exception{

        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(getContext());
        VistoriaDaoImpl vistoriaDao = new VistoriaDaoImpl(getContext());
        UsuarioVistoriaDaoImpl usuarioVistoriaDao = new UsuarioVistoriaDaoImpl(getContext());
        LocalizacaoDaoImpl localizacaoDao = new LocalizacaoDaoImpl(getContext());

        Usuario usuario1 = new Usuario("Junio", "39761555860", "junio", "12345", "junio.sendreto@hotmail.com", "SP", "rua", "4321", "12321", 1);
        Usuario usuario2 = new Usuario("Jarvan", "39761555860", "junio", "12345", "junio.sendreto@hotmail.com", "SP", "rua", "4321", "12321", 1);

        Vistoria vistoria1 = null;

        Vistoria vistoria2 = null;
        List<Vistoria> vistorias = new ArrayList<>();
        vistorias.add(vistoria1);
        vistorias.add(vistoria2);

       // Localizacao localizacao1 = new Localizacao((Collection) vistorias, 1.9747654, -2.387464, "SJC", "teste");
        //Localizacao localizacao2 = new Localizacao((Collection) vistorias, -2.387464, -2.387464, "SJC", "teste");

        //vistoria1.setLocalizacao(localizacao1);
        //vistoria2.setLocalizacao(localizacao2);

        UsuarioVistoria usuarioVistoria1 = null;
        UsuarioVistoria usuarioVistoria2 = null;

        try {


            usuarioDao.save(Usuario.class, usuario1);
            usuarioDao.save(Usuario.class, usuario2);
            ///localizacaoDao.save(Localizacao.class, localizacao1);
            //localizacaoDao.save(Localizacao.class, localizacao2);
            vistoriaDao.save(Vistoria.class, vistoria1);
            vistoriaDao.save(Vistoria.class, vistoria2);
            usuarioVistoriaDao.save(UsuarioVistoria.class, usuarioVistoria1);
            usuarioVistoriaDao.save(UsuarioVistoria.class, usuarioVistoria2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void testUpdate() throws Exception {

    }



    public void testDelete() throws Exception{

    }

    public void testListAll() throws Exception{

    }

    public void testFindById() throws Exception{

    }

    public void testCountAllRows() throws Exception{



    }

}
