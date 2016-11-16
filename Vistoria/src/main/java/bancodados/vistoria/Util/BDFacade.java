package bancodados.vistoria.Util;

import android.content.Context;

import bancodados.vistoria.core.service.dao.FotoVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.LocalizacaoDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioDaoImpl;
import bancodados.vistoria.core.service.dao.UsuarioVistoriaDaoImpl;
import bancodados.vistoria.core.service.dao.VistoriaDaoImpl;


/**
 * Created by junio on 04/11/16.
 */

public class BDFacade {

    private Context context;

    private UsuarioDaoImpl usuarioDao;
    private UsuarioVistoriaDaoImpl usuarioVistoriaDao;
    private VistoriaDaoImpl vistoriaDao;
    private LocalizacaoDaoImpl localizacaoDao;
    private FotoVistoriaDaoImpl fotoVistoriaDao;

    public BDFacade(Context context){
        this.context = context;
        usuarioDao = new UsuarioDaoImpl(context);
        usuarioVistoriaDao = new UsuarioVistoriaDaoImpl(context);
        vistoriaDao = new VistoriaDaoImpl(context);
        localizacaoDao = new LocalizacaoDaoImpl(context);
        fotoVistoriaDao = new FotoVistoriaDaoImpl(context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public UsuarioDaoImpl getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDaoImpl usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public UsuarioVistoriaDaoImpl getUsuarioVistoriaDao() {
        return usuarioVistoriaDao;
    }

    public void setUsuarioVistoriaDao(UsuarioVistoriaDaoImpl usuarioVistoriaDao) {
        this.usuarioVistoriaDao = usuarioVistoriaDao;
    }

    public VistoriaDaoImpl getVistoriaDao() {
        return vistoriaDao;
    }

    public void setVistoriaDao(VistoriaDaoImpl vistoriaDao) {
        this.vistoriaDao = vistoriaDao;
    }

    public LocalizacaoDaoImpl getLocalizacaoDao() {
        return localizacaoDao;
    }

    public void setLocalizacaoDao(LocalizacaoDaoImpl localizacaoDao) {
        this.localizacaoDao = localizacaoDao;
    }

    public FotoVistoriaDaoImpl getFotoVistoriaDao() {
        return fotoVistoriaDao;
    }

    public void setFotoVistoriaDao(FotoVistoriaDaoImpl fotoVistoriaDao) {
        this.fotoVistoriaDao = fotoVistoriaDao;
    }
}
